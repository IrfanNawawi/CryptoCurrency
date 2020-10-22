package com.mockdroid.cryptocurrency.cryptomodel

data class DataX(
    val close: Double,
    val conversionSymbol: String,
    val conversionType: String,
    val high: Double,
    val low: Double,
    val open: Double,
    val time: Int,
    val volumefrom: Double,
    val volumeto: Double
)