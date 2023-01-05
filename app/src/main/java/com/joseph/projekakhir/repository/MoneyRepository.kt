package com.joseph.projekakhir.repository

import com.joseph.projekakhir.retrofit.EndPointApi
import javax.inject.Inject

class MoneyRepository @Inject constructor(private val api: EndPointApi) {
//    add money
    fun addPemasukan(id_user:String,total_money:String,note:String,status:String) =
        api.addPemasukan(id_user,total_money,note,status)

//    update money
    fun updatePemasukan(id:String,total_money:String,note:String,status:String) =
        api.updatePemasukan(id,total_money,note,status)

//    delete money
    fun deleteMoney(id:String) = api.deleteMoney(id)

//    get money data
    suspend fun ambilUangPemasukan(id:Int)=api.ambilUangPemasukan(id)
    suspend fun ambilUangPengeluaran(id:Int)=api.ambilUangPengeluaran(id)

//    get total money data
    suspend fun ambilSemuaPemasukan(id:Int)=api.ambilSemuaPemasukan(id)
    suspend fun ambilSemuaPengeluaran(id:Int)=api.ambilSemuaPengeluaran(id)

//    get money total
    suspend fun ambilSemuaUang(id:Int)=api.ambilSemuaUang(id)

}
