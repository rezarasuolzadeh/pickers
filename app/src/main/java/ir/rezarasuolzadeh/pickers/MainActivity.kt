package ir.rezarasuolzadeh.pickers

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = White
                ) {
                    DateDialog()
                }
            }
        }
    }
}