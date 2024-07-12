package ir.rezarasuolzadeh.pickers.viewmodels.time

import androidx.lifecycle.ViewModel
import ir.rezarasuolzadeh.pickers.utils.enums.TimeType
import kotlinx.coroutines.flow.MutableStateFlow

class TimeViewModel : ViewModel() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       defaults                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private var defaultHours24 = (0..23).map { if (it < 10) "0$it" else "$it" }

    private var defaultHours12 = (0..12).map { if (it < 10) "0$it" else "$it" }

    private var defaultMinutes = (0..59).map { if (it < 10) "0$it" else "$it" }

    private var defaultSeconds = (0..59).map { if (it < 10) "0$it" else "$it" }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       variables                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    var is12Hour = MutableStateFlow(value = false)
        private set

    var timeType = MutableStateFlow(value = TimeType.AM)
        private set

    var hours = MutableStateFlow(value = defaultHours24)
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
            is TimeEvent.SetTimeFormat -> {
                is12Hour.value = event.is12Hour
            }
            is TimeEvent.SetTimeType -> {
                timeType.value = event.timeType
            }
            is TimeEvent.SetInitialTime -> {
                hours.value = if (is12Hour.value) defaultHours12 else defaultHours24
                event.initialHour?.let { initialHour ->
                    hours.value = (initialHour..if (is12Hour.value) 12 else 23).map { if (it < 10) "0$it" else "$it" } + (0..<initialHour).map { if (it < 10) "0$it" else "$it" }
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