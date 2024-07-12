package ir.rezarasuolzadeh.pickers.utils.managers

object ValidationManager {

    /**
     * check the time all sections validation and return the result in boolean format.
     */
    fun isTimeValid(second: Int, minute: Int, hour: Int, is12Hour: Boolean) = isSecondValid(second = second) && isMinuteValid(minute = minute) && isHourValid(hour = hour, is12Hour = is12Hour)

    /**
     * check the given second and if it's in valid range return true else return false.
     */
    fun isSecondValid(second: Int): Boolean = second in 0..59

    /**
     * check the given minute and if it's in valid range return true else return false.
     */
    fun isMinuteValid(minute: Int): Boolean = minute in 0..59

    /**
     * check the given hour and if it's in valid range return true else return false.
     */
    fun isHourValid(hour: Int, is12Hour: Boolean): Boolean = if(is12Hour) {
        hour in 0..12
    } else {
        hour in 0..23
    }

}