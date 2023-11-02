package com.example.crypto.model.coinDetail

import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    val contributors: Int,
    val followers: Int,
    val stars: Int,
    val subscribers: Int
)