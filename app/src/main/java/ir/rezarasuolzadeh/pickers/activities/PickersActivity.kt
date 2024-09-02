package ir.rezarasuolzadeh.pickers.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ir.rezarasuolzadeh.pickers.ui.dialog.TimeDialog
import ir.rezarasuolzadeh.pickers.ui.theme.PickersTheme
import ir.rezarasuolzadeh.pickers.ui.theme.White
import ir.rezarasuolzadeh.pickers.utils.enums.TimeType

class PickersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = White
                ) {
//                    DateDialog(
//                        initialYear = 1401,
//                        initialMonth = MonthType.ABAN,
//                        initialDay = 23,
//                        yearRange = 1375..1403
//                    )
                    TimeDialog(
                        initialHour = 2,
                        initialMinute = 13,
                        initialSecond = 7,
                        initialTimeType = TimeType.PM,
                        is12Hour = true,
                        showSeconds = true
                    )
                }
            }
        }
    }
}