package com.master.recipestools.service.dto

import com.master.recipestools.data.model.Category
import java.util.*

data class AuthDTO(val username: String, val email: String, val preferences: List<Category>, val token: String?)