package com.example.crypto.ui.screens.detailScreen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.crypto.model.coinDetail.CoinCompleteDetail

@Composable
fun Description(coin: CoinCompleteDetail) {
    if (!coin.description.isNullOrBlank()) {
        if (coin.description.length > 700) {
            var ans by remember {
                mutableStateOf(coin.description.substring(0, 700))
            }
            var expanded by remember {
                mutableStateOf(false)
            }
            Text(text = ans, fontSize = 16.sp, modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ))
            Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                TextButton(onClick = {
                    ans = if (expanded) coin.description.substring(0, 700)+"...."
                    else coin.description
                    expanded = !expanded
                }) {
                    Text(text = if (expanded) "see less" else "see more")
                }
            }
        }
        else {
            Text(text = coin.description, fontSize = 16.sp)
        }
    }
}