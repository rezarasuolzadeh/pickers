package ir.rezarasuolzadeh.pickers

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eniac.sorena.ui.util.picker.Picker
import com.eniac.sorena.ui.util.picker.rememberPickerState
import ir.rezarasuolzadeh.pickers.ui.theme.White

@Composable
fun DatePickerDialogCompose(
    onDateSelect: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = White)
    ) {
        val years = remember { (1380..1410).map { if (it < 10) "0$it" else "$it" } }
        val months = remember { listOf("فروردین", "اردیبهشت", "خرداد","تیر","مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند")}
        var days = (1..31).map { if (it < 10) "0$it" else "$it" }
        val yearPickerState = rememberPickerState()
        val monthPickerState = rememberPickerState()
        val dayPickerState = rememberPickerState()
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            text = "انتخاب تاریخ",
            color = Black,
            fontFamily = FontFamily(Font(R.font.vazir_num)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Picker(
                state = yearPickerState,
                items = years,
                visibleItemsCount = 3,
                modifier = Modifier
                    .padding(top = 25.dp)
                    .width(90.dp),
                textModifier = Modifier.padding(8.dp),
                textStyle = TextStyle(fontSize = 16.sp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = "/",
                color = Black,
                fontFamily = FontFamily(Font(R.font.vazir_num)),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Picker(
                state = monthPickerState,
                items = months,
                visibleItemsCount = 3,
                modifier = Modifier
                    .padding(top = 25.dp)
                    .width(90.dp),
                textModifier = Modifier.padding(8.dp),
                textStyle = TextStyle(fontSize = 16.sp),
                onItemChanged = {
                    Log.d("REZAAAAA", "${it}")
                    days = when(it) {
                        "فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور" -> {
                             (1..31).map { if (it < 10) "0$it" else "$it" }
                        }
                        "مهر", "آبان", "آذر", "دی", "بهمن" -> {
                            (1..30).map { if (it < 10) "0$it" else "$it" }
                        }
                        "اسفند" -> {
                            (1..29).map { if (it < 10) "0$it" else "$it" }
                        }
                        else -> {
                            (1..31).map { if (it < 10) "0$it" else "$it" }
                        }
                    }
                }
            )
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = "/",
                color = Black,
                fontFamily = FontFamily(Font(R.font.vazir_num)),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Picker(
                state = dayPickerState,
                items = days,
                visibleItemsCount = 3,
                modifier = Modifier
                    .padding(top = 25.dp)
                    .width(90.dp),
                textModifier = Modifier.padding(8.dp),
                textStyle = TextStyle(fontSize = 16.sp)
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth()
                .noRippleClickable {
                    onDateSelect("${yearPickerState.selectedItem}/${monthPickerState.selectedItem}/${dayPickerState.selectedItem}")
                },
            text = "ثبت",
            color = Black,
            fontFamily = FontFamily(Font(R.font.vazir_num)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(height = 10.dp))
    }
}

@Preview
@Composable
fun DatePickerDialogPreview() {
    DatePickerDialogCompose(
        onDateSelect = {
            // nothing to do yet
        }
    )
}