package ir.rezarasuolzadeh.pickers.ui.utility.manager

object ValidationManager {

    fun isTimeValid(second: Int, minute: Int, hour: Int) = isSecondValid(second = second) && isMinuteValid(minute = minute) && isHourValid(hour = hour)

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
    fun isHourValid(hour: Int): Boolean = hour in 0..23

}