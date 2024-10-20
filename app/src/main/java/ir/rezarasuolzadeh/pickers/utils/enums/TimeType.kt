package ir.rezarasuolzadeh.pickers.utils.enums

enum class TimeType(
    var persianTitle: String,
    var englishTitle: String
) {
    AM(persianTitle = "ق.ظ", englishTitle = "AM"),
    PM(persianTitle = "ب.ظ", englishTitle = "PM")
}