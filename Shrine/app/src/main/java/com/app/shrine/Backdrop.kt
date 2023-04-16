package com.app.shrine

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shrine.ui.theme.ShrineTheme
import kotlinx.coroutines.launch

val menuData = listOf("Featured", "Apartment", "Accessories", "Shoes")

@Composable
private fun TopAppBarText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text.uppercase(),
        style = MaterialTheme.typography.subtitle1,
        fontSize = 17.sp
    )
} // End of TopAppBarText


@Composable
private fun MenuSearchField() {
    var searchText by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .height(56.dp)
            .padding(end = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = { searchText = it },
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .padding(end = 36.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    innerTextField()
                }
            }
        )
        if (searchText.isEmpty()) {
            TopAppBarText(
                modifier = Modifier.alpha(ContentAlpha.disabled),
                text = "Search Shrine"
            )
        }
        Divider(
            modifier = Modifier.align(Alignment.BottomCenter),
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
        )
    }
} // End of MenuSearchField

@Composable
private fun ShrineTopAppBar(
    backdropRevealed: Boolean,
    onBackdropReveal: (Boolean) -> Unit = {},
) {
    TopAppBar(
        title = {
            val density = LocalDensity.current

            Box(
                Modifier
                    .width(46.dp)
                    .fillMaxHeight()
                    .toggleable(
                        value = backdropRevealed,
                        onValueChange = { onBackdropReveal(it) },
                        indication = rememberRipple(bounded = false, radius = 56.dp),
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                AnimatedVisibility(
                    visible = !backdropRevealed,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 180,
                            delayMillis = 90,
                            easing = LinearEasing
                        )
                    ) + slideInHorizontally(
                        initialOffsetX = {
                            with(density) {
                                (-20).dp.roundToPx()
                            }
                        }, animationSpec = tween(durationMillis = 270, easing = LinearEasing)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 120,
                            easing = LinearEasing
                        )
                    ) + slideOutHorizontally(
                        targetOffsetX = {
                            with(density) {
                                (-20).dp.roundToPx()
                            }
                        }, animationSpec = tween(durationMillis = 120, easing = LinearEasing)
                    )
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_menu_cut_24px),
                        contentDescription = "Menu navigation icon"
                    )
                }

                Icon(
                    painterResource(id = R.drawable.ic_shrine_logo),
                    contentDescription = "Shrine logo",
                    modifier = Modifier.offset(x = if (!backdropRevealed) 20.dp else 0.dp)
                )
            }

            // TopAppBar가 펼쳐져있으면 TopAppBar Text가 보이고, 아닐 경우 검색창이 보임
            if (!backdropRevealed) {
                TopAppBarText(text = "Shrine")
            } else {
                MenuSearchField()
            }
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search action",
                tint = LocalContentColor.current.copy(alpha = ContentAlpha.high),
                modifier = Modifier.padding(end = 12.dp)
            )
        },
        elevation = 0.dp
    )
} // End of ShrineTopAppBar

@ExperimentalMaterialApi
@Composable
fun Backdrop() {
    val scope = rememberCoroutineScope()
    var backdropState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    var menuSelection by remember {
        mutableStateOf(0)
    }

    BackdropScaffold(scaffoldState = backdropState, appBar = {
        TopAppBar(title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painterResource(id = R.drawable.ic_menu_cut_24px),
                    contentDescription = "Menu icon",
                    modifier = Modifier.clickable {
                        scope.launch {
                            if (backdropState.isConcealed) {
                                backdropState.reveal()
                            } else {
                                backdropState.conceal()
                            }
                        }
                    })
                Icon(
                    painterResource(id = R.drawable.ic_shrine_logo),
                    contentDescription = "Shrine logo",
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    "Shrine".uppercase(), style = MaterialTheme.typography.subtitle1
                )
            }
        }, actions = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "Search action"
            )
        }, elevation = 0.dp
        )
    }, frontLayerContent = {
        Column(Modifier.padding(16.dp)) {
            Text("This is the content for the ${menuData[menuSelection]}")
        }
    }, frontLayerShape = MaterialTheme.shapes.large,
        backLayerContent = {
            BackdropMenuItems(
                activeMenuItem = menuSelection,
                onMenuItemSelect = {
                    menuSelection = it
                }
            )
        }
    )
} // End of Backdrop


@Composable
private fun BackdropMenuItems(
    activeMenuItem: Int = -1,
    onMenuItemSelect: (index: Int) -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        menuData.forEachIndexed { idx, item ->
            MenuItem(
                index = idx,
                text = item,
                activeMenu = activeMenuItem,
            ) {
                onMenuItemSelect(it)
            }
        }
        Text(
            "My Account".uppercase(),
            style = MaterialTheme.typography.subtitle1

        )
    }
} // End of BackdropMenuItems

@Composable
fun MenuItem(
    index: Int = -1,
    text: String = "Menu item",
    activeMenu: Int = -1,
    onClick: (index: Int) -> Unit = {}
) {
    Box(
        modifier = Modifier.height(20.dp),
        contentAlignment = Alignment.Center
    ) {
        if (activeMenu == index) {
            Image(
                painterResource(id = R.drawable.ic_tab_indicator),
                contentDescription = null
            )
        }
        Text(
            "$text".uppercase(),
            style = MaterialTheme.typography.subtitle1, fontSize = 17.sp,
            modifier = Modifier.clickable {
                onClick(index)
            }
        )
    }
} // End of MenuItem

@Preview
@Composable
fun ShrineTopAppBarPreview() {
    ShrineTheme {
        Surface {
            Column(Modifier.padding(20.dp)) {
                ShrineTopAppBar(backdropRevealed = false)
                Spacer(Modifier.height(8.dp))
                ShrineTopAppBar(backdropRevealed = true)
            }
        }
    }
} // End of ShrineTopAppBarPreview

@Composable
private fun MenuText(
    text: String = "Item",
    activeDecoration: @Composable () -> Unit = {}
) {
    Box(
        modifier = Modifier.height(44.dp),
        contentAlignment = Alignment.Center
    ) {
        activeDecoration()
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.subtitle1
        )
    }
} // End of MenuText


@ExperimentalMaterialApi
@Preview
@Composable
fun BackdropPreview() {
    ShrineTheme {
        Backdrop()
    }
} // End of BackdropPreview


@Preview
@Composable
fun BackdropMenuItemsPreview() {
    ShrineTheme {
        Surface {
            BackdropMenuItems()
        }
    }
} // End of BackdropMenuItemsPreview

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    ShrineTheme {
        MenuItem()
    }
} // End of MenuItemPreview
