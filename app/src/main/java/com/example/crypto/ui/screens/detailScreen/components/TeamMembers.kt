package com.example.crypto.ui.screens.detailScreen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.crypto.R
import com.example.crypto.model.coinDetail.CoinCompleteDetail
import com.example.crypto.model.coinDetail.TeamMember

@Composable
fun TeamMembers(list: List<TeamMember>) {
    val teamMember = if (list.size > 1) "Team Members" else "Team Member"
    Text(text = teamMember, fontSize = MaterialTheme.typography.titleLarge.fontSize, fontWeight = FontWeight.SemiBold)
    Spacer(modifier = Modifier.height(6.dp))
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var showIcon by remember {
        mutableStateOf(false)
    }

    Column (Modifier.padding(10.dp).animateContentSize(
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )){
        if (list.size > 4) {
            showIcon = true
            for (i in 1..4) {
                OneTeamMember(teamMember = list[i])
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .height(2.dp))
            }
        }
        if (isExpanded) {
            var i: Int = 4
            while (i < list.size) {
                OneTeamMember(teamMember = list[i])
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .height(2.dp))
                i++;
            }
        }
        if (showIcon) {
            Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                val icon = if (isExpanded) R.drawable.up else R.drawable.down
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Composable
fun OneTeamMember(teamMember: TeamMember) {
    Text(text = teamMember.name, fontSize = MaterialTheme.typography.titleMedium.fontSize, color = MaterialTheme.colorScheme.secondary)
    Text(text = teamMember.position)
    Spacer(modifier = Modifier.height(3.dp))
}