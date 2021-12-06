package com.master.recipestools.ui.recipeslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.master.recipestools.repository.RecipesRepository

class RecipesListViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RecipesListViewModel::class.java)) {
            RecipesListViewModel(RecipesRepository()) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}