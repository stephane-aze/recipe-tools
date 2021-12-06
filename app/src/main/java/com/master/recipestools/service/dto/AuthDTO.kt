package com.master.recipestools.service.dto

import java.util.*

data class AuthDTO(val username: String, val email: String, val preferences: List<Locale.Category>)