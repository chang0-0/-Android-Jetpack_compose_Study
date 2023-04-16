package com.app.shrine

import android.graphics.Paint.Align
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.runtime.saveable.rememberSaveable
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

@OptIn(ExperimentalAnimationApi::class)
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

                val logoTransition = updateTransition(
                    targetState = backdropRevealed,
                    label = "logoTransition"
                )

                val logoOffset by logoTransition.animateDp(
                    transitionSpec = {
                        if (targetState) {
                            tween(durationMillis = 120, easing = LinearEasing)
                        } else {
                            tween(durationMillis = 270, easing = LinearEasing)
                        }
                    },
                    label = "logoOffset"
                ) { revealed ->
                    if (!revealed) 20.dp else 0.dp
                }

                Icon(
                    painterResource(id = R.drawable.ic_shrine_logo),
                    contentDescription = "Shrine logo",
                    modifier = Modifier.offset(x = logoOffset)
                )
            }

            AnimatedContent(
                targetState = backdropRevealed,
                transitionSpec = {
                    if (targetState) {
                        // Conceal to reveal
                        fadeIn(
                            animationSpec = tween(
                                durationMillis = 240,
                                delayMillis = 120,
                                easing = LinearEasing
                            )
                        ) +
                                slideInHorizontally(
                                    initialOffsetX = { with(density) { 30.dp.roundToPx() } },
                                    animationSpec = tween(
                                        durationMillis = 270,
                                        easing = LinearEasing
                                    )
                                ) with
                                fadeOut(
                                    animationSpec = tween(
                                        durationMillis = 120,
                                        easing = LinearEasing
                                    )
                                ) +
                                slideOutHorizontally(
                                    targetOffsetX = { with(density) { (-30).dp.roundToPx() } },
                                    animationSpec = tween(
                                        durationMillis = 120,
                                        easing = LinearEasing
                                    )
                                )
                    } else {
                        // Reveal to conceal
                        fadeIn(
                            animationSpec = tween(
                                durationMillis = 180,
                                delayMillis = 90,
                                easing = LinearEasing
                            )
                        ) +
                                slideInHorizontally(
                                    initialOffsetX = { with(density) { (-30).dp.roundToPx() } },
                                    animationSpec = tween(
                                        durationMillis = 270,
                                        easing = LinearEasing
                                    )
                                ) with
                                fadeOut(
                                    animationSpec = tween(
                                        durationMillis = 90,
                                        easing = LinearEasing
                                    )
                                ) +
                                slideOutHorizontally(
                                    targetOffsetX = { with(density) { 30.dp.roundToPx() } },
                                    animationSpec = tween(
                                        durationMillis = 90,
                                        easing = LinearEasing
                                    )
                                )
                    }.using(SizeTransform(clip = false))
                },
                contentAlignment = Alignment.CenterStart
            ) {

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
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    var backdropRevealed by rememberSaveable { mutableStateOf(scaffoldState.isRevealed) }
    val scope = rememberCoroutineScope()
    var activeCategory by rememberSaveable {
        mutableStateOf(Category.All)
    }

    BackdropScaffold(
        scaffoldState = scaffoldState,
        gesturesEnabled = false,
        appBar = {
            ShrineTopAppBar(
                backdropRevealed = scaffoldState.isRevealed,
                onBackdropReveal = {
                    backdropRevealed = it
                    scope.launch {
                        if (scaffoldState.isConcealed) {
                            scaffoldState.reveal()
                        } else {
                            scaffoldState.conceal()
                        }
                    }
                }
            )
        },
        frontLayerContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text("This is the content for category : $activeCategory")
            }
        },
        backLayerContent = {
            NavigationMenu(
                modifier = Modifier.padding(top = 12.dp, bottom = 32.dp),
                activeCategory = activeCategory,
                onMenuSelect = {
                    backdropRevealed = false
                    activeCategory = it
                    scope.launch {
                        scaffoldState.conceal()
                    }
                }
            )
        },
        frontLayerShape = MaterialTheme.shapes.large,
        frontLayerElevation = 16.dp
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
private fun NavigationMenu(
    modifier: Modifier = Modifier,
    activeCategory: Category = Category.All,
    onMenuSelect: (Category) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val categories = Category.values()

    }
} // End of NavigationMenu

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


@OptIn(ExperimentalAnimationApi::class)
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
