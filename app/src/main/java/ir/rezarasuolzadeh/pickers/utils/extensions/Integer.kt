package ir.rezarasuolzadeh.pickers.utils.extensions

/**
 * check the integer value, if null return 0 else return that's own value.
 */
fun Int?.orZero() = this ?: 0

/**
 * check the integer value, if null return 1 else return that's own value.
 */
fun Int?.orOne() = this ?: 1

/**
 * check the integer value to be a leap year or not, then return the result in boolean format.
 */
fun Int.isLeapYear() = this % 4 == 3