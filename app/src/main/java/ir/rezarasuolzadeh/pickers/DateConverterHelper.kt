package ir.rezarasuolzadeh.pickers

import java.text.SimpleDateFormat
import java.util.*

class DateConverterHelper
{
    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0
    private var jY: Int = 0
    private var jM: Int = 0
    private var jD: Int = 0
    private var gY: Int = 0
    private var gM: Int = 0
    private var gD: Int = 0
    private var leap: Int = 0
    private var march: Int = 0

    private fun JG2JD(year: Int, month: Int, day: Int, J1G0: Int): Int
    {
        var jd =
            1461 * (year + 4800 + (month - 14) / 12) / 4 + 367 * (month - 2 - 12 * ((month - 14) / 12)) / 12 - 3 * ((year + 4900 + (month - 14) / 12) / 100) / 4 + day - 32075
        if (J1G0 == 0)
            jd = jd - (year + 100100 + (month - 8) / 6) / 100 * 3 / 4 + 752
        return jd
    }

    private fun JD2JG(JD: Int, J1G0: Int)
    {
        val i: Int
        var j: Int
        j = 4 * JD + 139361631
        if (J1G0 == 0)
        {
            j = j + (4 * JD + 183187720) / 146097 * 3 / 4 * 4 - 3908
        }
        i = j % 1461 / 4 * 5 + 308
        gD = i % 153 / 5 + 1
        gM = i / 153 % 12 + 1
        gY = j / 1461 - 100100 + (8 - gM) / 6
    }

    private fun JD2Jal(JDN: Int)
    {
        JD2JG(JDN, 0)
        jY = gY - 621
        JalCal(jY)
        val JDN1F = JG2JD(gY, 3, march, 0)
        var k = JDN - JDN1F
        if (k >= 0)
        {
            if (k <= 185)
            {
                jM = 1 + k / 31
                jD = k % 31 + 1
                return
            } else
            {
                k = k - 186
            }
        } else
        {
            jY = jY - 1
            k = k + 179
            if (leap == 1)
                k = k + 1
        }
        jM = 7 + k / 30
        jD = k % 30 + 1
    }

    private fun Jal2JD(jY: Int, jM: Int, jD: Int): Int
    {
        JalCal(jY)
        return JG2JD(gY, 3, march, 1) + (jM - 1) * 31 - jM / 7 * (jM - 7) + jD - 1
    }

    private fun JalCal(jY: Int)
    {
        march = 0
        leap = 0
        val breaks = intArrayOf(
            - 61,
            9,
            38,
            199,
            426,
            686,
            756,
            818,
            1111,
            1181,
            1210,
            1635,
            2060,
            2097,
            2192,
            2262,
            2324,
            2394,
            2456,
            3178
        )
        gY = jY + 621
        var leapJ = - 14
        var jp = breaks[0]
        var jump: Int
        for (j in 1..19)
        {
            val jm = breaks[j]
            jump = jm - jp
            if (jY < jm)
            {
                var N = jY - jp
                leapJ = leapJ + N / 33 * 8 + (N % 33 + 3) / 4
                if (jump % 33 == 4 && jump - N == 4)
                    leapJ = leapJ + 1
                val leapG = gY / 4 - (gY / 100 + 1) * 3 / 4 - 150
                march = 20 + leapJ - leapG
                if (jump - N < 6)
                    N = N - jump + (jump + 4) / 33 * 33
                leap = ((N + 1) % 33 - 1) % 4
                if (leap == - 1)
                    leap = 4
                break
            }
            leapJ = leapJ + jump / 33 * 8 + jump % 33 / 4
            jp = jm
        }
    }

    override fun toString(): String
    {
        return String.format("%04d/%02d/%02d", getYear(), getMonth(), getDay())
    }

    fun GregorianToPersian(year: Int, month: Int, day: Int)
    {
        val jd = JG2JD(year, month, day, 0)
        JD2Jal(jd)
        this.year = jY
        this.month = jM
        this.day = jD
    }

