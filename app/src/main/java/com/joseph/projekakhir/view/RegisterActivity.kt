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

        registerClick()

//        back button
        viewBind.backRegisterImageButton.setOnClickListener {
            val myIntent = Intent(this, IntroActivity::class.java)
            startActivity(myIntent)
        }
    }

    private fun registerClick() {
        viewBind.daftarButton.setOnClickListener {
//            User=DataUser(
//                username=viewBind.namaPenggunaRegTextInputEditText.text!!.toString().trim(),
//                email=viewBind.EmailRegTextInputEditText.text!!.toString().trim(),
//                image= "",
//                password=viewBind.passwordRegTextInputEditText.text!!.toString().trim()
//            )
//            viewModel.addUser(User)

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
                viewModel.addUser(email,username, image, password).enqueue(object : Callback<SubmitRegister> {
                    override fun onResponse(call: Call<SubmitRegister>, response: Response<SubmitRegister>) {
                        if (response.isSuccessful) {
                            val myIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            Toast.makeText(this@RegisterActivity, "Register Berhasil", Toast.LENGTH_LONG).show()
                            myIntent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(myIntent)
                            finish()
                        } else {
                            Toast.makeText(this@RegisterActivity, "Register Gagal", Toast.LENGTH_LONG).show()
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