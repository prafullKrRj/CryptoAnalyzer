package com.example.crypto.ui.screens.listScreens.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavHostController
import com.example.crypto.R
import com.example.crypto.Screens
import com.example.crypto.model.Coins.Coins

@Composable
fun ListItemComposable(modifier: Modifier, coin: Coins, idx: Int, navController: NavHostController) {
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
                ExpandedCard(coin = coin, modifier = Modifier)
            }
        }
    }
}
