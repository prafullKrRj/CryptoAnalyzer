package com.example.crypto.ui.screens.commons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    modifier: Modifier,
    onSearched: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var userSearch by rememberSaveable {
        mutableStateOf("")
    }
    val color = MaterialTheme.colorScheme.tertiaryContainer
    OutlinedTextField(
        modifier = modifier,
        value = userSearch,
        onValueChange = {
            userSearch = it
        },
        shape = RoundedCornerShape(55.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = color,
            focusedBorderColor = color,
            unfocusedBorderColor = color,
            focusedLabelColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
                       IconButton(onClick = {
                           if (userSearch.isNotEmpty()) {
                               onSearched(userSearch)
                           }
                       }) {
                           Icon(imageVector = Icons.Default.Send, contentDescription = null)
                       }
        },
        label = { Text(text = "coin name") },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            keyboardController?.hide()
            if (userSearch.isNotEmpty()) {
                onSearched(userSearch)
            }
        })
    )
}