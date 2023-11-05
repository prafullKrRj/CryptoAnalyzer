package com.example.crypto.data

import com.example.crypto.network.CryptoApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val cryptoDetailRepository: CryptoRepository
}
class DefaultAppContainer: AppContainer {
    private val baseUrl =
        "https://api.coinpaprika.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: CryptoApiService by lazy {
        retrofit.create(CryptoApiService::class.java)
    }

    override val cryptoDetailRepository: CryptoRepository by lazy {
        NetworkCryptoDetailRepository(apiService = retrofitService)
    }
}