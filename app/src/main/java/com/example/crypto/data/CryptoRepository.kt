package com.example.crypto.data

import com.example.crypto.model.Coins.CryptoDetail
import com.example.crypto.model.coinDetail.CoinCompleteDetail
import com.example.crypto.network.CryptoApiService

interface CryptoRepository {
    suspend fun getCoins(): List<CryptoDetail>

    suspend fun getCoinById(coinId: String): CoinCompleteDetail
}

class NetworkCryptoDetailRepository (
    private val apiService: CryptoApiService
): CryptoRepository {
    override suspend fun getCoins(): List<CryptoDetail> = apiService.getCoins()
    override suspend fun getCoinById(coinId: String): CoinCompleteDetail = apiService.getCoinById(coinId = coinId)
}