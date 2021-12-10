package com.master.recipestools.ui.recipeslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.master.recipestools.data.model.Recipe
import com.master.recipestools.repository.RecipesRepository

class RecipesListViewModel(private val recipesRepository: RecipesRepository) : ViewModel() {
    //val recipeListResult: LiveData<List<Recipe>> = _loginResult
    val recipesList = MutableLiveData<List<Recipe>>()
    val errorMessage = MutableLiveData<String>()
    init {
        fetchRecipes()
    }
    fun fetchRecipes(){
        recipesList.postValue(recipesRepository.getRecipes(listener = {
            recipesList.postValue(it)
        }))
    }

}