package com.caminaseguro.presentation.map

import com.caminaseguro.domain.CustomMarker
import com.caminaseguro.domain.zone.Zone
import com.caminaseguro.domain.zone.ZoneSafety
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.Polygon
import com.google.maps.android.compose.MarkerState

sealed class MapEvent {
    object EnableMyLocation: MapEvent()
    data class CreateMarker(val latLng: LatLng): MapEvent()
    object CreateZone: MapEvent()
    data class SelectZone(val zone: Zone): MapEvent()
    object OnMapClick: MapEvent()
    data class ChangeSelectedZoneSafety(val newSafety: ZoneSafety): MapEvent()
    data class DeleteZone(val zoneId: Int): MapEvent()
    data class AddMarkerOnUserLocation(val latLng: LatLng): MapEvent()
    data class OnMarkerClick(val marker: Marker): MapEvent()
    data class OnMarkerDrag(val customMarker: CustomMarker): MapEvent()
}