package com.example.crypto.ui.screens.detailScreen.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.crypto.R

@Composable
fun DocumentComposable(links: String) {
    val context = LocalContext.current
    Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "Whitepaper:  ",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.SemiBold
        )
        IconButton(onClick = {
            openLink(link = links, context)
        }) {
            Icon(painter = painterResource(id = R.drawable.baseline_book_24), contentDescription = null, tint = Color.White)
        }
    }
}
private fun openLink(link: String, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, link)
        setPackage("com.android.chrome")
    }
    context.startActivity(intent)
}