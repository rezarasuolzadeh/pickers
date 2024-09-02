package ir.rezarasuolzadeh.pickers.viewmodels.time

import androidx.lifecycle.ViewModel
import ir.rezarasuolzadeh.pickers.utils.enums.TimeType
import kotlinx.coroutines.flow.MutableStateFlow

class TimeViewModel : ViewModel() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       defaults                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private var defaultHours12 = (0..12).map { if (it < 10) "0$it" else "$it" }

    private var defaultHours24 = (0..23).map { if (it < 10) "0$it" else "$it" }

    private var defaultMinutes = (0..59).map { if (it < 10) "0$it" else "$it" }

    private var defaultSeconds = (0..59).map { if (it < 10) "0$it" else "$it" }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       variables                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    var is12Hour = MutableStateFlow(value = false)
        private set

    var timeType = MutableStateFlow(value = TimeType.AM)
        private set

    var hours = MutableStateFlow(value = emptyList<String>())
        private set

    var minutes = MutableStateFlow(value = emptyList<String>())
        private set

    var seconds = MutableStateFlow(value = emptyList<String>())
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
                setTimeFormat(is12Hour = event.is12Hour)
            }
            is TimeEvent.SetTimeType -> {
                setTimeType(timeType = event.timeType)
            }
            is TimeEvent.SetInitialTime -> {
                setHour(hour = event.initialHour)
                setMinute(minute = event.initialMinute)
                setSecond(second = event.initialSecond)
                setTimeType(timeType = event.initialTimeType)
            }
        }
    }

    /**
     * set the time format with given value.
     */
    private fun setTimeFormat(is12Hour: Boolean) {
        this.is12Hour.value = is12Hour
    }

    /**
     * set the time type with given value.
     */
    private fun setTimeType(timeType: TimeType?) {
        timeType?.let { initialTimeType ->
            this.timeType.value = initialTimeType
        } ?: {
            this.timeType.value = TimeType.AM
        }
    }

    /**
     * prepare hours list according to given hour value.
     */
    private fun setHour(hour: Int?) {
        hour?.let { initialHour ->
            hours.value = (initialHour..if (is12Hour.value) 12 else 23).map { if (it < 10) "0$it" else "$it" } + (0..<initialHour).map { if (it < 10) "0$it" else "$it" }
        } ?: run {
            hours.value = if (is12Hour.value) defaultHours12 else defaultHours24
        }
    }

    /**
     * prepare minutes list according to given minute value.
     */
    private fun setMinute(minute: Int?) {
        minute?.let { initialMinute ->
            minutes.value = (initialMinute..59).map { if (it < 10) "0$it" else "$it" } + (0..<initialMinute).map { if (it < 10) "0$it" else "$it" }
        } ?: run {
            minutes.value = defaultMinutes
        }
    }

    /**
     * prepare seconds list according to given second value.
     */
    private fun setSecond(second: Int?) {
        second?.let { initialSecond ->
            seconds.value = (initialSecond..59).map { if (it < 10) "0$it" else "$it" } + (0..<initialSecond).map { if (it < 10) "0$it" else "$it" }
        } ?: {
            seconds.value = defaultSeconds
        }
    }

}