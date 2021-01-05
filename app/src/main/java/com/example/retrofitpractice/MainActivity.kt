package com.example.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.retrofitpractice.databinding.ActivityMainBinding
import com.example.retrofitpractice.retrofit.RetrofitManager

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.loginButton.setOnClickListener{
            Log.d(TAG, "MainActivity - onCreate: ");
            val username: String = binding.usernameInput.text.toString()
            val password: String = binding.passwordInput.text.toString()
            loginRequest(username, password)
        }
    }

    fun loginRequest(username: String, password: String){
        var dialogBuilder = AlertDialog.Builder(this@MainActivity)
        if(username.isEmpty() || password.isEmpty()){
            dialogBuilder.setTitle("알림")
            dialogBuilder.setMessage("빈 칸을 전부 채워주세요.")
            dialogBuilder.setPositiveButton("확인", null)
            dialogBuilder.show()
        }else{
            RetrofitManager.instance.login(username=username, password=password, completion = {
                loginResponse, response ->
                    when(loginResponse){
                        LoginResponse.FAIL -> {
                            dialogBuilder.setTitle("알림")
                            dialogBuilder.setMessage("로그인 실패")
                            dialogBuilder.setPositiveButton("확인", null)
                            dialogBuilder.show()
                        }

                        LoginResponse.OK -> {
                            dialogBuilder.setTitle("알림")
                            dialogBuilder.setMessage("로그인 성공")
                            dialogBuilder.setPositiveButton("확인", null)
                            dialogBuilder.show()
                        }
                    }
            })
        }
    }
}