package com.example.crypto.model.search

import com.google.gson.annotations.SerializedName

data class Ico(
    val id: String,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rev: Int,
    val symbol: String
)