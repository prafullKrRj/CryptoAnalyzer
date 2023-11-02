package com.example.crypto.data

import com.example.crypto.network.CryptoApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val cryptoDetailRepository: CryptoRepository
}
class DefaultAppContainer: AppContainer {
    private val baseUrl =
        "https://api.coinpaprika.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: CryptoApiService by lazy {
        retrofit.create(CryptoApiService::class.java)
    }

    override val cryptoDetailRepository: CryptoRepository by lazy {
        NetworkCryptoDetailRepository(apiService = retrofitService)
    }
}