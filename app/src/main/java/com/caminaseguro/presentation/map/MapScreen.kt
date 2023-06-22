package com.caminaseguro.presentation.map

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.caminaseguro.presentation.map.buttons.MyLocationButton
import com.caminaseguro.presentation.map.zone.ZoneOptionsBar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    viewModel: MapViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    padding: PaddingValues = PaddingValues(0.dp),
    launchToast: (String) -> Unit = {},
    getDeviceLocation: () -> Unit,
    currentLocation: LatLng,
    isLocationPermissionGranted: Boolean = false
) {
    val uiSettings = MapUiSettings(zoomControlsEnabled = false)
    val cameraPositionState = rememberCameraPositionState {
        position = viewModel.state.cameraPosition
    }
    val centerCallback = {
        getDeviceLocation()
        viewModel.launchCameraAnimation()
    }

    LaunchedEffect(viewModel.state.startCameraAnimation) {
        if(viewModel.state.startCameraAnimation) {
            cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(currentLocation, 16f))
            viewModel.stopCameraAnimation()
        }
    }

    /*LaunchedEffect(currentLocation) {
        viewModel.onEvent(MapEvent.AddMarkerOnUserLocation(currentLocation))
    }*/

    Scaffold(
        floatingActionButton = {
            if(isLocationPermissionGranted) {
                MyLocationButton(
                    centerCallback = centerCallback,
                    isCentered = viewModel.calculateDistanceBetweenPoints(
                        point1 = currentLocation,
                        cameraPositionState.position.target
                    ) <= 30
                )
            }
        },
        bottomBar = {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(padding.calculateBottomPadding())
            )
        }
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.state.properties,
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            contentPadding = padding,
            onMapClick = { viewModel.onEvent(MapEvent.OnMapClick) },
            onMapLongClick = { position -> viewModel.onEvent(MapEvent.CreateMarker(position)) },
        ) {
            if(viewModel.state.userMarker != null) {
                Marker(
                    state = viewModel.state.userMarker!!.state,
                    title = viewModel.state.userMarker!!.title,
                    draggable = viewModel.state.userMarker!!.draggable,
                    onClick = { marker -> viewModel.onMarkerClick(marker) }
                )
            }
            viewModel.state.markers.forEach { marker ->
                Marker(
                    state = marker.state,
                    title = marker.title,
                    draggable = marker.draggable,
                    onClick = { currentMarker -> viewModel.onMarkerClick(currentMarker) }
                )
            }
            viewModel.state.zones.forEach { zone ->
                Polygon(
                    points = zone.pointMarkers.map { marker -> marker.state.position },
                    tag = "Zona ${zone.id}",
                    clickable = true,
                    fillColor = zone.fillColor,
                    strokeColor = zone.strokeColor,
                    onClick = { viewModel.onEvent(MapEvent.SelectZone(zone)) }
                )
                if(zone.id == viewModel.state.selectedZoneId) {
                    zone.pointMarkers.forEach { marker ->
                        Marker(
                            state = marker.state,
                            title = marker.title,
                            draggable = marker.draggable,
                            onClick = { currentMarker -> viewModel.onMarkerClick(currentMarker) }
                        )
                    }
                }
            }
        }
        ZoneOptionsBar(
            viewModel = viewModel,
            padding = it
        )
    }
}