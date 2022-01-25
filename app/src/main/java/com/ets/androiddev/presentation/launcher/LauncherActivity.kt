package com.ets.androiddev.presentation.launcher

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.ets.androiddev.core.mvi.MviComposeActivity
import com.ets.androiddev.core.theme.AppTheme
import com.ets.androiddev.presentation.home.Home
import com.ets.androiddev.presentation.home.HomeCommand
import com.ets.androiddev.presentation.home.HomeViewModel
import com.ets.androiddev.presentation.home.HomeViewState

class LauncherActivity : MviComposeActivity<HomeViewModel, HomeViewState, HomeCommand>() {
    override val viewModel by viewModels<HomeViewModel>()

    @Composable
    override fun Content(
        state: HomeViewState,
        commands: (HomeCommand) -> Unit
    ) {
        AppTheme { Home(state, commands) }
    }
}
