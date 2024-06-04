package ir.rezarasuolzadeh.pickers.ui.viewmodel.time

sealed class TimeEvent {
    data object UpdateCurrentTime: TimeEvent()
}