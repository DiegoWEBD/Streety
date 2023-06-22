package com.caminaseguro.presentation.map

import com.caminaseguro.domain.CustomMarker
import com.caminaseguro.domain.zone.Zone
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties

private val initialPosition = LatLng(-29.939931, -71.253549)

data class MapState(
    val properties: MapProperties = MapProperties(),
    val cameraPosition: CameraPosition = CameraPosition.fromLatLngZoom(initialPosition, 12.5f),
    val markers: List<CustomMarker> = listOf(),
    val zones: List<Zone> = listOf(),
    val selectedZoneId: Int? = null,
    val showEditZoneBox: Boolean = false,
    val startCameraAnimation: Boolean = false,
    val userMarker: CustomMarker? = null,
)
