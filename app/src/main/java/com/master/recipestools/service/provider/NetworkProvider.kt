package com.master.recipestools.service.provider


import android.util.Log
import com.master.recipestools.data.model.Category
import com.master.recipestools.data.model.Food
import com.master.recipestools.data.model.Recipe
import com.master.recipestools.service.NetworkAPI
import com.master.recipestools.service.dto.*
import com.master.recipestools.service.mapper.CategoryMapper
import com.master.recipestools.service.mapper.FoodMapper
import com.master.recipestools.service.mapper.RecipeMapper
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


const val URL = "http://recipes-tools-api.herokuapp.com/"

object NetworkProvider {
    private val networkAPI: NetworkAPI = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(NetworkAPI::class.java)
    fun getCategories(listener: NetworkListener<List<Category>>) {
        return networkAPI.getCategories().enqueue((object: Callback<List<CategoryDTO>>{
            override fun onResponse(call: Call<List<CategoryDTO>>, response: Response<List<CategoryDTO>>) {
                val responseAPI:List<CategoryDTO>? = response.body()
                responseAPI?.let {
                    val categories: List<Category> = CategoryMapper().map(it)
                    listener.onSuccess(categories)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<List<CategoryDTO>>, t: Throwable) {
                listener.onError(t)
            }

        }))
    }
    fun getRecipes(token: String,listener: NetworkListener<List<Recipe>>) {
        return networkAPI.getRecipes(token).enqueue((object: Callback<List<RecipeDTO>>{
            override fun onResponse(call: Call<List<RecipeDTO>>, response: Response<List<RecipeDTO>>) {
                val responseAPI:List<RecipeDTO>? = response.body()
                responseAPI?.let {
                    val recipes: List<Recipe> = RecipeMapper().map(it)
                    listener.onSuccess(recipes)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<List<RecipeDTO>>, t: Throwable) {
                listener.onError(t)
            }

        }))
    }
    fun createRecipe(token: String, recipe: RecipeDTO,listener: NetworkListener<String>){
        return networkAPI.postRecipe(token, recipe).enqueue((object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val responseAPI:String? = response.body()
                responseAPI?.let {

                    listener.onSuccess(it)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                listener.onError(t)
            }

        }))
    }
    fun toLogin( signInDTO: SignInDTO,listener: NetworkListener<AuthDTO>){
            return networkAPI.toLogin(signInDTO).enqueue((object: Callback<AuthDTO>{
                override fun onResponse(call: Call<AuthDTO>, response: Response<AuthDTO>) {
                    val responseAPI:AuthDTO? = response.body()
                    responseAPI?.let {

                        listener.onSuccess(it)
                    } ?: listener.onError(Exception())
                }

                override fun onFailure(call: Call<AuthDTO>, t: Throwable) {
                    listener.onError(t)
                }

            }))
        }
    fun createUser( signInDTO: AuthDTO,listener: NetworkListener<AuthDTO>){
            return networkAPI.createUser(signInDTO).enqueue((object: Callback<AuthDTO>{
                override fun onResponse(call: Call<AuthDTO>, response: Response<AuthDTO>) {
                    val responseAPI:AuthDTO? = response.body()
                    responseAPI?.let {

                        listener.onSuccess(it)
                    } ?: listener.onError(Exception())
                }

                override fun onFailure(call: Call<AuthDTO>, t: Throwable) {
                    listener.onError(t)
                }

            }))
        }

    fun getFoods(token: String,listener: NetworkListener<List<Food>>) {
        return networkAPI.getFoods(token).enqueue((object: Callback<List<FoodDTO>>{
            override fun onResponse(call: Call<List<FoodDTO>>, response: Response<List<FoodDTO>>) {
                val responseAPI:List<FoodDTO>? = response.body()
                responseAPI?.let {
                    val foods: List<Food> = FoodMapper().map(it)
                    listener.onSuccess(foods)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<List<FoodDTO>>, t: Throwable) {
                listener.onError(t)
            }

        }))
    }
    fun createFood(token: String,foodDTO: FoodDTO,listener: NetworkListener<String>) {
        return networkAPI.createFood(token,foodDTO).enqueue((object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val responseAPI:String? = response.body()
                responseAPI?.let {

                    listener.onSuccess(it)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                listener.onError(t)
            }

        }))
    }
}
interface NetworkListener<T>{
    fun onSuccess(data: T)
    fun onError(throwable: Throwable)
}