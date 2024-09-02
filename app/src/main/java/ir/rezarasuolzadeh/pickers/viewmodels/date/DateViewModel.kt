package ir.rezarasuolzadeh.pickers.viewmodels.date

import androidx.lifecycle.ViewModel
import ir.rezarasuolzadeh.pickers.utils.enums.MonthType
import ir.rezarasuolzadeh.pickers.utils.extensions.isLeapYear
import ir.rezarasuolzadeh.pickers.utils.extensions.orFarvardin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DateViewModel : ViewModel() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       defaults                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private var defaultMonths = listOf("فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند")

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
    //                                        configs                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * handle the event that's given from composable function.
     */
    fun onEvent(event: DateEvent) {
        when (event) {
            is DateEvent.SetYearRange -> {
                years.value = event.yearRange.map { if (it < 10) "0$it" else "$it"  }
            }
            is DateEvent.OnUpdateDays -> {
                CoroutineScope(Dispatchers.Main).launch {
                    days.value = (currentSelectedDay..32).map { if (it < 10) "0$it" else "$it" } + (1..<currentSelectedDay).map { if (it < 10) "0$it" else "$it" }
                    delay(timeMillis = 30)
                    days.value = when (event.selectedMonth) {
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
            }
            is DateEvent.CheckLeapYear -> {
                if(event.year.toInt().isLeapYear()) {
                    if(event.month == MonthType.ESFAND.title) {
                        days.value = emptyList()
                        days.value = (1..30).map { if (it < 10) "0$it" else "$it" }
                    }
                } else {
                    if(event.month == MonthType.ESFAND.title) {
                        days.value = emptyList()
                        days.value = (1..29).map { if (it < 10) "0$it" else "$it" }
                    }
                }
            }
            is DateEvent.UpdateSelectedDay -> {
                currentSelectedDay = event.selectedDay
            }
            is DateEvent.SetInitialDate -> {
                event.initialYear?.let { initialYear ->
                    years.value = (initialYear..event.yearRange.last).map { if (it < 10) "0$it" else "$it" } + (event.yearRange.first..<initialYear).map { if (it < 10) "0$it" else "$it" }
                }
                event.initialMonth?.let { initialMonth ->
                    months.value = defaultMonths.subList(fromIndex = defaultMonths.indexOfFirst { it == initialMonth.title }, toIndex = 12) + defaultMonths.subList(fromIndex = 0, toIndex = defaultMonths.indexOfFirst { it == initialMonth.title })
                }
                event.initialDay?.let { initialDay ->
                    currentSelectedDay = initialDay
                    when (event.initialMonth.orFarvardin()) {
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
                            if((event.initialYear ?: event.yearRange.first).isLeapYear()) {
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
    }

}