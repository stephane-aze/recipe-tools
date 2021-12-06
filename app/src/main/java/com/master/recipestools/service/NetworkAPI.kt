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
    @GET("api/recipe/")
    fun getRecipes(@Header("auth-token") token: String) : Call<List<RecipeDTO>>
    @POST("api/recipe/")
    fun postRecipe(@Header("auth-token") token: String,@Body recipeDTO: RecipeDTO) : Call<RecipeResponseDTO>
    @GET("api/category")
    fun getCategories(@Header("auth-token") token: String/**/) : Call<List<CategoryDTO>>
    @GET("api/recipe/{id}")
    fun getRecipeById(@Header("auth-token") token: String, @Path("id") id: Long): Call<RecipeDTO>
    @GET("api/ingredient/")
    fun getIngredients(@Header("auth-token") token: String/**/) : Call<List<IngredientDTO>>
    @POST("api/ingredient")
    fun postIngredient(@Header("auth-token") token: String,@Body ingredientDTO: IngredientDTO) : Call<List<IngredientDTO>>


}