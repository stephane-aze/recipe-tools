package com.master.recipestools.service.mapper

import com.master.recipestools.data.model.Category
import com.master.recipestools.service.dto.CategoryDTO

class CategoryMapper {
    fun map(dto:List<CategoryDTO>): List<Category> = dto.map{ transform(it)}
    fun transform(categoryDTO: CategoryDTO): Category {
        return Category(name = categoryDTO.name)
    }
}