package com.example.crypto.ui.screens.detailScreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.crypto.model.coinDetail.CoinCompleteDetail

@Composable
fun OpenSource(coin: CoinCompleteDetail) {
    Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "Open Source:  ",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
        )
        Text(
            text = if (coin.openSource) "Yes" else "No",
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
    }
}