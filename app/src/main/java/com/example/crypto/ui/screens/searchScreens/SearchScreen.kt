package com.example.crypto.ui.screens.searchScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.crypto.CryptoApplication
import com.example.crypto.model.Coins.Coins
import com.example.crypto.model.search.Currency
import com.example.crypto.ui.screens.commons.ErrorScreen
import com.example.crypto.ui.screens.commons.LoadingScreen
import com.example.crypto.ui.screens.commons.SearchField
import com.example.crypto.ui.screens.detailScreen.MainScreenDivider
import com.example.crypto.ui.screens.listScreens.components.ListItemComposable

@Composable
fun SearchScreen(query: String, navController: NavHostController) {
    val x = object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CryptoApplication)
                val cryptoRepository = application.container.cryptoDetailRepository
                SearchScreenViewModel(cryptoRepository = cryptoRepository, query = query)
            }
        }
    }
    val viewModel: SearchScreenViewModel = viewModel(factory = x.Factory)
    when (val coinState = viewModel.searchState) {
        is SearchState.Error -> {
            ErrorScreen()
        }
        is SearchState.Loading -> {
            LoadingScreen()
        }
        is SearchState.Success -> {
            SearchScreenMain(coinState.details.currencies, navController, viewModel, coinState.query)
        }
    }
}
@Composable
private fun SearchScreenMain(
    currencies: List<Currency>,
    navController: NavHostController,
    viewModel: SearchScreenViewModel,
    query: String
) {

    val list: MutableList<Coins> = mutableListOf()
    for (cryptoDetail in currencies) {
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
    val title by remember {
        mutableStateOf(query)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        SearchField(modifier = Modifier.fillMaxWidth()) {
            viewModel.getCoins(it)
        }
        if (list.isNotEmpty()) {
            MainScreenDivider(modifier = Modifier)
            Text(
                text = title.ifBlank { query },
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
            MainScreenDivider(modifier = Modifier)
            ListOfResults(list = list, navController = navController)
        }
        else {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), contentAlignment = Alignment.Center){
                Text(text = "No such currency available!! \n\nEnter Valid queries!!", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun ListOfResults(list: List<Coins>, navController: NavHostController) {
    LazyColumn(
        Modifier
            .fillMaxSize()
    ) {
        items(list.size) { idx ->
            (list[idx]).also {
                ListItemComposable(
                    modifier = Modifier.padding(vertical = 4.dp),
                    coin = it,
                    idx = idx + 1,
                    navController = navController
                )
            }
        }
    }
}