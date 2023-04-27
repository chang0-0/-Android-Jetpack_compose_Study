package com.app.composetopappbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Icon"
                        )
                    }
                },
                title = {
                    Text(text = "Home")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        content = {
            CustomList(paddingValues = it)
        }
    )
} // End of MainScreen

@Composable
fun CustomList(paddingValues: PaddingValues) {
    val numbers = remember {
        mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    LazyColumn(
        modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
    ) {
        items(items = numbers, key = { it.hashCode() }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 24.dp),
                text = "$it",
                style = TextStyle(
                    fontSize = androidx.compose.material.MaterialTheme.typography.body1.fontSize,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
} // End of CustomList