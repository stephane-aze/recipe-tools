package com.master.recipestools.ui.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.master.recipestools.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}