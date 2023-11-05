package com.example.crypto.model.search

import com.google.gson.annotations.SerializedName

data class SearchFields(
    val currencies: List<Currency>,
    val exchanges: List<Exchange>,
    @SerializedName("icos")
    val icos: List<Ico>,
    val people: List<People>,
    val tags: List<Tag>
)