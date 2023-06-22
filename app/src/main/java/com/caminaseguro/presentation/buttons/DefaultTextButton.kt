package com.caminaseguro.presentation.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caminaseguro.presentation.BLUE_PRIMARY
import com.caminaseguro.presentation.DISABLED_BUTTON_CONTAINER_COLOR
import com.caminaseguro.presentation.DISABLED_BUTTON_CONTENT_COLOR
import com.caminaseguro.presentation.GREY_SHADOW
import com.caminaseguro.presentation.SMOOTH_WHITE
import com.caminaseguro.shadow

@Composable
fun DefaultTextButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    elevated: Boolean = false,
    enabled: Boolean = true,
    containerColor: Color = BLUE_PRIMARY,
    contentColor: Color = SMOOTH_WHITE,
    disabledContainerColor: Color = DISABLED_BUTTON_CONTAINER_COLOR,
    disabledContentColor: Color = DISABLED_BUTTON_CONTENT_COLOR
) {
    val modifierWithShadow = modifier.shadow(
        color = GREY_SHADOW,
        borderRadius = 30.dp,
        blurRadius = 3.dp,
        spread = 1.dp,
        offsetX = 4.dp,
        offsetY = 6.dp
    )

    Button(
        onClick = { onClick() },
        enabled = enabled,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        contentPadding = PaddingValues(horizontal = 10.dp),
        modifier = if(elevated) modifierWithShadow else modifier,

    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}