package com.yoavuli.newsfeedapp.data.model

enum class NewsFilter {

    TITLE_CONTAINS,
    RATING_ABOVE,
    NONE;



    fun filterList(newFeed: List<NewsFeedItem> , value : Any) {
        try{
             when (this) {
                TITLE_CONTAINS ->{
                    newFeed.filter { it.title.contains(value as String, ignoreCase = true) }
                }
                RATING_ABOVE -> {
                    newFeed.filter { it.rating >= (value as Int) }
                }
                NONE -> TODO()
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }

    }

}