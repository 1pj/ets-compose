package com.ets.androiddev.core.mvi

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class MviComposeActivity<V, S, C> :
    AppCompatActivity() where V : MviComposeViewModel<S, C> {
    private val pendingCommands = MutableSharedFlow<C>()

    abstract val viewModel: V

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch {
            pendingCommands.collect { onCommand(it) }
        }

        setContent {
            viewModel.stateAsFlow()
                .collectAsState()
                .value
                ?.let { state ->
                    ProvideWindowInsets {
                        Content(
                            state = state,
                            commands = {
                                lifecycleScope.launch { pendingCommands.emit(it) }
                            }
                        )
                    }
                }
        }
    }

    protected open fun onCommand(command: C) = viewModel.emitCommand(command)

    @Composable
    abstract fun Content(state: S, commands: (C) -> Unit)
}
