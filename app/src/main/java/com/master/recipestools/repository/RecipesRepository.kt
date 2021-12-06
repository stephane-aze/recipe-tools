package com.master.recipestools.repository

import com.master.recipestools.data.model.Recipe
import com.master.recipestools.service.NetworkAPI
import com.master.recipestools.service.provider.NetworkListener
import com.master.recipestools.service.provider.NetworkProvider

class RecipesRepository {
    fun getRecipes(): List<Recipe>{
        var recipes: List<Recipe> = emptyList()

        NetworkProvider.getRecipes(token= "",object: NetworkListener<List<Recipe>>{
            override fun onSuccess(data: List<Recipe>) {
                recipes= data

            }

            override fun onError(throwable: Throwable) {

            }

        })
        return recipes
    }
}