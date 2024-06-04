package ir.rezarasuolzadeh.pickers.ui.viewmodel.time

import androidx.lifecycle.ViewModel
import ir.rezarasuolzadeh.pickers.ui.utility.enums.TimeType
import kotlinx.coroutines.flow.MutableStateFlow

class TimeViewModel : ViewModel() {

    var defaultDays = (1..31).map { if (it < 10) "0$it" else "$it" }

    var timeType = MutableStateFlow(value = TimeType.AM)
        private set

    /**
     * handle the event that's given from composable function.
     */
    fun onEvent(event: TimeEvent) {
        when (event) {
            is TimeEvent.UpdateCurrentTime -> {
                // nothing to do yet
            }
            is TimeEvent.SetTimeType -> {
                timeType.value = event.timeType
            }
        }
    }

}