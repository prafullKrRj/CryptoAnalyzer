package com.example.crypto.ui.screens.listScreens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.R
import com.example.crypto.ui.screens.commons.SearchField

@Composable
fun AppBar(onSearched: (String) -> Unit) {
    var searchIconClicked by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(modifier = Modifier.width(8.dp))
        AppIcon()
        if (!searchIconClicked) {
            NonSearchesTitle(modifier = Modifier.weight(.70f))
            SearchIcon(modifier = Modifier.weight(.20f)) {
                searchIconClicked = true
            }
        }
        else {
            SearchField(modifier = Modifier
                .weight(.90f)
                .padding(end = 16.dp)) {  userSearch ->
                onSearched(userSearch)
                searchIconClicked = false
            }
        }
    }
}


@Composable
private fun SearchIcon(modifier: Modifier, onSearchClicked: (Boolean) -> Unit) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ){
        IconButton(onClick = {
            onSearchClicked(true)
        }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    }
}

@Composable
private fun AppIcon() {
    Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, modifier = Modifier
        .size(60.dp)
        .clip(
            CircleShape
        ))
}
@Composable
private fun NonSearchesTitle(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Text(text = "CryptoAnalyzer", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}