package com.example.crypto.model.Coins

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoDetail(
    val id: String,
    @SerialName(value = "is_active") val is_active: Boolean,
    @SerialName("is_new") val is_new: Boolean,
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
        isActive = is_active
    )
}