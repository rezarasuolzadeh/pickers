package ir.rezarasuolzadeh.pickers.ui.viewmodel.date

sealed class DateEvent {
    data class OnUpdateDays(val days: List<String>) : DateEvent()
    data class CheckLeapYear(val year: String, val month: String) : DateEvent()
}