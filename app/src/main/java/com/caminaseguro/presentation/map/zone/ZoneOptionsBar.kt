package com.caminaseguro.presentation.map.zone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.caminaseguro.presentation.buttons.DefaultIconButton
import com.caminaseguro.presentation.buttons.DefaultTextButton
import com.caminaseguro.presentation.map.EditZoneBox
import com.caminaseguro.presentation.map.MapEvent
import com.caminaseguro.presentation.map.MapViewModel

@Composable
fun ZoneOptionsBar(
    viewModel: MapViewModel,
    padding: PaddingValues
) {
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp + padding.calculateTopPadding(), end = 10.dp)
    ) {

        if(viewModel.state.selectedZoneId != null){
            DefaultIconButton(
                onClick = { viewModel.state = viewModel.state.copy(showEditZoneBox = true) },
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar"
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(10.dp)
            )
            DefaultIconButton(
                onClick = { viewModel.onEvent(MapEvent.DeleteZone(viewModel.state.selectedZoneId!!)) },
                backgroundColor = Color.Red,
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar"
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(10.dp)
            )
            DefaultIconButton(
                onClick = {  },
                imageVector = Icons.Default.Check,
                contentDescription = "Contains"
            )
        }
        else if(viewModel.state.markers.size >= 3){
            DefaultTextButton(
                onClick = { viewModel.onEvent(MapEvent.CreateZone) },
                text = "Zona",
                elevated = true
            )
        }
        if(viewModel.state.showEditZoneBox) {
            EditZoneBox(
                viewModel = viewModel,
            )
        }
    }
}