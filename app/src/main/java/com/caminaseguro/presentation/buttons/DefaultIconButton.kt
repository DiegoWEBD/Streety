package com.caminaseguro.presentation.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.caminaseguro.presentation.BLUE_PRIMARY
import com.caminaseguro.presentation.DISABLED_BLUE_PRIMARY
import com.caminaseguro.presentation.GREY_SHADOW
import com.caminaseguro.presentation.SMOOTH_WHITE
import com.caminaseguro.shadow

@Composable
fun DefaultIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    elevated: Boolean = true,
    enabled: Boolean = true,
    backgroundColor: Color = BLUE_PRIMARY,
    contentColor: Color = SMOOTH_WHITE,
    disabledBackgroundColor: Color = DISABLED_BLUE_PRIMARY,
    disabledContentColor: Color = SMOOTH_WHITE
) {
    var selfModifier = modifier

    if(elevated) {
        selfModifier = selfModifier.shadow(
            color = GREY_SHADOW,
            borderRadius = 30.dp,
            blurRadius = 5.dp,
            spread = 1.dp,
            offsetX = 4.dp,
            offsetY = 7.dp
        )
    }

    IconButton(
        onClick = { onClick() },
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor
        ),
        modifier = selfModifier
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}