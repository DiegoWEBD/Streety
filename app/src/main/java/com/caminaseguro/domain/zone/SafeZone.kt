package com.caminaseguro.domain.zone

import androidx.compose.ui.graphics.Color
import com.caminaseguro.domain.CustomMarker
import com.google.android.gms.maps.model.LatLng

class SafeZone(id: Int, pointMarkers: List<CustomMarker>): Zone(id, pointMarkers) {

    init {
        this.fillColor = Color(0x5F05B100)
        this.strokeColor = Color(0x0005B100)
    }

    override fun copy(id: Int, pointMarkers: List<CustomMarker>): Zone {
        return SafeZone(id, pointMarkers)
    }
}