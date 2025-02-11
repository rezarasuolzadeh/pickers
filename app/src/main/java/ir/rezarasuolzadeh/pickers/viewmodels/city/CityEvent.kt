package ir.rezarasuolzadeh.pickers.viewmodels.city

internal sealed class CityEvent {
    data class SetDefaultCities(val cities: List<String>) : CityEvent()
    data class SetProvinces(val provinces: List<String>) : CityEvent()
    data class SetCities(val cities: List<String>) : CityEvent()
}