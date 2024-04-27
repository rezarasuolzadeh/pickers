package ir.rezarasuolzadeh.pickers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.eniac.sorena.ui.util.picker.Picker
import com.eniac.sorena.ui.util.picker.rememberPickerState
import ir.rezarasuolzadeh.pickers.ui.theme.White

@Composable
fun TimePickerDialogCompose(
    onTimeSelect: (String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 355.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = White)
    ) {
        val (title, hourPicker, colon, minutePicker, button) = createRefs()
        val hours = remember { (0..23).map { if (it < 10) "0$it" else "$it" } }
        val minutes = remember { (0..59).map { if (it < 10) "0$it" else "$it" } }
        val hourPickerState = rememberPickerState()
        val minutePickerState = rememberPickerState()
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = "انتخاب زمان",
            color = Black,
            fontFamily = FontFamily(Font(R.font.vazir_num)),
            fontWeight = FontWeight.Bold
        )
        Picker(
            state = hourPickerState,
            items = hours,
            visibleItemsCount = 3,
            modifier = Modifier
                .padding(top = 25.dp)
                .width(90.dp)
                .constrainAs(hourPicker) {
                    end.linkTo(colon.start)
                    top.linkTo(title.bottom)
                },
            textModifier = Modifier.padding(8.dp),
            textStyle = TextStyle(fontSize = 40.sp)
        )
        Text(
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(colon) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(hourPicker.top)
                    bottom.linkTo(hourPicker.bottom)
                },
            text = ":",
            color = Black,
            fontFamily = FontFamily(Font(R.font.vazir_num)),
            fontWeight = FontWeight.ExtraBold
        )
        Picker(
            state = minutePickerState,
            items = minutes,
            visibleItemsCount = 3,
            modifier = Modifier
                .padding(top = 25.dp)
                .width(90.dp)
                .constrainAs(minutePicker) {
                    start.linkTo(colon.end)
                    top.linkTo(title.bottom)
                },
            textModifier = Modifier.padding(8.dp),
            textStyle = TextStyle(fontSize = 40.sp)
        )
    }
}

@Preview
@Composable
fun TimePickerDialogPreview() {
    TimePickerDialogCompose(
        onTimeSelect = {}
    )
}