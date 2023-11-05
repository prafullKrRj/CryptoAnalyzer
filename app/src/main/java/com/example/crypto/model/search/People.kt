package com.example.crypto.model.search

import com.google.gson.annotations.SerializedName

data class People(
    val id: String,
    val name: String,
    @SerializedName("teams_count")
    val teamsCount: Int
)