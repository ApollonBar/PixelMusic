package com.kyant.pixelmusic.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TwoToneCard(
    color: Color,
    text: String,
    icon: ImageVector?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (MaterialTheme.colors.isLight) color else LocalContentColor.current
    Card(
        modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        RoundedCornerShape(16.dp),
        color.copy(if (MaterialTheme.colors.isLight) 0.05f else 0.2f),
        elevation = 0.dp
    ) {
        Row(
            Modifier
                .clickable {}
                .padding(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    it,
                    contentDescription,
                    tint = contentColor
                )
                Spacer(Modifier.preferredWidth(32.dp))
            }
            Text(
                text,
                color = contentColor,
                style = MaterialTheme.typography.h5
            )
        }
    }
}