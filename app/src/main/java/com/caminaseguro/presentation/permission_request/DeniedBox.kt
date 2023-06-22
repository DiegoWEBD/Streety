package com.caminaseguro.presentation.permission_request

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DeniedBox(
    goToAppSettings: () -> Unit
) {
    val message = "Debes otorgar el o los permisos solicitados a la aplicación para poder " +
            "utilizar todas sus funcionalidades"

    AlertDialog(
        title = { Text(text = "Permiso requerido") },
        text = {
            Text(text = message)
        },
        onDismissRequest = {},
        confirmButton = {
            Button(
                onClick = goToAppSettings,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ir a ajustes de la app")
            }
        }
    )
}

