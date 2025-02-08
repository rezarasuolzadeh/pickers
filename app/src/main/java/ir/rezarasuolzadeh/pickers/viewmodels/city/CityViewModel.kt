package ir.rezarasuolzadeh.pickers.viewmodels.city

import android.util.Log
import ir.rezarasuolzadeh.pickers.utils.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class CityViewModel : BaseViewModel<CityEvent>() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       variables                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    var provinces = MutableStateFlow(value = emptyList<String>())
        private set

    var cities = MutableStateFlow(value = emptyList<String>())
        private set

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       overrides                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onEvent(event: CityEvent) {
        when (event) {
            is CityEvent.SetProvinces -> {
                setProvinces(provinceList = event.provinces)
            }
            is CityEvent.SetCities -> {
                setCities(cityList = event.cities)
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        helpers                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * set the years with given years range value.
     */
    private fun setProvinces(provinceList: List<String>) = CoroutineScope(Dispatchers.IO).launch {
        provinces.value = provinceList
    }

    /**
     * set selected day with given day.
     */
    private fun setCities(cityList: List<String>) {
        cities.value = cityList
    }

}