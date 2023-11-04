package com.example.crypto.ui.screens.detailScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.model.coinDetail.CoinCompleteDetail

@Composable
fun TitleRow(coin: CoinCompleteDetail) {
    Row (
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)){
        Row (Modifier.weight(.75f), verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "${coin.rank}. ",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = coin.name + " (${coin.symbol})",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Row (Modifier.weight(.25f), horizontalArrangement = Arrangement.End){
            Text(text = if (coin.isActive) "active" else "inactive", color = MaterialTheme.colorScheme.tertiary)
        }
    }
}