package ir.rezarasuolzadeh.pickers.utils.enums

enum class MonthType(val title: String) {
    FARVARDIN(title = "فروردین"),
    ORDIBEHESHT(title = "اردیبهشت"),
    KHORDAD(title = "خرداد"),
    TIR(title = "تیر"),
    MORDAD(title = "مرداد"),
    SHAHRIVAR(title = "شهریور"),
    MEHR(title = "مهر"),
    ABAN(title = "آبان"),
    AZAR(title = "آذر"),
    DEY(title = "دی"),
    BAHMAN(title = "بهمن"),
    ESFAND(title = "اسفند");

    val index: Int get() = ordinal + 1
}