package com.caminaseguro.presentation.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.caminaseguro.domain.CustomMarker
import com.caminaseguro.domain.zone.DangerZone
import com.caminaseguro.domain.zone.IndeterminateZone
import com.caminaseguro.domain.zone.NeutralZone
import com.caminaseguro.domain.zone.SafeZone
import com.caminaseguro.domain.zone.ZoneSafety
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.SphericalUtil
import com.google.maps.android.compose.MarkerState

class MapViewModel: ViewModel() {

    var state by mutableStateOf(MapState())

    fun onEvent(event: MapEvent) {
        when(event) {
            is MapEvent.CreateMarker -> {
                state = state.copy(
                    markers = state.markers.plus(makeCustomMarker(event.latLng))
                )
            }
            is MapEvent.EnableMyLocation -> {
                state = state.copy(
                    properties = state.properties.copy(isMyLocationEnabled = true)
                )
            }
            is MapEvent.CreateZone -> {
                val newZone = makeNewZone(state.markers.map { it.state.position })
                state = state.copy(
                    zones = state.zones.plus(newZone),
                    markers = listOf()
                )
            }
            is MapEvent.SelectZone -> {
                state = state.copy(
                    selectedZoneId = event.zone.id
                )
            }
            is MapEvent.OnMapClick -> unselectZone()
            is MapEvent.ChangeSelectedZoneSafety -> changeSelectedZoneSafety(event.newSafety)
            is MapEvent.DeleteZone -> deleteZone(event.zoneId)
            is MapEvent.AddMarkerOnUserLocation -> addMarkerOnUserLocation(event.latLng)
            is MapEvent.OnMarkerClick -> onMarkerClick(event.marker)
            is MapEvent.OnMarkerDrag -> onMarkerDrag(event.customMarker)
        }
    }

    private fun onMarkerDrag(customMarker: CustomMarker) {
        if(!customMarker.title.contains("Punto")) return

        val pointNumber = customMarker.title.split(' ')[1].toInt()

        /*val newSelectedZonePoints = state.selectedZone!!.points.mapIndexed { index, point ->
            if(index + 1 == pointNumber) customMarker.state.position
            else point
        }*/
        //val newZone = state.selectedZone!!.copy(points = newSelectedZonePoints)
    }

    private fun addMarkerOnUserLocation(point: LatLng) {
        state = state.copy(
            userMarker = makeCustomMarker(point, "Mi ubicación", false)
        )
    }

    fun onMarkerClick(marker: Marker): Boolean {
        val mustAvoidDefaultBehavior = true
        marker.showInfoWindow()
        return mustAvoidDefaultBehavior
    }

    fun calculateDistanceBetweenPoints(point1: LatLng, point2: LatLng): Double = SphericalUtil.computeDistanceBetween(point1, point2)

    fun launchCameraAnimation() {
        state = state.copy(startCameraAnimation = true)
    }

    fun stopCameraAnimation() {
        state = state.copy(startCameraAnimation = false)
    }

    fun setShowEditZoneBox(newState: Boolean) {
        state = state.copy(showEditZoneBox = newState)
    }

    private fun deleteZone(zoneId: Int) {
        state = state.copy(
            zones = state.zones.filter { it.id != zoneId },
            selectedZoneId = null
        )
    }

    private fun changeSelectedZoneSafety(newSafety: ZoneSafety) {
        val zone = state.zones.find { it.id == state.selectedZoneId } ?: return

        val updatedZone = when(newSafety) {
            ZoneSafety.Indeterminate -> IndeterminateZone(zone.id, zone.pointMarkers)
            ZoneSafety.Safe -> SafeZone(zone.id, zone.pointMarkers)
            ZoneSafety.Neutral -> NeutralZone(zone.id, zone.pointMarkers)
            ZoneSafety.Danger -> DangerZone(zone.id, zone.pointMarkers)
        }
        state = state.copy(
            zones = state.zones.map {
                if(it.id == zone.id) updatedZone
                else it
            },
            selectedZoneId = updatedZone.id,
            //showEditZoneBox = false
        )
    }

    private fun makeCustomMarker(position: LatLng, title: String? = null, draggable: Boolean = false): CustomMarker {
        val markerState = MarkerState(position)

        if(title != null) return CustomMarker(title, markerState, draggable)
        val markerNumber =
            if(state.markers.isEmpty()) 1
            else state.markers
                .maxBy { it.title.split(' ')[1].toInt() }
                .title.split(' ')[1]
                .toInt() + 1

        return CustomMarker("Marcador $markerNumber", markerState, draggable)
    }

    private fun makeNewZone(points: List<LatLng>): IndeterminateZone {
        val id =
            if(state.zones.isEmpty()) 1
            else state.zones.maxBy { it.id }.id + 1

        val pointMarkers = points.mapIndexed { index, point ->
            CustomMarker("Punto ${index + 1}", MarkerState(point), true)
        }

        return IndeterminateZone(id, pointMarkers)
    }

    private fun unselectZone() {
        if(state.selectedZoneId == null) return
        state = state.copy(
            selectedZoneId = null
        )
    }

    /*private fun findZone(points: List<LatLng>): Zone? {
        for(zone in state.zones) {
            if(zone.points.size == points.size - 1) {
                var found = true

                zone.points
                    .slice(IntRange(0, zone.points.size - 2))
                    .forEachIndexed { index, point ->
                        if(!found) return@forEachIndexed
                        if(point != points[index]) found = false
                    }
                if(found) return zone
            }
        }
        return null
    }*/
}