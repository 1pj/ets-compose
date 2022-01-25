package com.ets.androiddev.presentation.home

import com.ets.androiddev.domain.entities.Place

sealed class HomeCommand {
    class LoadData(val place: Place) : HomeCommand()
}
