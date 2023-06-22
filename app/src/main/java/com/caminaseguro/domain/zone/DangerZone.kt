package com.caminaseguro.domain.zone

import androidx.compose.ui.graphics.Color
import com.caminaseguro.domain.CustomMarker
import com.google.android.gms.maps.model.LatLng

class DangerZone(id: Int, pointMarkers: List<CustomMarker>): Zone(id, pointMarkers) {

    init {
        this.fillColor = Color(0x5FC50F00)
        this.strokeColor = Color(0x00C50F00)
    }

    override fun copy(id: Int, pointMarkers: List<CustomMarker>): Zone {
        return DangerZone(id, pointMarkers)
    }
}
