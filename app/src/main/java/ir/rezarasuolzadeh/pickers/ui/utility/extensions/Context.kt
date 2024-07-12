package ir.rezarasuolzadeh.pickers.ui.utility.extensions

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalDensity

/**
 * convert the pixel to dp then return the result.
 */
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}