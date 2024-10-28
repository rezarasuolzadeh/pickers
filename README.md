### تابع انتخاب زمان
<p align="center">
    <img alt="Date Dialog" src="/shots/time_dialog.png"  width="400" height="300"> 
</p>


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
#### در این قسمت شما میتونید با استفاده از پارامترهای ورودی تابع (که اسمشم جوری گذاشتم که گویا باشه) استفاده کنید و تا جای ممکن دیالوگ رو برای خودتون شخصی سازی کنین. تو اینجا دو تا lambda function داریم که اولیش onCancel هست که وقتی روی دکمه انصراف (یا هر چیزی که اسمش رو خودتون توی cancelTitle بزارید) زده بشه فراخوانی میشه:
```kotlin
onCancel = {
    // dismiss the dialog or do something
}
```
#### دومیش هم onTimeSelect هست که وقتی روی دکمه ثبت (یا هر چیزی که اسمش رو خودتون توی confirmTitle بزارید) زده بشه فراخوانی میشه که مقدار selectedTime همون زمانی هست که انتخاب کردید:
```kotlin
onTimeSelect = { selectedTime ->
    // show the time or do something
}
```




### تابع انتخاب تاریخ
<p align="center">
    <img alt="Time Dialog" src="/shots/date_dialog.png"  width="400" height="300"> 
</p>


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
#### در این قسمت شما میتونید با استفاده از پارامترهای ورودی تابع (که اسمشم جوری گذاشتم که گویا باشه) استفاده کنید و تا جای ممکن دیالوگ رو برای خودتون شخصی سازی کنین. تو اینجا دو تا lambda function داریم که اولیش onCancel هست که وقتی روی دکمه انصراف (یا هر چیزی که اسمش رو خودتون توی cancelTitle بزارید) زده بشه فراخوانی میشه:
```kotlin
onCancel = {
    // dismiss the dialog or do something
}
```
#### دومیش هم onDateSelect هست که وقتی روی دکمه ثبت (یا هر چیزی که اسمش رو خودتون توی confirmTitle بزارید) زده بشه فراخوانی میشه که مقدار selectedDate همون زمانی هست که انتخاب کردید:
```kotlin
onDateSelect = { selectedDate ->
    // show the time or do something
}
```
