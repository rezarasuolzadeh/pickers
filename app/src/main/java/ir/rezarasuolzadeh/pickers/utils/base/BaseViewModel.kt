package ir.rezarasuolzadeh.pickers.utils.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {

    /**
     * handle the all events that's send from composable.
     */
    abstract fun onEvent(event: T)

}