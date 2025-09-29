package com.yoavuli.newsfeedapp.data.model

data class NewsFeedItem(val title:String,
                        val description:String,
                        val image_url:String,
                        val rating:Int = 0,
                        val placeholderColor: PlaceholderColor? )



data class PlaceholderColor(
    val red: Int,
    val green: Int,
    val blue: Int
)