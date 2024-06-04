package ir.rezarasuolzadeh.pickers.ui.viewmodel.time

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class TimeViewModel : ViewModel() {

    var defaultDays = (1..31).map { if (it < 10) "0$it" else "$it" }

    var days = MutableStateFlow(value = defaultDays)
        private set

    /**
     * handle the event that's given from composable function.
     */
    fun onEvent(event: TimeEvent) {
        when (event) {
            TimeEvent.UpdateCurrentTime -> {
                // nothing to do yet
            }
        }
    }

}