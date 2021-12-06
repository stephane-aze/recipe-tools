package com.master.recipestools.data.model

data class Recipe(val title: String, val imagePath: String, val ingredients: List<Ingredient>, val preparation: List<Instruction>)