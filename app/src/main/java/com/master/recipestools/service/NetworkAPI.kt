package com.master.recipestools.service


import com.master.recipestools.service.dto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface NetworkAPI {
    @POST("api/auth/login")
    fun toLogin(@Body authDTO: SignInDTO
    ): Call<AuthDTO>
    @POST("api/auth/register")
    fun createUser(@Body authDTO: AuthDTO
    ): Call<AuthDTO>
    @GET("api/recipe/")
    fun getRecipes(@Header("auth-token") token: String) : Call<List<RecipeDTO>>
    @GET("api/foods/")
    fun getFoods(@Header("auth-token") token: String) : Call<List<FoodDTO>>
    @POST("api/recipe/")
    fun postRecipe(@Header("auth-token") token: String,@Body recipeDTO: RecipeDTO) : Call<String>
    @GET("api/category")
    fun getCategories() : Call<List<CategoryDTO>>
    @GET("api/recipe/{id}")
    fun getRecipeById(@Header("auth-token") token: String, @Path("id") id: Long): Call<RecipeDTO>
    @POST("api/food")
    fun createFood(@Header("auth-token") token: String,@Body foodDto: FoodDTO) : Call<String>


}