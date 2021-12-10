package com.master.recipestools.service.mapper

import com.master.recipestools.data.model.Category
import com.master.recipestools.data.model.Food
import com.master.recipestools.service.dto.CategoryDTO
import com.master.recipestools.service.dto.FoodDTO

class FoodMapper {
    fun map(dto:List<FoodDTO>): List<Food> = dto.map{ transform(it)}
    fun transform(foodDTO: FoodDTO): Food {
        return Food(name = foodDTO.name,category = Category(foodDTO.categoryDTO.name),picture = "")
    }
}