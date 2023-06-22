package com.caminaseguro.domain

import com.google.maps.android.compose.MarkerState

data class CustomMarker(
    val title: String,
    val state: MarkerState,
    val draggable: Boolean
)
