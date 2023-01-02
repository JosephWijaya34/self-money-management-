package com.joseph.projekakhir.repository

import com.joseph.projekakhir.retrofit.EndPointApi
import javax.inject.Inject

class ConverterRepository @Inject constructor(private val api: EndPointApi) {
    suspend fun getCurrency() = api.ambilCurrency()
}