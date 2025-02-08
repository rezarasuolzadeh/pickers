package ir.rezarasuolzadeh.pickers.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ir.rezarasuolzadeh.pickers.ui.bottomSheet.CityBottomSheet
import ir.rezarasuolzadeh.pickers.ui.bottomSheet.DateBottomSheet
import ir.rezarasuolzadeh.pickers.ui.bottomSheet.TimeBottomSheet
import ir.rezarasuolzadeh.pickers.ui.dialog.CityDialog
import ir.rezarasuolzadeh.pickers.ui.dialog.DateDialog
import ir.rezarasuolzadeh.pickers.ui.dialog.TimeDialog
import ir.rezarasuolzadeh.pickers.ui.theme.PickersTheme
import ir.rezarasuolzadeh.pickers.ui.theme.White
import ir.rezarasuolzadeh.pickers.utils.enums.MonthType
import ir.rezarasuolzadeh.pickers.utils.enums.TimeOutputType
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
                    val isTimeDialogVisible = remember { mutableStateOf(value = false) }
                    val isDateDialogVisible = remember { mutableStateOf(value = false) }
                    val isCityDialogVisible = remember { mutableStateOf(value = true) }
                    val isTimeBottomSheetVisible = remember { mutableStateOf(value = false) }
                    val isDateBottomSheetVisible = remember { mutableStateOf(value = false) }
                    val isCityBottomSheetVisible = remember { mutableStateOf(value = false) }

                    if (isTimeDialogVisible.value) {
                        ShowTimeDialog (
                            onCancel = {
                                isTimeDialogVisible.value = false
                            },
                            onTimeSelect = { selectedTime ->
                                Toast.makeText(this@PickersActivity, selectedTime, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }

                    if (isDateDialogVisible.value) {
                        ShowDateDialog(
                            onCancel = {
                                isDateDialogVisible.value = false
                            },
                            onDateSelect = { selectedDate ->
                                Toast.makeText(this@PickersActivity, selectedDate, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }

                    if (isCityDialogVisible.value) {
                        ShowCityDialog(
                            onCancel = {
                                isDateDialogVisible.value = false
                            },
                            onCitySelect = { selectedCity ->
                                Toast.makeText(this@PickersActivity, selectedCity, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }

                    if (isTimeBottomSheetVisible.value) {
                        ShowTimeBottomSheet (
                            onCancel = {
                                isTimeBottomSheetVisible.value = false
                            },
                            onTimeSelect = { selectedTime ->
                                Toast.makeText(this@PickersActivity, selectedTime, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }

                    if (isDateBottomSheetVisible.value) {
                        ShowDateBottomSheet(
                            onCancel = {
                                isDateBottomSheetVisible.value = false
                            },
                            onDateSelect = { selectedDate ->
                                Toast.makeText(this@PickersActivity, selectedDate, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }

                    if (isCityBottomSheetVisible.value) {
                        ShowCityBottomSheet(
                            onCancel = {
                                isCityBottomSheetVisible.value = false
                            },
                            onCitySelect = { selectedCity ->
                                Toast.makeText(this@PickersActivity, selectedCity, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }

    /**
     * show the time picker dialog with it's parameters.
     */
    @Composable
    private fun ShowTimeDialog(
        onCancel: () -> Unit,
        onTimeSelect: (String) -> Unit
    ) {
        TimeDialog(
            initialHour = 2,
            initialMinute = 13,
            initialSecond = 7,
            initialTimeType = TimeType.PM,
            outputType = TimeOutputType.ENGLISH,
            is12Hour = true,
            showSeconds = true,
            onCancel = onCancel,
            onTimeSelect = onTimeSelect
        )
    }

    /**
     * show the date picker dialog with it's parameters.
     */
    @Composable
    private fun ShowDateDialog(
        onCancel: () -> Unit,
        onDateSelect: (String) -> Unit
    ) {
        DateDialog(
            initialYear = 1401,
            initialMonth = MonthType.ABAN,
            initialDay = 23,
            yearRange = 1375..1403,
            onCancel = onCancel,
            onDateSelect = onDateSelect
        )
    }

    /**
     * show the city picker dialog with it's parameters.
     */
    @Composable
    private fun ShowCityDialog(
        onCancel: () -> Unit,
        onCitySelect: (String) -> Unit
    ) {
        CityDialog(
            onCancel = onCancel,
            onCitySelect = onCitySelect
        )
    }


    /**
     * show the time picker bottom sheet with it's parameters.
     */
    @Composable
    private fun ShowTimeBottomSheet(
        onCancel: () -> Unit,
        onTimeSelect: (String) -> Unit
    ) {
        TimeBottomSheet(
            initialHour = 2,
            initialMinute = 13,
            initialSecond = 7,
            initialTimeType = TimeType.PM,
            outputType = TimeOutputType.ENGLISH,
            is12Hour = true,
            showSeconds = true,
            onCancel = onCancel,
            onTimeSelect = onTimeSelect
        )
    }

    /**
     * show the date picker bottom sheet with it's parameters.
     */
    @Composable
    private fun ShowDateBottomSheet(
        onCancel: () -> Unit,
        onDateSelect: (String) -> Unit
    ) {
        DateBottomSheet(
            initialYear = 1401,
            initialMonth = MonthType.ABAN,
            initialDay = 23,
            yearRange = 1375..1403,
            onCancel = onCancel,
            onDateSelect = onDateSelect
        )
    }

    /**
     * show the city picker bottom sheet with it's parameters.
     */
    @Composable
    private fun ShowCityBottomSheet(
        onCancel: () -> Unit,
        onCitySelect: (String) -> Unit
    ) {
        CityBottomSheet(
            onCancel = onCancel,
            onCitySelect = onCitySelect
        )
    }

}