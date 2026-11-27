package ir.rezarasuolzadeh.pickers.utils.enums

enum class PickerType(
    val label: String
) {
    TIME_DIALOG(label = "Time dialog"),
    TIME_BOTTOM_SHEET(label = "Time bottom sheet"),
    DATE_DIALOG(label = "Date dialog"),
    DATE_BOTTOM_SHEET(label = "Date bottom sheet")
}