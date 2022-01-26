package com.ets.androiddev.di

import com.ets.androiddev.data.services.WeatherServiceImpl
import com.ets.androiddev.domain.services.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideWeatherService(): WeatherService = WeatherServiceImpl()
}
