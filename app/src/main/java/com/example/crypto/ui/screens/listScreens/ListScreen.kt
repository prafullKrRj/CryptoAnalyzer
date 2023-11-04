package com.example.crypto.ui.screens.listScreens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.crypto.R
import com.example.crypto.Screens
import com.example.crypto.model.Coins.CryptoDetail
import com.example.crypto.ui.screens.commons.ErrorScreen
import com.example.crypto.ui.screens.commons.LoadingScreen
import com.example.crypto.ui.screens.listScreens.components.AppBar
import com.example.crypto.ui.screens.listScreens.components.ListItemComposable

@Composable
fun ListScreen(
    navController: NavHostController,
    savedStateHandle: SavedStateHandle
) {
    val viewModel: ListScreenViewModel =
        viewModel(factory = ListScreenViewModel.Factory)
    val listState: ListState = viewModel.listState
    when (listState) {
        is ListState.Success -> {
            MainScreen(coins = listState.details,
                modifier = Modifier,
                navController = navController,
                savedStateHandle = savedStateHandle
            )
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
fun MainScreen(coins: List<CryptoDetail>, modifier: Modifier, navController: NavHostController, savedStateHandle: SavedStateHandle) {
    Scaffold (
        topBar = { AppBar() }
    ) { paddingValues ->
        Box(modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(bottom = 16.dp)
            .padding(horizontal = 16.dp)) {
            LazyColumn {
                items(coins.size) { idx ->
                    val coin = (coins[idx]).also {
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
    }
}