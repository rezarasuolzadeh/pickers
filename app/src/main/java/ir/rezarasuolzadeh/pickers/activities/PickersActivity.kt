package ir.rezarasuolzadeh.pickers.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ir.rezarasuolzadeh.pickers.ui.dialog.DateDialog
import ir.rezarasuolzadeh.pickers.ui.dialog.TimeDialog
import ir.rezarasuolzadeh.pickers.ui.theme.PickersTheme
import ir.rezarasuolzadeh.pickers.ui.theme.White
import ir.rezarasuolzadeh.pickers.utils.enums.MonthType

class PickersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = White
                ) {
                    DateDialog(
                        initialYear = 1402,
                        initialMonth = MonthType.SHAHRIVAR,
                        yearRange = 1375..1403
                    )
//                    TimeDialog()
                }
            }
        }
    }
}