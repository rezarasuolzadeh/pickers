package ir.rezarasuolzadeh.pickers.utils.managers

import ir.rezarasuolzadeh.pickers.utils.enums.MonthType
import ir.rezarasuolzadeh.pickers.utils.extensions.isLeapYear

object DateValidationManager {

    /**
     * check the date all sections validation and return the result in boolean format.
     */
    fun isDateValid(day: Int, month: MonthType, year: Int, yearRange: IntRange) = isYearValid(year = year, yearRange = yearRange) && isDayValid(day = day, month = month, year = year)

    /**
     * check the year value is in year period and it's be ok return true else return false.
     */
    fun isYearValid(year: Int, yearRange: IntRange): Boolean = year in yearRange

    /**
     * check the day validation on each month and it's valid return true else return false.
     */
    fun isDayValid(day: Int, month: MonthType, year: Int): Boolean = when (month) {
        MonthType.FARVARDIN,
        MonthType.ORDIBEHESHT,
        MonthType.KHORDAD,
        MonthType.TIR,
        MonthType.MORDAD,
        MonthType.SHAHRIVAR -> {
            day in 1..31
        }
        MonthType.MEHR,
        MonthType.ABAN,
        MonthType.AZAR,
        MonthType.DEY,
        MonthType.BAHMAN -> {
            day in 1..30
        }
        MonthType.ESFAND -> {
            if(year.isLeapYear()) {
                day in 1..30
            } else {
                day in 1..29
            }
        }
    }

}