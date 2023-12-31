package com.example.crypto.ui.screens.detailScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.data.CryptoRepository
import com.example.crypto.model.coinDetail.CoinCompleteDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface CoinDetailState {
    data class Success(val coinDetail: CoinCompleteDetail, var id: String? = null): CoinDetailState
    object Error: CoinDetailState
    object Loading: CoinDetailState
}
class DetailScreenViewModel(
    coinId: String,
    private val cryptoRepository: CryptoRepository
) : ViewModel () {
    var coinDetailState: CoinDetailState by mutableStateOf(CoinDetailState.Loading)
        private set
    init {
        getCoinById(coinId = coinId)
    }
    private fun getCoinById(coinId: String) {
        viewModelScope.launch {
            coinDetailState = CoinDetailState.Loading
            delay(350L)
            coinDetailState = try {
                CoinDetailState.Success(cryptoRepository.getCoinById(coinId))
            } catch (e: IOException) {
                CoinDetailState.Loading
            } catch (e: HttpException) {
                CoinDetailState.Loading
            }
        }
    }
}