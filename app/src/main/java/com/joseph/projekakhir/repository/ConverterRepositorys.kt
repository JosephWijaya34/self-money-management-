package com.joseph.projekakhir.repository

import com.joseph.projekakhir.model.Currency
import com.joseph.projekakhir.util.Resource

interface ConverterRepositorys {
    suspend fun getRates(base: String): Resource<Currency>
}