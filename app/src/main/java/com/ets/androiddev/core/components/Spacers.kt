@file:Suppress("unused")

package com.ets.androiddev.core.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.ets.androiddev.R

object Spacers {
    @Composable
    fun Xs() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_xs)))
    }

    @Composable
    fun S() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_s)))
    }

    @Composable
    fun M() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_m)))
    }

    @Composable
    fun L() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_l)))
    }

    @Composable
    fun Xl() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_xl)))
    }

    @Composable
    fun Xxl() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_xxl)))
    }

    @Composable
    fun Xxxl() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_xxxl)))
    }
}
