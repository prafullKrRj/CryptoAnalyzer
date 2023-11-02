package com.example.crypto.model.coinDetail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksExtended(
    @SerialName("stats")val stats: Stats,
    val type: String,
    val url: String
)