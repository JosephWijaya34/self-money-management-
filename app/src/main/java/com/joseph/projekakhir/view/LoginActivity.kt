package com.joseph.projekakhir.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.ActivityLoginBinding
import com.joseph.projekakhir.model.SubmitLogin
import com.joseph.projekakhir.model.SubmitRegister
import com.joseph.projekakhir.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityLoginBinding
    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        //        back button
        viewBind.backLoginImageButton.setOnClickListener {
            val myIntent = Intent(this, IntroActivity::class.java)
            startActivity(myIntent)
        }

        loginClick()
    }

    private fun loginClick() {
        viewBind.loginButton.setOnClickListener {
            val email=viewBind.EmailLogTextInputEditText.text.toString().trim()
            val password=viewBind.passwordLogTextInputEditText.text.toString().trim()

            //            checker
            if (email.isEmpty()) {
                viewBind.EmailLogTextInputLayout.error="Tolong isi kolom nama"
            } else {
                viewBind.EmailLogTextInputLayout.error=""
            }

            if (password.isEmpty()) {
                viewBind.passwordLogTextInputLayout.error="Tolong isi kolom password"
            } else {
                viewBind.passwordLogTextInputLayout.error=""
            }

//            check data with api
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
                viewModel.loginUser(email, password).enqueue(object :Callback<SubmitLogin> {
                    override fun onResponse(
                        call: Call<SubmitLogin>,
                        response: Response<SubmitLogin>
                    ) {
                        if (response.isSuccessful) {
                            val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
                                .putExtra("login_id", response.body()?.user_id)
//                            Toast.makeText(this@LoginActivity, response.body()?.user_id.toString(), Toast.LENGTH_SHORT).show()
                            startActivity(myIntent)
                        } else {
                            Toast.makeText(this@LoginActivity, "Login gagal", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<SubmitLogin>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Login gagal", Toast.LENGTH_SHORT).show()
                    }
                })


            }
        }
    }
}

