package com.master.recipestools.service.dto

data class RecipeDTO(val title: String,val ingredients: List<IngredientDTO>,val instructions: List<InstructionDTO>)