package com.example.crypto.ui.screens.detailScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.crypto.model.coinDetail.CoinCompleteDetail
import com.example.crypto.model.coinDetail.TeamMember

@Composable
fun TeamMembers(list: List<TeamMember>) {
    val teamMember = if (list.size > 1) "Team Members" else "Team Member"
    Text(text = teamMember, fontSize = MaterialTheme.typography.titleLarge.fontSize, fontWeight = FontWeight.SemiBold)
    Spacer(modifier = Modifier.height(6.dp))
    Column (Modifier.padding(10.dp)){
        list.forEach {
            OneTeamMember(teamMember = it)
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .height(2.dp))

        }
    }
}

@Composable
fun OneTeamMember(teamMember: TeamMember) {
    Text(text = teamMember.name, fontSize = MaterialTheme.typography.titleMedium.fontSize, color = MaterialTheme.colorScheme.secondary)
    Text(text = teamMember.position)
    Spacer(modifier = Modifier.height(3.dp))
}