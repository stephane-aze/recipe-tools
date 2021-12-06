package com.master.recipestools.service.dto

import com.master.recipestools.data.model.Unity

data class IngredientDTO(val food:FoodDTO, val quantity: Double, val unity: Unity)
