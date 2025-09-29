package com.yoavuli.newsfeedapp.presentation

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoavuli.newsfeedapp.data.NewsRepo
import com.yoavuli.newsfeedapp.data.model.NewsFeedItem
import com.yoavuli.newsfeedapp.data.model.NewsFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsFeedViewModel : ViewModel() {


        private val _newFeed = MutableStateFlow<List<NewsFeedItem>>(emptyList())
        val newsFeed: StateFlow<List<NewsFeedItem>> = _newFeed

        val repo = NewsRepo()


        fun init(context: Context) {
            viewModelScope.launch(Dispatchers.IO){
            repo.init(context)
            _newFeed.value  = repo.getNewsFeedByFilter(emptyList())
        }
            }

    fun updateFilters(title: String,rating: Int) {
        val filterList = mutableListOf<Pair<NewsFilter,Any>>()
        if (title.isNotBlank()) {
            filterList.add(Pair(NewsFilter.TITLE_CONTAINS,title))
        }
        _newFeed.value  = repo.getNewsFeedByFilter(filterList)
    }


}