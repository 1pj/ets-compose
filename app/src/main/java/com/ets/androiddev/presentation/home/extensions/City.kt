
package com.ets.androiddev.presentation.home.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ets.androiddev.R
import com.ets.androiddev.domain.entities.Place

@Composable
internal fun Place.displayText(): String = when (this) {
    Place.NewYork -> stringResource(R.string.places_new_york)
    Place.London -> stringResource(R.string.places_london)
    Place.Tokyo -> stringResource(R.string.places_tokyo)
    Place.Sydney -> stringResource(R.string.places_sydney)
}
