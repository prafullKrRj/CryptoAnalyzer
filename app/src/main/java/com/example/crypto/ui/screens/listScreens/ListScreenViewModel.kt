package com.example.crypto.ui.screens.listScreens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.crypto.CryptoApplication
import com.example.crypto.data.CryptoRepository
import com.example.crypto.model.Coins.CryptoDetail
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface ListState {
    data class Success(val details: List<CryptoDetail>): ListState
    object Error: ListState
    object Loading: ListState
}

class ListScreenViewModel(
    private val cryptoRepository: CryptoRepository
): ViewModel() {

    var listState: ListState by mutableStateOf(ListState.Error)
        private set

    init {
        getCoins()
    }
    private fun getCoins() {
        viewModelScope.launch {
            listState = ListState.Loading
            listState = try {
                ListState.Success(cryptoRepository.getCoins())
            } catch (e: IOException) {
                ListState.Loading
            } catch (e: HttpException) {
                ListState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CryptoApplication)
                val cryptoRepository = application.container.cryptoDetailRepository
                ListScreenViewModel(cryptoRepository = cryptoRepository)
            }
        }
    }
}