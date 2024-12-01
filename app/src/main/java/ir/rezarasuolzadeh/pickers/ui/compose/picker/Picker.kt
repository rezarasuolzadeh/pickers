package ir.rezarasuolzadeh.pickers.ui.compose.picker

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import ir.rezarasuolzadeh.pickers.ui.theme.White
import ir.rezarasuolzadeh.pickers.utils.extensions.fadingEdge
import ir.rezarasuolzadeh.pickers.utils.extensions.pixelsToDp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
internal fun Picker(
    items: List<String>,
    state: PickerState = rememberPickerState(),
    modifier: Modifier = Modifier,
    fontFamily: FontFamily,
    itemColor: Color = White,
    startIndex: Int = 0,
    visibleItemsCount: Int = 3,
    textModifier: Modifier = Modifier,
    showDivider: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    dividerColor: Color = LocalContentColor.current,
    onItemChanged: (String) -> Unit = {}
) {
    val visibleItemsMiddle = visibleItemsCount / 2
    val listScrollCount = Integer.MAX_VALUE
    val listScrollMiddle = listScrollCount / 2
    val listStartIndex = listScrollMiddle - listScrollMiddle % items.size - visibleItemsMiddle + startIndex
    val listState = when (items.size) {
        31 -> {
            rememberLazyListState(initialFirstVisibleItemIndex = listStartIndex)
        }
        30 -> {
            rememberLazyListState(initialFirstVisibleItemIndex = listStartIndex)
        }
        29 -> {
            rememberLazyListState(initialFirstVisibleItemIndex = listStartIndex)
        }
        else -> {
            rememberLazyListState(initialFirstVisibleItemIndex = listStartIndex)
        }
    }
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    val itemHeightPixels = remember { mutableIntStateOf(value = 0) }
    val itemHeightDp = pixelsToDp(pixels = itemHeightPixels.intValue)
    val fadingEdgeGradient = remember {
        Brush.verticalGradient(
            0f to Color.Transparent,
            0.5f to itemColor,
            1f to Color.Transparent
        )
    }

    /**
     * get the item in specific index from items list.
     */
    fun getItem(index: Int): String {
        return items[index % items.size]
    }

    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index ->
                getItem(index = index + visibleItemsMiddle)
            }
            .distinctUntilChanged()
            .collect { item ->
                state.selectedItem = item
                onItemChanged(item)
            }
    }

    Box(
        modifier = modifier
    ) {
        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(height = itemHeightDp * visibleItemsCount)
                .fadingEdge(brush = fadingEdgeGradient)
        ) {
            items(listScrollCount) { index ->
                Text(
                    text = getItem(index = index),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = textStyle,
                    color = itemColor,
                    fontFamily = fontFamily,
                    modifier = Modifier
                        .onSizeChanged { size ->
                            itemHeightPixels.intValue = size.height
                        }
                        .then(other = textModifier),
                    textAlign = TextAlign.Center
                )
            }
        }
        if (showDivider) {
            Divider(
                color = dividerColor,
                modifier = Modifier.offset(y = itemHeightDp * visibleItemsMiddle)
            )
            Divider(
                color = dividerColor,
                modifier = Modifier.offset(y = itemHeightDp * (visibleItemsMiddle + 1))
            )
        }
    }

}