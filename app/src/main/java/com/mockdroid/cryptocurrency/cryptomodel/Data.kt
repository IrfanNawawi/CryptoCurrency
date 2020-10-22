package com.mockdroid.cryptocurrency.cryptomodel

data class Data(
    val Aggregated: Boolean,
    val Data: List<DataX>,
    val TimeFrom: Int,
    val TimeTo: Int
)