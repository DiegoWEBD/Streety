package com.caminaseguro.presentation.custom_map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caminaseguro.presentation.BLUE_PRIMARY
import com.caminaseguro.presentation.SMOOTH_BLACK
import com.caminaseguro.presentation.SMOOTH_WHITE
import com.caminaseguro.shadow

@Composable
fun SearchBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(vertical = 35.dp, horizontal = 10.dp)
            .shadow(
                color = SMOOTH_BLACK,
                borderRadius = 50.dp,
                blurRadius = 6.dp,
                spread = 1.dp,
                offsetX = 5.dp,
                offsetY = 6.dp
            )
            .background(SMOOTH_WHITE, shape = RoundedCornerShape(50))
    ) {
        Text(
            text = "Camina Seguro",
            color = BLUE_PRIMARY,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
    }
}