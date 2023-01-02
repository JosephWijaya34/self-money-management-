package com.joseph.projekakhir.model

data class Currency(
    val amount: Double,
    val base: String,
    val date: String,
    val rates: Rates
)