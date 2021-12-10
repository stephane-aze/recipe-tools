package com.master.recipestools.ui.recipeslist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.master.recipestools.adapter.RecipeAdapter
import com.master.recipestools.data.model.Recipe
import com.master.recipestools.databinding.FragmentListRecipeBinding
import com.master.recipestools.ui.recipe.RecipeActivity

class RecipesListFragment : Fragment() {

    private lateinit var recipesListViewModel: RecipesListViewModel
    private var _binding: FragmentListRecipeBinding? = null
    private lateinit var recipesLayoutManager: LinearLayoutManager
    private lateinit var recipesRecyclerView: RecyclerView
    private val productsAdapter by lazy { RecipeAdapter() }


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipesListViewModel =
            ViewModelProvider(this).get(RecipesListViewModel::class.java)

        _binding = FragmentListRecipeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initRecyclerView()

        //val textView: TextView = binding.textNotifications
        recipesListViewModel.recipesList.observe(viewLifecycleOwner, Observer {
            //textView.text = it
            productsAdapter.recipes = it as MutableList<Recipe>
        })
        return root
    }
    private fun initRecyclerView() {
        recipesRecyclerView = binding.recyclerView2

        recipesLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recipesRecyclerView.apply {
            adapter = productsAdapter

        }
        productsAdapter.context=requireActivity()
        productsAdapter.listener = { showRecipe(it) }
    }

    private fun showRecipe(recipe: Recipe) {
        val intent = Intent(context, RecipeActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}