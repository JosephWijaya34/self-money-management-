package com.joseph.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.projekakhir.model.Users
import com.joseph.projekakhir.repository.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: EndPointRepository) : ViewModel() {
    //    private val justAUser = DataUser(
//        email = "joseph@gmail.com",
//        id = 3,
//        image = "kebaya",
//        password = "1245",
//        username = "josephkeren"
//    )
//    create User insert to api
//    fun addUser(userdataX: DataUser)=viewModelScope.launch {
//        repository.addUser(userdataX)
//    }
    fun addUser(email:String,username:String,image:String,password:String)=
        repository.addUser(email,username,image,password)

    fun loginUser(email:String,password:String)=repository.loginUser(email,password)

    suspend fun getUserById(id:Int)=repository.getUserById(id)

    //get user by id
    val _user : MutableLiveData<Users> by lazy {
        MutableLiveData<Users>()
    }

    val user : LiveData<Users>
        get() = _user

    fun getUserbyId(id:Int) = viewModelScope.launch {
        repository.getUserById(id).let {
                response ->
            if (response.isSuccessful){
                _user.postValue(response.body())
            }else{
                Log.e("Get Data","Failed!")
            }
        }
    }


}