    fun GregorianToPersian(year: String, month: String, day: String)
    {
        val jd = JG2JD(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day), 0)
        JD2Jal(jd)
        this.year = jY
        this.month = jM
        this.day = jD
    }

    fun PersianToGregorian(year: Int, month: Int, day: Int)
    {
        val jd = Jal2JD(year, month, day)
        JD2JG(jd, 0)
        this.year = gY
        this.month = gM
        this.day = gD
    }

    fun PersianToGregorianDateFormat(date: String, splitter: String = "/"): String
    {
        val dateSplit = date.split(splitter)
        PersianToGregorian(dateSplit[0].toInt(), dateSplit[1].toInt(), dateSplit[2].toInt())
        return toDateFormat(year, month, day)
    }

    fun GerigorianToPersianDateFormat(date: String, splitter: String = "/"): String
    {
        val dateSplit = date.split(splitter)
        GregorianToPersian(dateSplit[0].toInt(), dateSplit[1].toInt(), dateSplit[2].toInt())
        return toDateFormat(year, month, day)
    }

    fun getStringMonth(): String
    {
        when (getMonth())
        {
            1 -> return "فروردین"
            2 -> return "اردیبهشت"
            3 -> return "خرداد"
            4 -> return "تیر"
            5 -> return "مرداد"
            6 -> return "شهریور"
            7 -> return "مهر"
            8 -> return "آبان"
            9 -> return "آذر"
            10 -> return "دی"
            11 -> return "بهمن"
            12 -> return "اسفند"
            else -> return "..."
        }
    }

    fun getStringMonth(month: Int, persian: Boolean): String
    {
        return if (persian)
        {
            when (month)
            {
                1 -> "فروردین"
                2 -> "اردیبهشت"
                3 -> "خرداد"
                4 -> "تیر"
                5 -> "مرداد"
                6 -> "شهریور"
                7 -> "مهر"
                8 -> "آبان"
                9 -> "آذر"
                10 -> "دی"
                11 -> "بهمن"
                12 -> "اسفند"
                else -> "..."
            }
        } else
        {
            when (month)
            {
                1 -> "Jan"
                2 -> "Feb"
                3 -> "Mar"
                4 -> "Apr"
                5 -> "May"
                6 -> "Jun"
                7 -> "Jul"
                8 -> "Aug"
                9 -> "Sep"
                10 -> "Oct"
                11 -> "Nov"
                12 -> "Dec"
                else -> "..."
            }
        }
    }

    fun getDay(): Int
    {
        return day
    }

    fun getMonth(): Int
    {
        return month
    }

    fun getYear(): Int
    {
        return year
    }

    fun getStringFormatDay(): String {
        return if(day >= 10) day.toString() else "0$day"
    }

    fun getStringFormatMonth(): String {
        return if(month >= 10) month.toString() else "0$month"
    }

    fun getStringFormatYear(): String {
        return year.toString()
    }

    fun toAndroidDate(str: String): String
    {
        try
        {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
            val date = dateFormat.parse(str + "Z")
            val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
            return formatter.format(date)
        } catch (e: Exception)
        {
            return "2020/01/01"
        }

    }

    fun getPersianDay(enDay: String): String
    {
        when (enDay)
        {
            "Wednesday" -> return "چهارشنبه"
            "Thursday" -> return "پنج شنبه"
            "Friday" -> return "جمعه"
            "Saturday" -> return "شنبه"
            "Sunday" -> return "یکشنبه"
            "Monday" -> return "دوشنبه"
            "Tuesday" -> return "سه شنبه"
        }
        return enDay
    }

    fun getPersianTimeSince(magnitude: String, dimension: String): String
    {
//        if (!Utility.isRtl()) {
//            return if (magnitude == "0") {
//                "Right now!"
//            } else "$magnitude$dimension ago"
//        }
        var persianDimension = ""
        when (dimension)
        {
            "minute", "minutes" ->
            {
                if (magnitude == "0")
                {
                    return "همین الان!"
                }
                persianDimension = "دقیقه پیش"
            }
            "hour", "hours" -> persianDimension = "ساعت پیش"
            "day", "days" -> persianDimension = "روز پیش"
            "week", "weeks" -> persianDimension = "هفته پیش"
            "month", "months" -> persianDimension = "ماه پیش"
            "year", "years" -> persianDimension = "سال پیش"
        }
        return "$magnitude $persianDimension"
    }

    fun toDateFormat(year: Int, month: Int, day: Int): String
    {
        return "$year/$month/$day"
    }
}