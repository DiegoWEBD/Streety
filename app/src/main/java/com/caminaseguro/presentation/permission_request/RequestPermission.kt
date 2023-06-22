package com.caminaseguro.presentation.permission_request

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermission(
    permission: String,
    isOptional: Boolean,
    onRequestSucceeded: () -> Unit,
    rationaleMessage: String,
    goToAppSettings: () -> Unit
) {
    val permissionState = rememberPermissionState(permission)

    LaunchedEffect(true) {
        permissionState.launchPermissionRequest()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        if(permissionState.status.isGranted) {
            onRequestSucceeded()
        }
        else if(permissionState.status.shouldShowRationale){
            Rationale(
                message = rationaleMessage,
                onOkClick = { permissionState.launchPermissionRequest() }
            )
        }
        else if(!isOptional) {
            DeniedBox(goToAppSettings = goToAppSettings)
        }
        else{
            Text(text = "ES OPCIONAL")
        }
    }
}