package com.example.crypto.model.search

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("contract_address")
    val contractAddress: List<ContractAddres>,
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val rev: Int,
    val symbol: String,
    val type: String
)