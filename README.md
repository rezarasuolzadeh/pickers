[![](https://jitpack.io/v/rezarasuolzadeh/pickers.svg)](https://jitpack.io/#rezarasuolzadeh/pickers)

### درباره کتابخونه
تو این کتابخونه که با jetpack compose توسعه داده شده، شما میتونید به راحتی time picker ، date picker و city picker رو به صورت dialog و bottom sheet استفاده کنید. توی هر قسمت که تو ادامه بهش اشاره میشه، یک سری پارامترها برای هر تابع در نظر گرفته شده که به شما امکان شخصی سازی تا حد زیادی رو میده که با اپلیکیشن شما سازگاری داشته باشه. در حال حاضر توابع زیر قابل استفاده هستن:

<div style="text-align: right;">
    &nbsp;&nbsp;•&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;تابع انتخاب زمان (dialog)
</div>
<div style="text-align: right;">
    &nbsp;&nbsp;•&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;تابع انتخاب زمان (bottom sheet)
</div>
<div style="text-align: right;">
    &nbsp;&nbsp;•&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;تابع انتخاب تاریخ (dialog)
</div>
<div style="text-align: right;">
    &nbsp;&nbsp;•&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;تابع انتخاب تاریخ (bottom sheet)
</div>
<div style="text-align: right;">
    &nbsp;&nbsp;•&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;تابع انتخاب شهر و استان (dialog)
</div>
<div style="text-align: right;">
    &nbsp;&nbsp;•&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;تابع انتخاب شهر و استان (bottom sheet)
</div>
<br>&nbsp;<br>

### نحوه استفاده 
نحوه استفاده از کتابخونه هم خیلی سادس (البته من پیشفرضم اینه که gradle پروژتون kotlin dsl هست، برای حالتی که از groovy استفاده میکنید هم باید یه تغییرات کوچیکی بدین که خودتون بهتر از من میدونید😁)، اول وارد فایل setting.gradle.kts میشین و تیکه کد زیر رو تو قسمتی که مشخص شده اضافه میکنید:
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```
بعدشم وارد فایل build.gradle.kts مربوط به app میشین و تیکه کد زیر رو هم تو قسمتی که مشخص شده اضافه میکنید:

```kotlin
dependencies {
    implementation("com.github.rezarasuolzadeh:pickers:1.2.17")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0")
}
```
<br>&nbsp;<br>
## تابع انتخاب زمان (Dialog)
<p align="center">
    <img alt="Date Dialog" src="/shots/time_dialog.png"  width="400" height="300"> 
</p>

با فراخوانی تابع زیر، شما میتونید خروجی بالا رو (که به صورت Dialog هست) تو هر جایی از کد compose تون که بخواین داشته باشین:

```kotlin
TimeDialog(
    title = null,
    confirmTitle = null,
    cancelTitle = null,
    initialHour = null,
    initialMinute = null,
    initialSecond = null,
    initialTimeType = null,
    fontFamily = null,
    is12Hour = false,
    showSeconds = true,
    titleColor = null,
    hourColor = null,
    minuteColor = null,
    secondColor = null,
    timeTypeColor = null,
    colonColor = null,
    confirmColor = null,
    cancelColor = null,
    dividerColor = null,
    backgroundColor = null,
    backgroundBrush = null,
    outputType = null,
    outputSeparator = null,
    onCancel = {},
    onTimeSelect = {}
)
```
در این قسمت شما میتونید با استفاده از پارامترهای ورودی تابع (که اسمشم جوری گذاشتم که گویا باشه) استفاده کنید و تا جای ممکن دیالوگ رو برای خودتون شخصی سازی کنین. تو اینجا دو تا lambda function داریم که اولیش onCancel هست که وقتی روی دکمه انصراف (یا هر چیزی که اسمش رو خودتون توی cancelTitle بزارید) زده بشه فراخوانی میشه:
```kotlin
onCancel = {
    // dismiss the dialog or do something
}
```
دومیش هم onTimeSelect هست که وقتی روی دکمه ثبت (یا هر چیزی که اسمش رو خودتون توی confirmTitle بزارید) زده بشه فراخوانی میشه که مقدار selectedTime همون زمانی هست که انتخاب کردید:
```kotlin
onTimeSelect = { selectedTime ->
    // show the time or do something
}
```
<br>&nbsp;<br>

## تابع انتخاب زمان (BottomSheet)
<p align="center">
    <img alt="Date Dialog" src="/shots/time_bottom_sheet.png"  width="400" height="300"> 
</p>

با فراخوانی تابع زیر، شما میتونید خروجی بالا (که به صورت BottomSheet هست) رو تو هر جایی از کد compose تون که بخواین داشته باشین:

```kotlin
TimeBottomSheet(
    title = null,
    confirmTitle = null,
    cancelTitle = null,
    initialHour = null,
    initialMinute = null,
    initialSecond = null,
    initialTimeType = null,
    fontFamily = null,
    is12Hour = false,
    showSeconds = true,
    titleColor = null,
    hourColor = null,
    minuteColor = null,
    secondColor = null,
    timeTypeColor = null,
    colonColor = null,
    confirmColor = null,
    cancelColor = null,
    dividerColor = null,
    backgroundColor = null,
    backgroundBrush = null,
    outputType = null,
    outputSeparator = null,
    onCancel = {},
    onTimeSelect = {}
)
```
در این قسمت شما میتونید با استفاده از پارامترهای ورودی تابع (که اسمشم جوری گذاشتم که گویا باشه) استفاده کنید و تا جای ممکن دیالوگ رو برای خودتون شخصی سازی کنین. تو اینجا دو تا lambda function داریم که اولیش onCancel هست که وقتی روی دکمه انصراف (یا هر چیزی که اسمش رو خودتون توی cancelTitle بزارید) زده بشه فراخوانی میشه:
```kotlin
onCancel = {
    // collapse the bottom sheet or do something
}
```
دومیش هم onTimeSelect هست که وقتی روی دکمه ثبت (یا هر چیزی که اسمش رو خودتون توی confirmTitle بزارید) زده بشه فراخوانی میشه که مقدار selectedTime همون زمانی هست که انتخاب کردید:
```kotlin
onTimeSelect = { selectedTime ->
    // show the time or do something
}
```
<br>&nbsp;<br>

## تابع انتخاب تاریخ (Dialog)
<p align="center">
    <img alt="Time Dialog" src="/shots/date_dialog.png"  width="400" height="300"> 
</p>

با فراخوانی تابع زیر، شما میتونید خروجی بالا (که به صورت Dialog هست) رو تو هر جایی از کد compose تون که بخواین داشته باشین:

```kotlin
DateDialog(
    title = null,
    confirmTitle = null,
    cancelTitle = null,
    initialDay = null,
    initialMonth = null,
    initialYear = null,
    yearRange = 1380..1410,
    fontFamily = null,
    titleColor = null,
    yearColor = null,
    monthColor = null,
    dayColor = null,
    slashColor = null,
    confirmColor = null,
    cancelColor = null,
    dividerColor = null,
    backgroundColor = null,
    backgroundBrush = null,
    outputType = null,
    outputSeparator = null,
    onCancel = {},
    onDateSelect = {}
)
```
در این قسمت شما میتونید با استفاده از پارامترهای ورودی تابع (که اسمشم جوری گذاشتم که گویا باشه) استفاده کنید و تا جای ممکن دیالوگ رو برای خودتون شخصی سازی کنین. تو اینجا دو تا lambda function داریم که اولیش onCancel هست که وقتی روی دکمه انصراف (یا هر چیزی که اسمش رو خودتون توی cancelTitle بزارید) زده بشه فراخوانی میشه:
```kotlin
onCancel = {
    // dismiss the dialog or do something
}
```
دومیش هم onDateSelect هست که وقتی روی دکمه ثبت (یا هر چیزی که اسمش رو خودتون توی confirmTitle بزارید) زده بشه فراخوانی میشه که مقدار selectedDate همون زمانی هست که انتخاب کردید:
```kotlin
onDateSelect = { selectedDate ->
    // show the time or do something
}
```
<br>&nbsp;<br>

## تابع انتخاب تاریخ (BottomSheet)
<p align="center">
    <img alt="Time Dialog" src="/shots/date_bottom_sheet.png"  width="400" height="300"> 
</p>

با فراخوانی تابع زیر، شما میتونید خروجی بالا (که به صورت BottomSheet هست) رو تو هر جایی از کد compose تون که بخواین داشته باشین:

```kotlin
DateBottomSheet(
    title = null,
    confirmTitle = null,
    cancelTitle = null,
    initialDay = null,
    initialMonth = null,
    initialYear = null,
    yearRange = 1380..1410,
    fontFamily = null,
    titleColor = null,
    yearColor = null,
    monthColor = null,
    dayColor = null,
    slashColor = null,
    confirmColor = null,
    cancelColor = null,
    dividerColor = null,
    backgroundColor = null,
    backgroundBrush = null,
    outputType = null,
    outputSeparator = null,
    onCancel = {},
    onDateSelect = {}
)
```
در این قسمت شما میتونید با استفاده از پارامترهای ورودی تابع (که اسمشم جوری گذاشتم که گویا باشه) استفاده کنید و تا جای ممکن دیالوگ رو برای خودتون شخصی سازی کنین. تو اینجا دو تا lambda function داریم که اولیش onCancel هست که وقتی روی دکمه انصراف (یا هر چیزی که اسمش رو خودتون توی cancelTitle بزارید) زده بشه فراخوانی میشه:
```kotlin
onCancel = {
    // collapse the bottom sheet or do something
}
```
دومیش هم onDateSelect هست که وقتی روی دکمه ثبت (یا هر چیزی که اسمش رو خودتون توی confirmTitle بزارید) زده بشه فراخوانی میشه که مقدار selectedDate همون زمانی هست که انتخاب کردید:
```kotlin
onDateSelect = { selectedDate ->
    // show the time or do something
}
```
<br>&nbsp;<br>

## تابع انتخاب شهر و استان (Dialog)
<p align="center">
    <img alt="Time Dialog" src="/shots/city_dialog.png"  width="400" height="300"> 
</p>

با فراخوانی تابع زیر، شما میتونید خروجی بالا (که به صورت Dialog هست) رو تو هر جایی از کد compose تون که بخواین داشته باشین:

```kotlin
CityDialog(
    title = null,
    confirmTitle = null,
    cancelTitle = null,
    fontFamily = null,
    titleColor = null,
    provinceColor = null,
    cityColor = null,
    dashColor = null,
    confirmColor = null,
    cancelColor = null,
    dividerColor = null,
    backgroundColor = null,
    backgroundBrush = null,
    outputSeparator = null,
    onCancel = {},
    onCitySelect = {}
)
```
در این قسمت شما میتونید با استفاده از پارامترهای ورودی تابع (که اسمشم جوری گذاشتم که گویا باشه) استفاده کنید و تا جای ممکن دیالوگ رو برای خودتون شخصی سازی کنین. تو اینجا دو تا lambda function داریم که اولیش onCancel هست که وقتی روی دکمه انصراف (یا هر چیزی که اسمش رو خودتون توی cancelTitle بزارید) زده بشه فراخوانی میشه:
```kotlin
onCancel = {
    // dismiss the dilog or do something
}
```
دومیش هم onCitySelect هست که وقتی روی دکمه ثبت (یا هر چیزی که اسمش رو خودتون توی confirmTitle بزارید) زده بشه فراخوانی میشه که مقدار selectedCity همون زمانی هست که انتخاب کردید:
```kotlin
onCitySelect = { selectedCity ->
    // show the city or do something
}
```
<br>&nbsp;<br>

## تابع انتخاب شهر و استان (BottomSheet)
<p align="center">
    <img alt="Date Dialog" src="/shots/city_bottom_sheet.png"  width="400" height="300"> 
</p>

با فراخوانی تابع زیر، شما میتونید خروجی بالا (که به صورت BottomSheet هست) رو تو هر جایی از کد compose تون که بخواین داشته باشین:

```kotlin
CityBottomSheet(
    title = null,
    confirmTitle = null,
    cancelTitle = null,
    fontFamily = null,
    titleColor = null,
    provinceColor = null,
    cityColor = null,
    dashColor = null,
    confirmColor = null,
    cancelColor = null,
    dividerColor = null,
    backgroundColor = null,
    backgroundBrush = null,
    outputSeparator = null,
    onCancel = {},
    onCitySelect = {}
)
```
در این قسمت شما میتونید با استفاده از پارامترهای ورودی تابع (که اسمشم جوری گذاشتم که گویا باشه) استفاده کنید و تا جای ممکن باتم شیت رو برای خودتون شخصی سازی کنین. تو اینجا دو تا lambda function داریم که اولیش onCancel هست که وقتی روی دکمه انصراف (یا هر چیزی که اسمش رو خودتون توی cancelTitle بزارید) زده بشه فراخوانی میشه:
```kotlin
onCancel = {
    // collapse the bottom sheet or do something
}
```
دومیش هم onTimeSelect هست که وقتی روی دکمه ثبت (یا هر چیزی که اسمش رو خودتون توی confirmTitle بزارید) زده بشه فراخوانی میشه که مقدار selectedTime همون زمانی هست که انتخاب کردید:
```kotlin
onCitySelect = { selectedCity ->
    // show the city or do something
}
```
<br>&nbsp;<br>
