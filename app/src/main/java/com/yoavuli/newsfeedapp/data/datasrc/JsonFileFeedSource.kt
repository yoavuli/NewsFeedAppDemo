package com.yoavuli.newsfeedapp.data.datasrc

import android.content.Context
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.yoavuli.newsfeedapp.data.model.NewsFeedItem

class JsonFileFeedSource : FeedDataSource {

    override suspend fun getNewsFeed(context : Context): List<NewsFeedItem> {
        return try {
            val json = context.assets.open("articles.json")
                .bufferedReader()
                .use { it.readText() }

            val type = object : TypeToken<ArticlesWrapper>() {}.type
            Gson().fromJson<ArticlesWrapper>(json, type).articles
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // fallback so app doesn’t crash
        }
    }
}

data class ArticlesWrapper(
    val articles: List<NewsFeedItem>
)