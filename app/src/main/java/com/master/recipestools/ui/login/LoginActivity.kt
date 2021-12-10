package com.master.recipestools.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.master.recipestools.MainActivity
import com.master.recipestools.databinding.ActivityLoginBinding

import com.master.recipestools.service.dto.AuthDTO
import com.master.recipestools.service.dto.SignInDTO
import com.master.recipestools.service.provider.NetworkListener
import com.master.recipestools.service.provider.NetworkProvider
import com.master.recipestools.session.SessionManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginEmail : EditText
    private lateinit var loginPassword : EditText
    private lateinit var btnLogin : Button
    private lateinit var mProgressBar: ProgressBar
    private val TAG = "LoginActivity"
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        binding.login.setOnClickListener {

            toLogin()
        }
    }


    private fun init(){
        loginPassword = binding.password
        loginEmail = binding.username
        btnLogin = binding.login
        //preferenceHelper = PreferenceHelper(this)
        mProgressBar = binding.loading
        sessionManager = SessionManager(this)

    }


    private fun toLogin() {

        showSimpleProgressDialog()
        val signIn= SignInDTO(email = loginEmail.text.toString(),password = loginPassword.text.toString())
        NetworkProvider.toLogin(signIn,listener=object: NetworkListener<AuthDTO> {
            override fun onSuccess(data: AuthDTO) {
                removeSimpleProgressDialog()
                sessionManager.saveAuthToken(data.token?:"")
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("TOKEN",data.token)
                startActivity(intent)
                finish()


                }

            override fun onError(throwable: Throwable) {
                removeSimpleProgressDialog()
                Toast.makeText(this@LoginActivity,"Erreur:${throwable.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
        })



    }

    private fun showSimpleProgressDialog() {
        val visibility = if (mProgressBar.visibility == View.GONE) View.VISIBLE else View.GONE
        mProgressBar.visibility = visibility
    }

    private fun removeSimpleProgressDialog() {
        if (mProgressBar.visibility==View.VISIBLE) {
            mProgressBar.visibility=View.GONE
        }
    }
}