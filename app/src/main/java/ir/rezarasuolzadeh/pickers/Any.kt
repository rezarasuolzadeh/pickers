package ir.rezarasuolzadeh.pickers

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

/**
 * convert the pixel to dp then return the result.
 */
@Composable
fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }