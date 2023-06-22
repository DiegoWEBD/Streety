package com.caminaseguro.presentation.permission_request

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissions(
    permissions: List<String>,
    onRequestSucceeded: () -> Unit,
    rationaleMessage: String,
    goToAppSettings: () -> Unit
) {
    val permissionsState = rememberMultiplePermissionsState(permissions)

    LaunchedEffect(true) {
        permissionsState.launchMultiplePermissionRequest()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        if(permissionsState.allPermissionsGranted) {
            onRequestSucceeded()
        }
        else if(permissionsState.shouldShowRationale){
            Rationale(
                message = rationaleMessage,
                onOkClick = { permissionsState.launchMultiplePermissionRequest() }
            )
        }
        else {
            DeniedBox(goToAppSettings = goToAppSettings)
        }
    }
}