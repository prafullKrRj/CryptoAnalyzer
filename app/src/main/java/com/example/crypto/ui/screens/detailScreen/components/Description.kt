package com.example.crypto.ui.screens.detailScreen.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.crypto.model.coinDetail.CoinCompleteDetail

@Composable
fun Description(coin: CoinCompleteDetail) {
    Text(text = coin.description, fontSize = 16.sp)
}