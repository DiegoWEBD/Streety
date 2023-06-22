package com.caminaseguro.presentation.permission_request

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Rationale(
    message: String,
    onOkClick: () -> Unit
) {
    AlertDialog(
        title = { Text(text = "Permiso requerido") },
        text = { Text(message) },
        onDismissRequest = onOkClick,
        confirmButton = {
            Button(
                onClick = onOkClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Vale")
            }
        }
    )
}