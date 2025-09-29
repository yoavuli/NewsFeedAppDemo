package com.yoavuli.newsfeedapp.data.datasrc

import android.content.Context
import com.yoavuli.newsfeedapp.data.model.NewsFeedItem

interface FeedDataSource {

    suspend fun getNewsFeed(context: Context): List<NewsFeedItem>
}