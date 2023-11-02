package com.example.crypto.model.coinDetail


import kotlinx.serialization.Serializable

@Serializable
data class TeamMembers(
    val id: String,
    val name: String,
    val position: String
)