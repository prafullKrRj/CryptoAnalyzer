package com.example.crypto.ui.screens.detailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crypto.model.coinDetail.CoinCompleteDetail
import com.example.crypto.ui.screens.listScreens.ErrorScreen
import com.example.crypto.ui.screens.listScreens.LoadingScreen

@Composable
fun CoinDetailScreen() {
    val viewModel: DetailScreenViewModel = viewModel(factory = DetailScreenViewModel.Factory)
    val coinState = viewModel.coinDetailState

    when (coinState) {
        is CoinDetailState.Error -> {
            ErrorScreen()
        }
        is CoinDetailState.Loading -> {
            LoadingScreen()
        }
        is CoinDetailState.Success -> SuccessScreen(coinState.coinDetail, Modifier.padding(16.dp))
    }
}

@Composable
private fun SuccessScreen(coin: CoinCompleteDetail, modifier: Modifier) {
    LazyColumn (modifier = modifier){
        item {
            TitleRow(coin = coin)
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Description(coin = coin)
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Tags(coin = coin)
        }
    }

}

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
                text = coin.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Row (Modifier.weight(.25f), horizontalArrangement = Arrangement.End){
            Text(text = if (coin.isActive) "active" else "inactive", color = MaterialTheme.colorScheme.tertiary)
        }
    }
}

@Composable
fun Description(coin: CoinCompleteDetail) {
    Text(text = coin.description, fontSize = 16.sp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tags(coin: CoinCompleteDetail) {
    val tags = coin.tags
    Text(text = "Tags", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
    LazyRow (Modifier.fillMaxWidth()){
        items(tags.size) {
            InputChip(selected = false, onClick = { /*TODO*/ }, label = { Row {
                Text(text = tags[it].name)
            }}, modifier = Modifier.padding(horizontal = 3.dp))
        }
    }
}