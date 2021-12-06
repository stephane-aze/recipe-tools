package com.master.recipestools.service.provider


import android.util.Log
import com.master.recipestools.data.model.Category
import com.master.recipestools.data.model.Recipe
import com.master.recipestools.service.NetworkAPI
import com.master.recipestools.service.dto.AuthDTO
import com.master.recipestools.service.dto.CategoryDTO
import com.master.recipestools.service.dto.RecipeDTO
import com.master.recipestools.service.dto.SignInDTO
import com.master.recipestools.service.mapper.CategoryMapper
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
    fun getCategories(token: String,listener: NetworkListener<List<Category>>) {
        return networkAPI.getCategories(token).enqueue((object: Callback<List<CategoryDTO>>{
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
    }fun getRecipes(token: String,listener: NetworkListener<List<Recipe>>) {
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
    /*fun toLogin(userSignIn: SignInDTO,listener: NetworkListener<Auth>){
        return networkAPI.toLogin(userSignIn)
        .enqueue(object : Callback<AuthDTO> {
            override fun onResponse(
                call: Call<AuthDTO>,
                response: Response<AuthDTO>
            ) {

                val authResponse:AuthDTO? = response.body()
                authResponse?.let {notNullAuthResponse->
                    val auth: Auth = Auth(email = notNullAuthResponse.email?:"",password = "",name = notNullAuthResponse.name,token = notNullAuthResponse.token, address = notNullAuthResponse.address,phone = notNullAuthResponse.phone,association = notNullAuthResponse.association,role = notNullAuthResponse.role,isValid = notNullAuthResponse.isValid,id = notNullAuthResponse.id)
                    listener.onSuccess(auth)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<AuthDTO>, t: Throwable) {
                listener.onError(t)
            }
        })
    }

    fun getProducts(token: String,listener: NetworkListener<List<Product>>) {
        return networkAPI.getProducts(token).enqueue((object: Callback<List<ProductDTO>>{
            override fun onResponse(call: Call<List<ProductDTO>>, response: Response<List<ProductDTO>>) {
                val responseAPI:List<ProductDTO>? = response.body()
                responseAPI?.let {
                    val categories: List<Product> = ProductMapper().map(it)
                    listener.onSuccess(categories)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<List<ProductDTO>>, t: Throwable) {
                listener.onError(t)
            }

        }))
    }
    fun getCart(token: String,listener: NetworkListener<Cart>){
        return networkAPI.getCart(token).enqueue((object :Callback<CartDTO>{
            override fun onResponse(call: Call<CartDTO>, response: Response<CartDTO>) {
                val responseAPI:CartDTO? = response.body()
                responseAPI?.let {
                    val categories = Cart(userId = it.userId,items = CartIdemMapper().map(it.items?: emptyList()),subTotal = it.subTotal?:0.0,status = it.status,_id=it._id)
                    listener.onSuccess(categories)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<CartDTO>, t: Throwable) {
                listener.onError(t)
            }

        }))
    }
    fun removeItemCart(token: String,item: ItemCartDTO,listener: NetworkListener<Cart>){
        return networkAPI.removeItemToCart(itemDTO = item,token).enqueue((object :Callback<CartDTO>{
            override fun onResponse(call: Call<CartDTO>, response: Response<CartDTO>) {
                val responseAPI:CartDTO? = response.body()
                responseAPI?.let {
                    val categories: Cart = Cart(userId = it.userId,items = CartIdemMapper().map(it.items?: emptyList()),subTotal = it.subTotal?:0.0,status = it.status,_id=it._id)
                    listener.onSuccess(categories)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<CartDTO>, t: Throwable) {
                listener.onError(t)
            }

        }))
    }
    fun addItemCart(token: String,item: ItemCartDTO,listener: NetworkListener<Cart>){
        return networkAPI.addItemToCart(itemDTO = item,token).enqueue((object :Callback<CartDTO>{
            override fun onResponse(call: Call<CartDTO>, response: Response<CartDTO>) {
                val responseAPI:CartDTO? = response.body()
                responseAPI?.let {
                    val categories: Cart = Cart(userId = it.userId,items = CartIdemMapper().map(it.items?: emptyList()),subTotal = it.subTotal?:0.0,status = it.status,_id=it._id)
                    listener.onSuccess(categories)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<CartDTO>, t: Throwable) {
                listener.onError(t)
            }

        }))
    }
    fun createProject(token: String,project: ProjectDTO,listener: NetworkListener<ProjectDTO>){
        return networkAPI.createProject(project = project,token).enqueue((object :Callback<ProjectDTO>{
            override fun onResponse(call: Call<ProjectDTO>, response: Response<ProjectDTO>) {
                val responseAPI:ProjectDTO? = response.body()
                Log.d("PIPI",response.body().toString())
                responseAPI?.let {
                    listener.onSuccess(it)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<ProjectDTO>, t: Throwable) {
                Log.d("PIPI","yjgkgkgkg")
                listener.onError(t)
            }

        }))
    }
    fun emptyCart(token: String,listener: NetworkListener<Cart>){
        return networkAPI.emptyCart(token).enqueue((object :Callback<CartDTO>{
            override fun onResponse(call: Call<CartDTO>, response: Response<CartDTO>) {
                val responseAPI:CartDTO? = response.body()
                responseAPI?.let {
                    val categories: Cart = Cart(userId = it.userId,items = CartIdemMapper().map(it.items?: emptyList()),subTotal = it.subTotal?:0.0,status = it.status,_id=it._id)
                    listener.onSuccess(categories)
                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<CartDTO>, t: Throwable) {
                listener.onError(t)
            }

        }))
    }

*/

}
interface NetworkListener<T>{
    fun onSuccess(data: T)
    fun onError(throwable: Throwable)
}