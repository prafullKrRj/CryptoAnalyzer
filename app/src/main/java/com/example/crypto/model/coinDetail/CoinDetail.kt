package com.example.crypto.model.coinDetail

data class CoinDetail (
    val description: String,
    val id: String,
    val isActive: Boolean,
    val team: List<TeamMembers>,
    val rank: Int,
    val tags: List<Tag>,
)
