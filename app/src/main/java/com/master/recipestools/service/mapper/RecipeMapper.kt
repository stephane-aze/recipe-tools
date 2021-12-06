package com.master.recipestools.service.mapper

import com.master.recipestools.data.model.Ingredient
import com.master.recipestools.data.model.Recipe
import com.master.recipestools.service.dto.RecipeDTO

class RecipeMapper {
    fun map(recipesDTO: List<RecipeDTO>): List<Recipe> = recipesDTO.map{ transform(it)}
    private fun transform(recipeDTO: RecipeDTO): Recipe {
        return with(recipeDTO) {
            Recipe(title = title, imagePath = "",ingredients = IngredientMapper().map(ingredients), preparation = PreparationsMapper().map(instructions))
        }
    }
}