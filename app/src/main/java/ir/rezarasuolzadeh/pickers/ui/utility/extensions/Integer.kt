package ir.rezarasuolzadeh.pickers.ui.utility.extensions

/**
 * check the integer value, if null return 0 else return that's own value.
 */
fun Int?.orZero() = this ?: 0