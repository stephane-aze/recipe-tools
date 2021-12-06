package com.master.recipestools.service.mapper

import com.master.recipestools.data.model.Category
import com.master.recipestools.data.model.Food
import com.master.recipestools.data.model.Ingredient
import com.master.recipestools.data.model.Recipe
import com.master.recipestools.service.dto.CategoryDTO
import com.master.recipestools.service.dto.IngredientDTO
import com.master.recipestools.service.dto.RecipeDTO

class IngredientMapper {
    fun map(ingredientDTO: List<IngredientDTO>): List<Ingredient> = ingredientDTO.map{ transform(it)}
    fun transform(ingredientDTO: IngredientDTO): Ingredient {
        return with(ingredientDTO) {
            Ingredient(quantity = quantity,product = Food(name=food.name,category = Category(food.categoryDTO.name),picture = ""),unity = unity)
        }
    }
}