package com.caminaseguro.domain.zone

import androidx.compose.ui.graphics.Color
import com.caminaseguro.domain.CustomMarker
import com.google.android.gms.maps.model.LatLng

class NeutralZone(id: Int, pointMarkers: List<CustomMarker>): Zone(id, pointMarkers) {

    init {
        this.fillColor = Color(0x5FFFE000)
        this.strokeColor = Color(0x00FFE000)
    }

    override fun copy(id: Int, pointMarkers: List<CustomMarker>): Zone {
        return NeutralZone(id, pointMarkers)
    }
}