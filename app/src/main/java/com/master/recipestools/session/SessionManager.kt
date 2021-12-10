package com.master.recipestools.session

import android.content.Context
import android.content.SharedPreferences
import com.master.recipestools.R
import com.master.recipestools.ui.createingredient.ProfileFragment

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    private val USER_EMAIL = "user_email"
    private val USER_NAME = "user_name"

    companion object {
        const val USER_TOKEN = "user_token"

    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }
    fun saveEmailAuth(email: String) {
        val editor = prefs.edit()
        editor.putString(USER_EMAIL, email)
        editor.apply()
    }
    fun saveUserNameAuth(email: String) {
        val editor = prefs.edit()
        editor.putString(USER_NAME, email)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
    fun fetchAuthEmail(): String? {
        return prefs.getString(USER_EMAIL, null)
    }
    fun fetchAuthName(): String? {
        return prefs.getString(USER_NAME, null)
    }

}