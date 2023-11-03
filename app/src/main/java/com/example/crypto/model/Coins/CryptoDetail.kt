package com.example.crypto.model.Coins

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class CryptoDetail(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)
fun CryptoDetail.getCoins(): Coins{
    return Coins(
        name = name,
        symbol = symbol,
        id = id,
        rank = rank,
        isActive = isActive
    )
}