package com.master.recipestools.ui

import com.master.recipestools.R
import com.master.recipestools.ui.login.LoginActivity


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.master.recipestools.*


class SplashScreenActivity: AppCompatActivity() {
    //private lateinit var preferenceHelper: PreferenceHelper
    private lateinit var mLogo : ImageView
    private val timeout: Long = 1500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        init()
        animateLogo()
        redirectionActivity()

    }

    private fun redirectionActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
            /*/**/val intent = Intent(applicationContext, MainAssociationActivity::class.java)
            startActivity(intent)
            finish()*/


        }, timeout)

    }

    private fun init(){
        mLogo = findViewById(R.id.animLogo)
    }
    private fun animateLogo(){
        val animation: Animation= AnimationUtils.loadAnimation(this,
            R.anim.anim
        )
        mLogo.startAnimation(animation)
    }

}