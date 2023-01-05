package com.joseph.projekakhir.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.joseph.projekakhir.databinding.ActivityRegisterBinding
import com.joseph.projekakhir.model.SubmitRegister
import com.joseph.projekakhir.model.Updateuser
import com.joseph.projekakhir.view.MainActivity.Companion.login_id
import com.joseph.projekakhir.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityRegisterBinding
    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        try {
            if (intent.getStringExtra("editUser") == "editUser") {
                editUser()
            }else{
                registerClick()
            }
        }catch (e:Exception){
            Log.e("Error",e.message.toString())
        }
    }

    private fun editUser(){
        viewBind.backRegisterImageButton.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("editUser", "editUser")
            }
            startActivity(myIntent)
        }
        val emailUser = intent.getStringExtra("email").toString()
        viewBind.tagLineRegisTextView.text="Edit Profile"
        viewBind.isiRegisTextView.text="edit profil anda"
        viewBind.daftarButton.text="Simpan Edit"
        viewBind.EmailRegTextInputEditText.setText(emailUser)
        // make email edit text readonly
        viewBind.EmailRegTextInputEditText.isEnabled=false

            //        edit data
        viewBind.daftarButton.setOnClickListener {
            val id = login_id.toString()
            val username=viewBind.namaPenggunaRegTextInputEditText.text.toString().trim()
            val email=viewBind.EmailRegTextInputEditText.text.toString().trim()
            val image = ""
            val password=viewBind.passwordRegTextInputEditText.text.toString().trim()

            //            checker
            if (username.isEmpty()) {
                viewBind.namaPenggunaRegTextInputLayout.error="Tolong isi kolom nama"
            } else {
                viewBind.namaPenggunaRegTextInputLayout.error=""
            }

            if (email.isEmpty()) {
                viewBind.EmailRegTextInputLayout.error="Tolong isi kolom email"
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    viewBind.EmailRegTextInputLayout.error="Tolong masukan alamat email yang benar"
                } else {
                    viewBind.EmailRegTextInputLayout.error=""
                }
            }

            if (password.isEmpty()) {
                viewBind.passwordRegTextInputLayout.error="Tolong isi kolom password"
            } else {
                viewBind.passwordRegTextInputLayout.error=""
            }

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
                viewModel=ViewModelProvider(this)[UsersViewModel::class.java]
                viewModel.updateUser(id,email,username,image,password).enqueue(object :Callback<Updateuser> {
                    override fun onResponse(call: Call<Updateuser>, response: Response<Updateuser>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@RegisterActivity, "Berhasil Edit Akun", Toast.LENGTH_SHORT).show()
                            val myIntent = Intent(this@RegisterActivity, MainActivity::class.java).apply {
                                putExtra("editUser", "editUser")
                            }
                            startActivity(myIntent)
                        } else {
                            Toast.makeText(this@RegisterActivity, "Gagal Edit Akun", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Updateuser>, t: Throwable) {
                        Log.d("RegisterActivity", "onFailure: ${t.message}")
                    }
                })
            }
        }
    }

    private fun registerClick() {
        //        back button
        viewBind.backRegisterImageButton.setOnClickListener {
            val myIntent = Intent(this, IntroActivity::class.java)
            startActivity(myIntent)
        }
        viewBind.daftarButton.setOnClickListener {
            val username=viewBind.namaPenggunaRegTextInputEditText.text.toString().trim()
            val email=viewBind.EmailRegTextInputEditText.text.toString().trim()
            val image = ""
            val password=viewBind.passwordRegTextInputEditText.text.toString().trim()

//            checker
            if (username.isEmpty()) {
                viewBind.namaPenggunaRegTextInputLayout.error="Tolong isi kolom nama"
            } else {
                viewBind.namaPenggunaRegTextInputLayout.error=""
            }

            if (email.isEmpty()) {
                viewBind.EmailRegTextInputLayout.error="Tolong isi kolom email"
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    viewBind.EmailRegTextInputLayout.error="Tolong masukan alamat email yang benar"
                } else {
                    viewBind.EmailRegTextInputLayout.error=""
                }
            }

            if (password.isEmpty()) {
                viewBind.passwordRegTextInputLayout.error="Tolong isi kolom password"
            } else {
                viewBind.passwordRegTextInputLayout.error=""
            }

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                viewModel=ViewModelProvider(this)[UsersViewModel::class.java]
                viewModel.addUser(email, username, image, password).enqueue(object :Callback<SubmitRegister> {
                    override fun onResponse(call: Call<SubmitRegister>, response: Response<SubmitRegister>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@RegisterActivity, "Berhasil mendaftar", Toast.LENGTH_SHORT).show()
                            val myIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(myIntent)
                        } else {
                            Toast.makeText(this@RegisterActivity, "Gagal mendaftar", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<SubmitRegister>, t: Throwable) {
                        Log.d("RegisterActivity", "onFailure: ${t.message}")
                    }
                })
            }
        }
    }
}