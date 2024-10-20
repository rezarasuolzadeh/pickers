package ir.rezarasuolzadeh.pickers.utils.extensions

import android.content.Context
import android.widget.Toast

/**
 * show toast message with short length in current context.
 */
internal fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}