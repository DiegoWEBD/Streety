package com.caminaseguro.domain.zone

import androidx.compose.ui.graphics.Color
import com.caminaseguro.domain.CustomMarker
import com.google.android.gms.maps.model.LatLng

enum class ZoneSafety { Indeterminate, Safe, Neutral, Danger }

abstract class Zone(
    val id: Int,
    var pointMarkers: List<CustomMarker>,
    var fillColor: Color = Color.Transparent,
    var strokeColor: Color = Color.Transparent
) {
    abstract fun copy(
        id: Int = this.id,
        pointMarkers: List<CustomMarker> = this.pointMarkers
    ): Zone
}