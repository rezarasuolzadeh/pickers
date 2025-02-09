package ir.rezarasuolzadeh.pickers.ui.compose.city

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.rezarasuolzadeh.pickers.R
import ir.rezarasuolzadeh.pickers.ui.compose.picker.Picker
import ir.rezarasuolzadeh.pickers.ui.compose.picker.PickerState
import ir.rezarasuolzadeh.pickers.ui.compose.picker.rememberPickerState
import ir.rezarasuolzadeh.pickers.ui.theme.DarkBlue
import ir.rezarasuolzadeh.pickers.ui.theme.LightBlue
import ir.rezarasuolzadeh.pickers.ui.theme.White
import ir.rezarasuolzadeh.pickers.utils.extensions.noRippleClickable
import ir.rezarasuolzadeh.pickers.utils.managers.DatabaseManager
import ir.rezarasuolzadeh.pickers.viewmodels.city.CityEvent
import ir.rezarasuolzadeh.pickers.viewmodels.city.CityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

////////////////////////////////////////////////////////////////////////////////////////////////////
//                                            screen                                              //
////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
internal fun CityPickerDialogCompose(
    title: String?,
    confirmTitle: String?,
    cancelTitle: String?,
    fontFamily: FontFamily?,
    titleColor: Color?,
    confirmColor: Color?,
    cancelColor: Color?,
    dividerColor: Color?,
    backgroundColor: Color?,
    backgroundBrush: Brush?,
    outputSeparator: Char?,
    onCitySelect: (String) -> Unit,
    onCancel: () -> Unit,
    cityViewModel: CityViewModel = viewModel()
) {
    val context = LocalContext.current
    val provinces by cityViewModel.provinces.collectAsStateWithLifecycle()
    val cities by cityViewModel.cities.collectAsStateWithLifecycle()
    val provincePickerState = rememberPickerState()
    val cityPickerState = rememberPickerState()

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            cityViewModel.onEvent(
                event = CityEvent.SetProvinces(
                    provinces = DatabaseManager(context = context).getProvinces()
                )
            )
        }
    }

    CityPickerDialogComposeContent(
        title = title,
        confirmTitle = confirmTitle,
        cancelTitle = cancelTitle,
        fontFamily = fontFamily,
        titleColor = titleColor,
        confirmColor = confirmColor,
        cancelColor = cancelColor,
        dividerColor = dividerColor,
        backgroundColor = backgroundColor,
        backgroundBrush = backgroundBrush,
        outputSeparator = outputSeparator,
        onCitySelect = onCitySelect,
        provinces = provinces,
        cities = cities,
        provincePickerState = provincePickerState,
        cityPickerState = cityPickerState,
        onProvinceChanged = { provinceTitle ->
            CoroutineScope(context = Dispatchers.IO).launch {
                val deferred = CoroutineScope(context = Dispatchers.IO).async {
                    val cityList: List<String>
                    val databaseManager = DatabaseManager(context = context)
                    val provinceId = databaseManager.getProvinceId(provinceTitle = provinceTitle)
                    cityList = databaseManager.getProvinceCities(provinceId = provinceId)
                    cityList
                }
                deferred.await().let {
                    cityViewModel.onEvent(
                        event = CityEvent.SetCities(
                            cities = deferred.await()
                        )
                    )
                }
            }
        },
        onCityChanged = {
            // nothing to do yet
        },
        onCancel = onCancel
    )
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           content                                              //
////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
internal fun CityPickerDialogComposeContent(
    title: String?,
    confirmTitle: String?,
    cancelTitle: String?,
    fontFamily: FontFamily?,
    titleColor: Color?,
    confirmColor: Color?,
    cancelColor: Color?,
    dividerColor: Color?,
    backgroundColor: Color?,
    backgroundBrush: Brush?,
    outputSeparator: Char?,
    provinces: List<String>,
    cities: List<String>,
    provincePickerState: PickerState,
    cityPickerState: PickerState,
    onProvinceChanged: (String) -> Unit,
    onCityChanged: (String) -> Unit,
    onCitySelect: (String) -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = when {
            backgroundBrush == null && backgroundColor == null -> {
                Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 15.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                LightBlue,
                                DarkBlue
                            )
                        )
                    )
            }
            backgroundBrush != null && backgroundColor == null -> {
                Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 15.dp))
                    .background(brush = backgroundBrush)
            }
            backgroundBrush == null && backgroundColor != null -> {
                Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 15.dp))
                    .background(color = backgroundColor)
            }
            backgroundBrush != null && backgroundColor != null -> {
                Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 15.dp))
                    .background(color = backgroundColor)
            }
            else -> Modifier
        }
    ) {
        Text(
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth(),
            text = title ?: "انتخاب شهر",
            color = titleColor ?: White,
            fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (provinces.isNotEmpty()) {
                Picker(
                    state = provincePickerState,
                    items = provinces,
                    visibleItemsCount = 3,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .width(width = 160.dp),
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    itemColor = White,
                    textModifier = Modifier.padding(all = 8.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    onItemChanged = onProvinceChanged
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .width(width = 10.dp),
                text = "-",
                color = White,
                fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            if (cities.isNotEmpty()) {
                Picker(
                    state = cityPickerState,
                    items = cities,
                    visibleItemsCount = 3,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .width(width = 160.dp),
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    itemColor = White,
                    textModifier = Modifier.padding(all = 8.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    onItemChanged = onCityChanged
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 25.dp)
                .height(height = 0.5.dp)
                .fillMaxWidth()
                .background(color = dividerColor ?: White)
        )
        ConstraintLayout(
            modifier = Modifier
                .height(height = 50.dp)
                .fillMaxWidth()
        ) {
            val (confirm, cancel, divider) = createRefs()
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .height(height = 50.dp)
                    .width(width = 0.5.dp)
                    .background(color = dividerColor ?: White)
                    .constrainAs(ref = divider) {
                        start.linkTo(anchor = parent.start)
                        end.linkTo(anchor = parent.end)
                        bottom.linkTo(anchor = parent.bottom)
                        top.linkTo(anchor = parent.top)
                    }
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .height(height = 50.dp)
                    .constrainAs(ref = confirm) {
                        start.linkTo(anchor = parent.start)
                        end.linkTo(anchor = cancel.start)
                        width = Dimension.fillToConstraints
                        bottom.linkTo(anchor = parent.bottom)
                        top.linkTo(anchor = parent.top)
                    }
                    .noRippleClickable {
                        onCancel()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = cancelTitle ?: "انصراف",
                    color = cancelColor ?: White,
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 50.dp)
                    .constrainAs(ref = cancel) {
                        start.linkTo(anchor = confirm.end)
                        end.linkTo(anchor = parent.end)
                        width = Dimension.fillToConstraints
                        bottom.linkTo(anchor = parent.bottom)
                        top.linkTo(anchor = parent.top)
                    }
                    .noRippleClickable {
                        onCitySelect("${provincePickerState.selectedItem}${outputSeparator ?: "-"}${cityPickerState.selectedItem}")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = confirmTitle ?: "ثبت",
                    color = confirmColor ?: White,
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           preview                                              //
////////////////////////////////////////////////////////////////////////////////////////////////////

@Preview
@Composable
internal fun CityPickerDialogPreview() {
    CityPickerDialogCompose(
        title = null,
        confirmTitle = null,
        cancelTitle = null,
        fontFamily = null,
        titleColor = null,
        confirmColor = null,
        cancelColor = null,
        dividerColor = null,
        backgroundColor = null,
        backgroundBrush = null,
        outputSeparator = null,
        onCitySelect = {
            // nothing to do yet
        },
        onCancel = {
            // nothing to do yet
        },
        cityViewModel = CityViewModel()
    )
}