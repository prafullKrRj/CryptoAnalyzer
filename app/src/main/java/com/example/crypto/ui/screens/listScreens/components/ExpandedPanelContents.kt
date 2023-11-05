package com.example.crypto.ui.screens.listScreens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.crypto.model.Coins.Coins

@Composable
fun ExpandedCard(coin: Coins, modifier: Modifier) {
    Column (modifier.padding(top = 8.dp, bottom = 16.dp)){
        Text(text = "Rank: ${coin.rank}")
        Text(text = "Type: ${coin.type}")
    }
}