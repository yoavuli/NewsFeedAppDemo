package com.yoavuli.newsfeedapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.yoavuli.newsfeedapp.data.model.NewsFeedItem


@Composable
fun NewsFeedScreen(modifier: Modifier) {

    val viewModel = NewsFeedViewModel()
    viewModel.init(context = LocalContext.current.applicationContext)
    val newsFeed by viewModel.newsFeed.collectAsState()


    var title by rememberSaveable { mutableStateOf("") }
    var rating by rememberSaveable { mutableStateOf(0) }
    // title search input
    OutlinedTextField(
        value = title,
        onValueChange = { title = it },
        singleLine = true,
        label = { Text("Title search") },

    )
    Button(
        onClick = {
            viewModel.updateFilters(title,rating)
            title = "" // optimistic clear
        },
        enabled = title.isNotBlank()
    ) {
        Text("Filter")
    }


    NewsFeedList(
        modifier = modifier.padding(8.dp),
        items = newsFeed
    )


}

@Composable
fun NewsFeedList(modifier: Modifier, items: List<NewsFeedItem>) {
   LazyColumn(modifier = modifier) {
         items(items.size) { index ->
              NewsFeedItemCard(item = items[index])
         }
   }

}

@Composable
fun NewsFeedItemCard(item: NewsFeedItem) {
    Card ( modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp))
    {

        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(item.title ?: "")
        }
    }

}