package com.example.crypto.ui.screens.detailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.crypto.model.coinDetail.CoinCompleteDetail
import com.example.crypto.ui.screens.listScreens.ErrorScreen
import com.example.crypto.ui.screens.listScreens.LoadingScreen

@Composable
fun CoinDetailScreen(
    navController: NavController,
    savedStateHandle: SavedStateHandle
) {
    val viewModel: DetailScreenViewModel = viewModel(factory = DetailScreenViewModel.Factory)
    val coinState = viewModel.coinDetailState

    when (coinState) {
        is CoinDetailState.Error -> {
            ErrorScreen()
        }
        is CoinDetailState.Loading -> {
            LoadingScreen()
        }
        is CoinDetailState.Success -> SuccessScreen(coinState.coinDetail)
    }
}

@Composable
private fun SuccessScreen(coin: CoinCompleteDetail) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = coin.name, fontSize = 40.sp)
    }
}
