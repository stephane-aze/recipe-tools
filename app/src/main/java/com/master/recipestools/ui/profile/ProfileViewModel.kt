package com.master.recipestools.ui.createingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.master.recipestools.session.SessionManager
import dagger.hilt.android.internal.Contexts.getApplication


class ProfileViewModel(var sessionManager: SessionManager): ViewModel() {

    private val _textEmail = MutableLiveData<String>().apply {
        value = sessionManager.fetchAuthEmail()
    }
    val textEmail: LiveData<String> = _textEmail
    private val _textName = MutableLiveData<String>().apply {
        value = sessionManager.fetchAuthEmail()
    }
    val textName: LiveData<String> = _textName

}