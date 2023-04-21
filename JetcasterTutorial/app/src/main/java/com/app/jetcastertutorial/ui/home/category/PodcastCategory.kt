package com.app.jetcastertutorial.ui.home.category

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.jetcastertutorial.data.PodcastWithExtraInfo


@Composable
fun PodcastCategory(
    categoryId: Long,
    navigationToPlayer: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel : PodcastCategoryViewModel = viewModel(

    )


    val viewState by viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = modifier) {

    }
} // End of PodcastCategory


@Composable
private fun CategoryPotcasts(
    topPodcasts : List<PodcastWithExtraInfo>,

    ) {

}