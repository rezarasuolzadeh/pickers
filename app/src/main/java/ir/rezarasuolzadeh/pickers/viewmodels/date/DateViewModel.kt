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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       variables                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    var days = MutableStateFlow(value = defaultDays)
        private set

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        configs                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * handle the event that's given from composable function.
     */
    fun onEvent(event: DateEvent) {
        when (event) {
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
        }
    }

}