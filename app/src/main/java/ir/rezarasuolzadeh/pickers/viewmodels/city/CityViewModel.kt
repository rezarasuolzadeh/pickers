package ir.rezarasuolzadeh.pickers.viewmodels.city

import android.util.Log
import ir.rezarasuolzadeh.pickers.utils.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class CityViewModel : BaseViewModel<CityEvent>() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       defaults                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private var defaultCities = emptyList<String>()

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
            is CityEvent.SetDefaultCities -> {
                setDefaultCities(cityList = event.cities)
            }
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
     * initialize the default cities with default province's cities.
     */
    private fun setDefaultCities(cityList: List<String>) = CoroutineScope(Dispatchers.IO).launch {
        defaultCities = cityList
    }

    /**
     * initialize the provinces list with given province list.
     */
    private fun setProvinces(provinceList: List<String>) = CoroutineScope(Dispatchers.IO).launch {
        provinces.value = provinceList
    }

    /**
     * update the cities list according to given city list.
     */
    private fun setCities(cityList: List<String>) = CoroutineScope(Dispatchers.IO).launch {
        cities.value = defaultCities
        delay(timeMillis = 10)
        cities.value = cityList
    }

}