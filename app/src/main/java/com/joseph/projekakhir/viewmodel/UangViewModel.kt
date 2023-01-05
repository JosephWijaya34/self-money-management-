package com.joseph.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.projekakhir.model.DataMoney
import com.joseph.projekakhir.model.Money
import com.joseph.projekakhir.model.MoneyTotal
import com.joseph.projekakhir.model.Users
import com.joseph.projekakhir.repository.MoneyRepository
import com.joseph.projekakhir.view.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UangViewModel @Inject constructor(private val repository: MoneyRepository) : ViewModel() {
    //    add uang
    fun addPemasukan(id_user:String,total_money:String,note:String,status:String)=
        repository.addPemasukan(id_user,total_money,note,status)

    //  update uang
    fun updatePemasukan(id:String,total_money:String,note:String,status:String)=
        repository.updatePemasukan(id,total_money,note,status)

    //    delete uang
    fun deleteMoney(id:String)=repository.deleteMoney(id)

    //get Uang Pemasukan data
    val _moneyPemasukan: MutableLiveData<Money> by lazy {
        MutableLiveData<Money>()
    }
    val moneyPemasukan: LiveData<Money> get()=_moneyPemasukan
    fun getMoneyPemasukan()=viewModelScope.launch {
        repository.ambilUangPemasukan(MainActivity.login_id).let { response ->
            if (response.isSuccessful) {
                _moneyPemasukan.postValue(response.body() as Money)
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }
    val _moneyPengeluaran: MutableLiveData<Money> by lazy {
        MutableLiveData<Money>()
    }
    val moneyPengeluaran: LiveData<Money> get()=_moneyPengeluaran
    fun getMoneyPengeluaran()=viewModelScope.launch {
        repository.ambilUangPengeluaran(MainActivity.login_id).let { response ->
            if (response.isSuccessful) {
                _moneyPengeluaran.postValue(response.body() as Money)
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }

//    get total money data
val _SemuaPemasukan: MutableLiveData<MoneyTotal> by lazy {
    MutableLiveData<MoneyTotal>()
}
    val SemuaPemasukan: LiveData<MoneyTotal> get()=_SemuaPemasukan

    fun getSemuaPemasukan(id:Int)=viewModelScope.launch {
        repository.ambilSemuaPemasukan(id).let { response ->
            if (response.isSuccessful) {
                _SemuaPemasukan.postValue(response.body() as MoneyTotal)
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }

    val _SemuaPengeluaran: MutableLiveData<MoneyTotal> by lazy {
        MutableLiveData<MoneyTotal>()
    }
    val SemuaPengeluaran: LiveData<MoneyTotal> get()=_SemuaPengeluaran

    fun getSemuaPengeluaran(id:Int)=viewModelScope.launch {
        repository.ambilSemuaPengeluaran(id).let { response ->
            if (response.isSuccessful) {
                _SemuaPengeluaran.postValue(response.body() as MoneyTotal)
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }

//    get money total
    val _semuaUang: MutableLiveData<MoneyTotal> by lazy {
        MutableLiveData<MoneyTotal>()
    }

    val semuaUang: LiveData<MoneyTotal> get()=_semuaUang

    fun getSemuaUang(id:Int)=viewModelScope.launch {
        repository.ambilSemuaUang(id).let { response ->
            if (response.isSuccessful) {
                _semuaUang.postValue(response.body() as MoneyTotal)
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }

}
