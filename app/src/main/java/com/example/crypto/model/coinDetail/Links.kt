package com.example.crypto.model.coinDetail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val explorer: List<String>,
    val facebook: List<String>,
    val reddit: List<String>,
    @SerialName("source_code")val source_code: List<String>,
    val website: List<String>,
    val youtube: List<String>
)