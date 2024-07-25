package ir.rezarasuolzadeh.pickers.utils.extensions

import ir.rezarasuolzadeh.pickers.utils.enums.MonthType

/**
 * check the month type value, if null return FARVARDIN else return that's own value.
 */
fun MonthType?.orFarvardin() = this ?: MonthType.FARVARDIN