package com.master.recipestools.ui.recipeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.master.recipestools.data.model.Recipe
import com.master.recipestools.repository.RecipesRepository
import com.master.recipestools.ui.login.LoginResult

class RecipesListViewModel(private val recipesRepository: RecipesRepository) : ViewModel() {
    //val recipeListResult: LiveData<List<Recipe>> = _loginResult
    val recipesList = MutableLiveData<List<Recipe>>()
    val errorMessage = MutableLiveData<String>()

    fun getRecipes(){
        recipesList.postValue(recipesRepository.getRecipes())
    }
}