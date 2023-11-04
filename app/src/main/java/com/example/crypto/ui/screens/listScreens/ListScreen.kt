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
import androidx.compose.material3.CircularProgressIndicator
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
        else -> {
            ErrorScreen()
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

@Composable
fun ListItemComposable(modifier: Modifier, coin: CryptoDetail, idx: Int, navController: NavHostController) {
    val status: String = if (coin.isActive) "active" else "inactive"
    var expanded by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.inversePrimary,
        label = "color"
    )
    Card (
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
            .clickable {
                navController
                    .navigate(Screens.COIN_DETAIL_SCREEN.name + "/${coin.id}")
            }
    ) {
        Column (
            Modifier
                .background(color = color)
                .padding(start = 16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Row (
                    Modifier
                        .weight(.72f)
                        .padding(end = 4.dp), verticalAlignment = Alignment.CenterVertically){
                    Text(text = "${idx}.")
                    Spacer(modifier = Modifier.width(6.dp))
                    Column (verticalArrangement = Arrangement.Center){
                        Text(text = coin.name, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = coin.symbol, fontSize = 14.sp)
                    }
                }
                Row (Modifier.weight(.28f), verticalAlignment = Alignment.CenterVertically){
                    Text(text = status, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(5.dp))
                    val icon = if (expanded) R.drawable.up else R.drawable.down
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
            if (expanded) {
                Column (Modifier.padding(top = 8.dp, bottom = 16.dp)){
                    Text(text = "Rank: ${coin.rank}")
                    Text(text = "Type: ${coin.type}")
                }
            }
        }
    }
}

@Composable
private fun AppBar() {
    Row (
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, modifier = Modifier
            .size(60.dp)
            .clip(
                CircleShape
            ))
        Text(text = "CryptoAnalyzer", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}
@Composable
fun ErrorScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Error!", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.tertiary)
    }
}