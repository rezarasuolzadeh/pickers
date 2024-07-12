package ir.rezarasuolzadeh.pickers.ui.viewmodel.time

import androidx.lifecycle.ViewModel
import ir.rezarasuolzadeh.pickers.ui.utility.enums.TimeType
import kotlinx.coroutines.flow.MutableStateFlow

class TimeViewModel : ViewModel() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       defaults                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private var defaultHours = (0..12).map { if (it < 10) "0$it" else "$it" }

    private var defaultMinutes = (0..59).map { if (it < 10) "0$it" else "$it" }

    private var defaultSeconds = (0..59).map { if (it < 10) "0$it" else "$it" }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       variables                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    var timeType = MutableStateFlow(value = TimeType.AM)
        private set

    var hours = MutableStateFlow(value = defaultHours)
        private set

    var minutes = MutableStateFlow(value = defaultMinutes)
        private set

    var seconds = MutableStateFlow(value = defaultSeconds)
        private set

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        configs                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

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
            is TimeEvent.SetInitTime -> {
                event.initialHour?.let { initialHour ->
                    hours.value = (initialHour..12).map { if (it < 10) "0$it" else "$it" } + (0..<initialHour).map { if (it < 10) "0$it" else "$it" }
                }
                event.initialMinute?.let { initialMinute ->
                    minutes.value = (initialMinute..59).map { if (it < 10) "0$it" else "$it" } + (0..<initialMinute).map { if (it < 10) "0$it" else "$it" }
                }
                event.initialSecond?.let { initialSecond ->
                    seconds.value = (initialSecond..59).map { if (it < 10) "0$it" else "$it" } + (0..<initialSecond).map { if (it < 10) "0$it" else "$it" }
                }
            }
        }
    }

}