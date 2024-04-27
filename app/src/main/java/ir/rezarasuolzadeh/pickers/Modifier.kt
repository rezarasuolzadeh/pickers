package ir.rezarasuolzadeh.pickers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer

/**
 * set fading for edge of component with set on it's modifier.
 */
fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(
        compositingStrategy = CompositingStrategy.Offscreen
    )
    .drawWithContent {
        drawContent()
        drawRect(
            brush = brush,
            blendMode = BlendMode.DstIn
        )
    }