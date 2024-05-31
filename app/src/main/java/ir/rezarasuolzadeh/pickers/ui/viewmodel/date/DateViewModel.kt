package ir.rezarasuolzadeh.pickers.ui.viewmodel.date

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DateViewModel : ViewModel() {

    var defaultDays = (1..31).map { if (it < 10) "0$it" else "$it" }

    var days = MutableStateFlow(value = defaultDays)
        private set

    /**
     * handle the event that's given from composable function.
     */
    fun onEvent(event: DateEvent) {
        when (event) {
            is DateEvent.OnUpdateDays -> {
                days.value = event.days
            }
            is DateEvent.CheckLeapYear -> {
                if(event.year.toInt() % 4 == 3) {
                    if(event.month == "اسفند") {
                        days.value = (1..30).map { if (it < 10) "0$it" else "$it" }
                    }
                } else {
                    if(event.month == "اسفند") {
                        days.value = (1..29).map { if (it < 10) "0$it" else "$it" }
                    }
                }
            }
        }
    }

}