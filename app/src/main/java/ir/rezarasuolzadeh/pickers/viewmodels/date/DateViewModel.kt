package ir.rezarasuolzadeh.pickers.viewmodels.date

import androidx.lifecycle.ViewModel
import ir.rezarasuolzadeh.pickers.utils.enums.MonthType
import ir.rezarasuolzadeh.pickers.utils.extensions.isLeapYear
import kotlinx.coroutines.flow.MutableStateFlow

class DateViewModel : ViewModel() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       defaults                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    var defaultDays = (1..31).map { if (it < 10) "0$it" else "$it" }

    var defaultYears = (1380..1410).map { if (it < 10) "0$it" else "$it" }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       variables                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    var days = MutableStateFlow(value = defaultDays)
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
                days.value = event.days
            }
            is DateEvent.CheckLeapYear -> {
                if(event.year.toInt().isLeapYear()) {
                    if(event.month == MonthType.ESFAND.title) {
                        days.value = (1..30).map { if (it < 10) "0$it" else "$it" }
                    }
                } else {
                    if(event.month == MonthType.ESFAND.title) {
                        days.value = (1..29).map { if (it < 10) "0$it" else "$it" }
                    }
                }
            }
            is DateEvent.SetInitialDate -> {
                event.initialYear?.let { initialYear ->
                    years.value = (initialYear..event.yearRange.last).map { if (it < 10) "0$it" else "$it" } + (event.yearRange.first..<initialYear).map { if (it < 10) "0$it" else "$it" }
                }
                event.initialMonth?.let { initialMonth ->
//                    minutes.value = (initialYear..59).map { if (it < 10) "0$it" else "$it" } + (0..<initialMinute).map { if (it < 10) "0$it" else "$it" }
                }
                event.initialDay?.let { initialDay ->
//                    seconds.value = (initialSecond..59).map { if (it < 10) "0$it" else "$it" } + (0..<initialSecond).map { if (it < 10) "0$it" else "$it" }
                }
            }
        }
    }

}