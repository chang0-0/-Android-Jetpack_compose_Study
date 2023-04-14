package com.app.shrine

data class ItemData @JvmOverloads constructor(
    val id: Int,
    val title: String,
    val price: Int,
    val vendor: Vendor,
    val photoResId: Int,
)

enum class Vendor {
    Alphi,
    Lmbrjk,
    Mal,
    Six,
    Squiggle,
}

val SampleItemsData = listOf(
    ItemData(
        id = 0,
        title = "Vagabond chair",
        price = 120,
        vendor = Vendor.Squiggle,
        photoResId = R.drawable.photo_0
    ),
    ItemData(
        id = 1,
        title = "Stella chair",
        price = 180,
        vendor = Vendor.Alphi,
        photoResId = R.drawable.photo_1
    ),
    ItemData(
        id = 2,
        title = "Whitney chair",
        price = 210,
        vendor = Vendor.Six,
        photoResId = R.drawable.photo_2
    ),
    ItemData(
        id = 3,
        title = "Weave key chair",
        price = 200,
        vendor = Vendor.Mal,
        photoResId = R.drawable.photo_3
    ),
    ItemData(
        id = 4,
        title = "Gatsby chair",
        price = 150,
        vendor = Vendor.Lmbrjk,
        photoResId = R.drawable.photo_4
    ),
    ItemData(
        id = 5,
        title = "Shrug chair",
        price = 248,
        vendor = Vendor.Squiggle,
        photoResId = R.drawable.photo_5
    ),
    ItemData(
        id = 6,
        title = "Gilt chair",
        price = 300,
        vendor = Vendor.Mal,
        photoResId = R.drawable.photo_6
    )
)
