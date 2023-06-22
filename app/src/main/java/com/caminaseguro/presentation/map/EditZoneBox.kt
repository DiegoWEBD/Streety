package com.caminaseguro.presentation.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.caminaseguro.domain.zone.DangerZone
import com.caminaseguro.domain.zone.IndeterminateZone
import com.caminaseguro.domain.zone.NeutralZone
import com.caminaseguro.domain.zone.SafeZone
import com.caminaseguro.domain.zone.ZoneSafety
import com.caminaseguro.presentation.DISABLED_BUTTON_CONTAINER_COLOR
import com.caminaseguro.presentation.DISABLED_BUTTON_CONTENT_COLOR
import com.caminaseguro.presentation.GREEN_COLOR
import com.caminaseguro.presentation.RED_COLOR
import com.caminaseguro.presentation.SMOOTH_BLACK
import com.caminaseguro.presentation.SMOOTH_WHITE
import com.caminaseguro.presentation.YELLOW_COLOR
import com.caminaseguro.presentation.buttons.DefaultTextButton

@Composable
fun EditZoneBox(
    viewModel: MapViewModel,
) {

    AlertDialog(
        onDismissRequest = { viewModel.setShowEditZoneBox(false) },
        title = {
            Text(
                text = "Editar seguridad de zona",
                color = SMOOTH_BLACK
            )
        },
        text = {
            Column() {
                DefaultTextButton(
                    onClick = {
                        viewModel.onEvent(MapEvent.ChangeSelectedZoneSafety(ZoneSafety.Indeterminate))
                    },
                    text = "Seguridad sin determinar",
                    //enabled = viewModel.state.selectedZone !is IndeterminateZone,
                    //elevated = viewModel.state.selectedZone is IndeterminateZone,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = DISABLED_BUTTON_CONTAINER_COLOR,
                    contentColor = DISABLED_BUTTON_CONTENT_COLOR,
                    disabledContainerColor = SMOOTH_BLACK,
                    disabledContentColor = SMOOTH_WHITE
                )
                DefaultTextButton(
                    onClick = {
                        viewModel.onEvent(MapEvent.ChangeSelectedZoneSafety(ZoneSafety.Safe))
                    },
                    text = "Zona segura",
                    //enabled = viewModel.state.selectedZone !is SafeZone,
                    //elevated = viewModel.state.selectedZone is SafeZone,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = DISABLED_BUTTON_CONTAINER_COLOR,
                    contentColor = DISABLED_BUTTON_CONTENT_COLOR,
                    disabledContainerColor = GREEN_COLOR,
                    disabledContentColor = SMOOTH_WHITE
                )
                DefaultTextButton(
                    onClick = {
                        viewModel.onEvent(MapEvent.ChangeSelectedZoneSafety(ZoneSafety.Neutral))
                    },
                    text = "Zona neutral",
                    //enabled = viewModel.state.selectedZone !is NeutralZone,
                    //elevated = viewModel.state.selectedZone is NeutralZone,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = DISABLED_BUTTON_CONTAINER_COLOR,
                    contentColor = DISABLED_BUTTON_CONTENT_COLOR,
                    disabledContainerColor = YELLOW_COLOR,
                    disabledContentColor = SMOOTH_WHITE
                )
                DefaultTextButton(
                    onClick = {
                        viewModel.onEvent(MapEvent.ChangeSelectedZoneSafety(ZoneSafety.Danger))
                    },
                    text = "Zona peligrosa",
                    //enabled = viewModel.state.selectedZone !is DangerZone,
                    //elevated = viewModel.state.selectedZone is DangerZone,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = DISABLED_BUTTON_CONTAINER_COLOR,
                    contentColor = DISABLED_BUTTON_CONTENT_COLOR,
                    disabledContainerColor = RED_COLOR,
                    disabledContentColor = SMOOTH_WHITE
                )
            }
        },
        confirmButton = {
            DefaultTextButton(
                onClick = { viewModel.setShowEditZoneBox(false) },
                text = "Listo",
                modifier = Modifier.fillMaxWidth()
            )
        },
        containerColor = SMOOTH_WHITE
    )
}