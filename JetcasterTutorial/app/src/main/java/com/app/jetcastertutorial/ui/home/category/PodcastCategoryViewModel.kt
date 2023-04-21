package com.app.jetcastertutorial.ui.home.category

class PodcastCategoryViewModel(
    private val categoryId : Long,
    private val categoryStore : CategoryStore = Graph.categoryStore,
) {
}