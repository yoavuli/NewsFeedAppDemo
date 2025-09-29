package com.yoavuli.newsfeedapp.data

import android.content.Context
import com.yoavuli.newsfeedapp.data.datasrc.JsonFileFeedSource
import com.yoavuli.newsfeedapp.data.model.NewsFeedItem
import com.yoavuli.newsfeedapp.data.model.NewsFilter

class NewsRepo {

    private val newsFeed : MutableList<NewsFeedItem> = mutableListOf<NewsFeedItem>()


    suspend fun init (context: Context) {
        val dataSource = JsonFileFeedSource()
        val fetchedFeed = dataSource.getNewsFeed(context)
        newsFeed.addAll(fetchedFeed)
    }


    fun getNewsFeedByFilter(filterList :List <Pair<NewsFilter,Any>> ): List<NewsFeedItem> {


        val filteredList = newsFeed.toMutableList()
            filterList.forEach { filterPair ->
            filterPair.first.filterList(filteredList,filterPair.second)


        }

        return filteredList
    }



}