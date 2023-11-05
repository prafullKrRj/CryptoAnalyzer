package com.example.crypto.ui.screens.listScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.crypto.Screens
import com.example.crypto.model.Coins.Coins
import com.example.crypto.model.Coins.CryptoDetail
import com.example.crypto.ui.screens.commons.ErrorScreen
import com.example.crypto.ui.screens.commons.LoadingScreen
import com.example.crypto.ui.screens.listScreens.components.AppBar
import com.example.crypto.ui.screens.listScreens.components.ListItemComposable

@Composable
fun ListScreen(
    navController: NavHostController
) {
    val viewModel: ListScreenViewModel =
        viewModel(factory = ListScreenViewModel.Factory)
    when (val listState: ListState = viewModel.listState) {
        is ListState.Success -> {
            MainScreen(coins = listState.details,
                modifier = Modifier,
                navController = navController,
            ) {
                navController
                    .navigate(Screens.SEARCH_SCREEN.name + "/$it")
            }
        }
        is ListState.Error -> {
            ErrorScreen()
        }
        is ListState.Loading -> {
            LoadingScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    coins: List<CryptoDetail>,
    modifier: Modifier,
    navController: NavHostController,
    onSearched: (String) -> Unit
) {
    Scaffold (
        topBar = { AppBar {  userSearch ->
            onSearched(userSearch)
        } }
    ) { paddingValues ->
        Box(modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(bottom = 16.dp)
            .padding(horizontal = 16.dp)) {
            CoinsList(coins = coins, navController = navController)
        }
    }
}

@Composable
fun CoinsList(coins: List<CryptoDetail>, navController: NavHostController) {
    val list: MutableList<Coins> = mutableListOf()
    for (cryptoDetail in coins) {
        val coin = Coins(
            name = cryptoDetail.name,
            id = cryptoDetail.id,
            rank = cryptoDetail.rank,
            symbol = cryptoDetail.symbol,
            isActive = cryptoDetail.isActive,
            type = cryptoDetail.type
        )
        list.add(coin)
    }
    LazyColumn {
        item {
            Row (Modifier.fillMaxWidth()){
                Text(
                    text = "Total: ${coins.size}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp
                )
            }
        }
        items(coins.size) { idx ->
            (list[idx]).also {
                ListItemComposable(
                    modifier = Modifier.padding(vertical = 8.dp),
                    coin = it,
                    idx = idx + 1,
                    navController = navController
                )
            }
        }
    }
}