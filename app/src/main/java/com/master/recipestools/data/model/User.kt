package com.master.recipestools.data.model

data class User(val email:String,val password:String,val username: String, val preferences: List<Category>)
