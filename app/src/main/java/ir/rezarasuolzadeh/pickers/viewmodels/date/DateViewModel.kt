package ir.rezarasuolzadeh.pickers.viewmodels.date

import ir.rezarasuolzadeh.pickers.utils.base.BaseViewModel
import ir.rezarasuolzadeh.pickers.utils.enums.MonthType
import ir.rezarasuolzadeh.pickers.utils.extensions.isLeapYear
import ir.rezarasuolzadeh.pickers.utils.extensions.orFarvardin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class DateViewModel : BaseViewModel<DateEvent>() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       defaults                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private var defaultMonths = listOf(MonthType.FARVARDIN.title, MonthType.ORDIBEHESHT.title, MonthType.KHORDAD.title, MonthType.TIR.title, MonthType.MORDAD.title, MonthType.SHAHRIVAR.title, MonthType.MEHR.title, MonthType.ABAN.title, MonthType.AZAR.title, MonthType.DEY.title, MonthType.BAHMAN.title, MonthType.ESFAND.title)

    private var currentSelectedDay: Int = 1

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       variables                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    var days = MutableStateFlow(value = emptyList<String>())
        private set

    var months = MutableStateFlow(value = emptyList<String>())
        private set

    var years = MutableStateFlow(value = emptyList<String>())
        private set

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       overrides                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * handle the event that's given from composable function.
     */
    override fun onEvent(event: DateEvent) {
        when (event) {
            is DateEvent.SetYearRange -> {
                setYears(yearRange = event.yearRange)
            }
            is DateEvent.UpdateDays -> {
                setDay(month = event.selectedMonth)
            }
            is DateEvent.CheckLeapYear -> {
                checkLeapYear(
                    year = event.year,
                    month = event.month
                )
            }
            is DateEvent.UpdateSelectedDay -> {
                setSelectedDay(day = event.selectedDay)
            }
            is DateEvent.SetInitialDate -> {
                setDate(
                    year = event.initialYear,
                    month = event.initialMonth,
                    day = event.initialDay,
                    yearRange = event.yearRange
                )
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        helpers                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * set the years with given years range value.
     */
    private fun setYears(yearRange: IntRange) {
        years.value = yearRange.map { if (it < 10) "0$it" else "$it"  }
    }

    /**
     * set selected day with given day.
     */
    private fun setSelectedDay(day: Int) {
        currentSelectedDay = day
    }

    /**
     * check the leap year situation then update days.
     */
    private fun checkLeapYear(year: String, month: String) {
        if (year.toInt().isLeapYear()) {
            if (month == MonthType.ESFAND.title) {
                days.value = emptyList()
                days.value = (1..30).map { if (it < 10) "0$it" else "$it" }
            }
        } else {
            if (month == MonthType.ESFAND.title) {
                days.value = emptyList()
                days.value = (1..29).map { if (it < 10) "0$it" else "$it" }
            }
        }
    }

    /**
     * set the days according to the given month.
     */
    private fun setDay(month: String) = CoroutineScope(Dispatchers.Main).launch {
        days.value = (currentSelectedDay..32).map { if (it < 10) "0$it" else "$it" } + (1..<currentSelectedDay).map { if (it < 10) "0$it" else "$it" }
        delay(timeMillis = 30)
        days.value = when (month) {
            MonthType.FARVARDIN.title,
            MonthType.ORDIBEHESHT.title,
            MonthType.KHORDAD.title,
            MonthType.TIR.title,
            MonthType.MORDAD.title,
            MonthType.SHAHRIVAR.title -> {
                (currentSelectedDay..31).map { if (it < 10) "0$it" else "$it" } + (1..<currentSelectedDay).map { if (it < 10) "0$it" else "$it" }
            }

            MonthType.MEHR.title,
            MonthType.ABAN.title,
            MonthType.AZAR.title,
            MonthType.DEY.title,
            MonthType.BAHMAN.title -> {
                (currentSelectedDay..30).map { if (it < 10) "0$it" else "$it" } + (1..<currentSelectedDay).map { if (it < 10) "0$it" else "$it" }
            }

            MonthType.ESFAND.title -> {
                (currentSelectedDay..30).map { if (it < 10) "0$it" else "$it" } + (1..<currentSelectedDay).map { if (it < 10) "0$it" else "$it" }
            }

            else -> {
                (currentSelectedDay..29).map { if (it < 10) "0$it" else "$it" } + (1..<currentSelectedDay).map { if (it < 10) "0$it" else "$it" }
            }
        }
    }

    /**
     * set the year, month and day with given values.
     */
    private fun setDate(year: Int?, month: MonthType?, day: Int?, yearRange: IntRange) {
        year?.let { initialYear ->
            years.value = (initialYear..yearRange.last).map { if (it < 10) "0$it" else "$it" } + (yearRange.first..<initialYear).map { if (it < 10) "0$it" else "$it" }
        }
        month?.let { initialMonth ->
            months.value = defaultMonths.subList(fromIndex = defaultMonths.indexOfFirst { it == initialMonth.title }, toIndex = 12) + defaultMonths.subList(fromIndex = 0, toIndex = defaultMonths.indexOfFirst { it == initialMonth.title })
        } ?: run {
            months.value = defaultMonths
        }
        day?.let { initialDay ->
            currentSelectedDay = initialDay
            when (month.orFarvardin()) {
                MonthType.FARVARDIN,
                MonthType.ORDIBEHESHT,
                MonthType.KHORDAD,
                MonthType.TIR,
                MonthType.MORDAD,
                MonthType.SHAHRIVAR -> {
                    days.value = emptyList()
                    days.value = (initialDay..31).map { if (it < 10) "0$it" else "$it" } + (1..<initialDay).map { if (it < 10) "0$it" else "$it" }
                }
                MonthType.MEHR,
                MonthType.ABAN,
                MonthType.AZAR,
                MonthType.DEY,
                MonthType.BAHMAN -> {
                    days.value = (initialDay..30).map { if (it < 10) "0$it" else "$it" } + (1..<initialDay).map { if (it < 10) "0$it" else "$it" }
                }
                MonthType.ESFAND -> {
                    if((year ?: yearRange.first).isLeapYear()) {
                        days.value = emptyList()
                        days.value = (initialDay..30).map { if (it < 10) "0$it" else "$it" } + (1..<initialDay).map { if (it < 10) "0$it" else "$it" }
                    } else {
                        days.value = emptyList()
                        days.value = (initialDay..29).map { if (it < 10) "0$it" else "$it" } + (1..<initialDay).map { if (it < 10) "0$it" else "$it" }
                    }
                }
            }
        }
    }

}