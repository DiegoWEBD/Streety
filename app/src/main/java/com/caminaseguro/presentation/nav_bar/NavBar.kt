package com.caminaseguro.presentation.nav_bar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.caminaseguro.presentation.GREY_SHADOW
import com.caminaseguro.presentation.SMOOTH_GREY
import com.caminaseguro.shadow

@Composable
fun NavBar() {
    NavigationBar(
        containerColor = SMOOTH_GREY,
        modifier = Modifier
            .shadow(
                color = GREY_SHADOW,
                blurRadius = 15.dp,
                spread = 5.dp,
                offsetY = 8.dp
            )
    ) {
        NavigationBarItem(
            onClick = { /*TODO*/ },
            selected = false,
            icon = { Text(text = "Mapa") },
        )
        NavigationBarItem(
            onClick = { /*TODO*/ },
            selected = false,
            icon = { Text(text = "Social") },
        )
        NavigationBarItem(
            onClick = { /*TODO*/ },
            selected = false,
            icon = { Text(text = "Alertas") },
        )
        NavigationBarItem(
            onClick = { /*TODO*/ },
            selected = false,
            icon = { Text(text = "Yo") },
        )
    }
}