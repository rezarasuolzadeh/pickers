package ir.rezarasuolzadeh.pickers.utils.extensions

/**
 * check the integer value, if null return 0 else return that's own value.
 */
internal fun Int?.orZero() = this ?: 0

/**
 * check the integer value, if null return 1 else return that's own value.
 */
internal fun Int?.orOne() = this ?: 1

/**
 * check the integer value to be a leap year or not, then return the result in boolean format.
 */
internal fun Int.isLeapYear() = this % 4 == 3