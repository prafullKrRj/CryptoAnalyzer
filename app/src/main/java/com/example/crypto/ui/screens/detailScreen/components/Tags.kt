package com.example.crypto.ui.screens.detailScreen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crypto.model.coinDetail.CoinCompleteDetail
import com.example.crypto.model.coinDetail.Tag

@Composable
fun Tags(coin: CoinCompleteDetail) {

    Text(text = "Tags", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
    Spacer(modifier = Modifier.height(6.dp))
    if (coin.tags.size > 7) {
        var tags by remember {
            mutableStateOf(coin.tags.subList(0,7))
        }
        var expanded by remember {
            mutableStateOf(false)
        }
        TagsRow(tags = tags)
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
            TextButton(onClick = {
                tags = if (expanded) coin.tags.subList(0, 7)
                else coin.tags
                expanded = !expanded
            }) {
                Text(text = if (expanded) "see less" else "see more")
            }
        }
    }
    else {
        TagsRow(tags = coin.tags)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsRow(tags: List<Tag>) {
    FlowRow (Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        )){
        tags.forEach {
            Tag(tag = it.name)
        }
    }
}
@Composable
fun Tag(tag: String) {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = tag,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }
}