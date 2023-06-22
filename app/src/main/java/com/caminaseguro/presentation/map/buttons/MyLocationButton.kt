package com.caminaseguro.presentation.map.buttons

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.caminaseguro.R
import com.caminaseguro.presentation.BLUE_PRIMARY
import com.caminaseguro.presentation.SMOOTH_BLACK

@Composable
fun MyLocationButton(
    centerCallback: () -> Unit,
    isCentered: Boolean = false
) {
    val iconPainter = painterResource(id = R.drawable.baseline_my_location_24)
    val contentColor = if(isCentered) BLUE_PRIMARY else SMOOTH_BLACK

    FloatingActionButton(
        onClick = { centerCallback() },
        containerColor = Color.White,
        contentColor = contentColor,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(4.dp)
    ) {
        Icon(painter = iconPainter, contentDescription = "Centrar")
    }
}