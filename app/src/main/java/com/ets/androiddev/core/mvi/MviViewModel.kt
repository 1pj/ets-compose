package com.ets.androiddev.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ets.androiddev.domain.datatypes.Async
import com.ets.androiddev.domain.datatypes.Fail
import com.ets.androiddev.domain.datatypes.Loading
import com.ets.androiddev.domain.datatypes.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class MviViewModel<S>(initialState: S) : ViewModel() {
    private val state = MutableStateFlow(initialState)

    fun stateAsFlow(): StateFlow<S> = state

    fun <T> withState(block: (S) -> T) = block(state.value)

    protected fun setState(reducer: S.() -> S) {
        state.value = reducer(state.value)
    }

    protected suspend fun <V> Flow<V>.executeCatching(reducer: S.(Async<V>) -> S) {
        catch { setState { reducer(Fail(it)) } }
        collect { setState { reducer(Success(it)) } }
    }

    protected fun <V> executeCatching(
        block: suspend S.() -> V,
        reducer: S.(Async<V>) -> S
    ) {
        viewModelScope.launch {
            setState { reducer(Loading) }

            try {
                val result = block(state.value)
                setState { reducer(Success(result)) }
            } catch (error: Throwable) {
                setState { reducer(Fail(error)) }
            }
        }
    }
}
