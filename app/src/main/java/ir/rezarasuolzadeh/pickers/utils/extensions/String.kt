package ir.rezarasuolzadeh.pickers.utils.extensions

import ir.rezarasuolzadeh.pickers.utils.enums.MonthType

/**
 * convert the string month to the numeric one and return it in string format.
 */
fun String.toNumericMonth(): String = when (this) {
    MonthType.FARVARDIN.title -> "01"
    MonthType.ORDIBEHESHT.title -> "02"
    MonthType.KHORDAD.title -> "03"
    MonthType.TIR.title -> "04"
    MonthType.MORDAD.title -> "05"
    MonthType.SHAHRIVAR.title -> "06"
    MonthType.MEHR.title -> "07"
    MonthType.ABAN.title -> "08"
    MonthType.AZAR.title -> "09"
    MonthType.DEY.title -> "10"
    MonthType.BAHMAN.title -> "11"
    MonthType.ESFAND.title -> "12"
    else -> "-1"
}