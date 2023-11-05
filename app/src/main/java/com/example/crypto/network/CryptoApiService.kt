package com.example.crypto.network

import com.example.crypto.model.coinDetail.CoinCompleteDetail
import com.example.crypto.model.Coins.CryptoDetail
import com.example.crypto.model.search.SearchFields
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApiService {


    @GET("/v1/coins")
    suspend fun getCoins(): List<CryptoDetail>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinCompleteDetail

    @GET("/v1/search")
    suspend fun getSearchAnswers(@Query("q") query: String) : SearchFields
}
