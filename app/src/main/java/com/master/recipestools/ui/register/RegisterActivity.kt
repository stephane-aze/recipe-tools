package com.master.recipestools.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.master.recipestools.MainActivity

import com.master.recipestools.data.model.Category
import com.master.recipestools.databinding.ActivityRegisterBinding
import com.master.recipestools.service.dto.AuthDTO
import com.master.recipestools.service.dto.SignInDTO
import com.master.recipestools.service.provider.NetworkListener
import com.master.recipestools.service.provider.NetworkProvider
import com.master.recipestools.session.SessionManager

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var loginEmail : EditText
    private lateinit var loginPassword : EditText
    private lateinit var btnRegister : Button
    private lateinit var mProgressBar: ProgressBar
    private lateinit var sessionManager: SessionManager
    private lateinit var preferences: MutableList<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        getCategories()
        binding.register.setOnClickListener {
            registerUser()
        }

    }


    private fun getCategories() {
        NetworkProvider.getCategories(listener = object: NetworkListener<List<Category>> {


            override fun onError(throwable: Throwable) {
                return
            }

            override fun onSuccess(data: List<Category>) {
                val dataIterator = data as ListIterator<Category>

                dataIterator.forEach{ cat ->
                    //binding.chipGroup.addChip(myContext,it.name)

                    val chip = Chip(this@RegisterActivity)
                    chip.text=cat.name
                    chip.isClickable = true
                    chip.isCheckable = true
                    chip.isCheckedIconVisible = false
                    chip.isFocusable = true
                    /*chip.setTextColor(
                        ContextCompat.getColorStateList(
                            this@RegisterActivity,
                            R.color.text_state_chip_colors
                        )
                    )
                    chip.chipBackgroundColor =
                        ContextCompat.getColorStateList(context!!, R.color.bg_state_chip_list)*/

                    chip.setOnClickListener {
                        preferences.add(cat)
                    }
                    binding.chipGroup.addView(chip)

                }
            }
        })


    }
    private fun init(){
        loginPassword = binding.password
        loginEmail = binding.username
        btnRegister = binding.register
        mProgressBar = binding.loading
        sessionManager = SessionManager(this)

    }
    private fun registerUser() {
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
                Toast.makeText(applicationContext,"Erreur:${throwable.localizedMessage}",Toast.LENGTH_SHORT).show()
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