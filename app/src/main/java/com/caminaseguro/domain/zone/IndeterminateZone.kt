package com.caminaseguro.domain.zone

import androidx.compose.ui.graphics.Color
import com.caminaseguro.domain.CustomMarker
import com.google.android.gms.maps.model.LatLng

class IndeterminateZone(id: Int, pointMarkers: List<CustomMarker>): Zone(id, pointMarkers) {

    init {
        this.fillColor = Color(0x5F000000)
        this.strokeColor = Color(0x00000000)
    }

    override fun copy(id: Int, pointMarkers: List<CustomMarker>): Zone {
        return IndeterminateZone(id, pointMarkers)
    }
}