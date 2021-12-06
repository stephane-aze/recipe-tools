package com.master.recipestools.ui.recipeslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.master.recipestools.databinding.FragmentListRecipeBinding

class RecipesListFragment : Fragment() {

    private lateinit var recipesListViewModel: RecipesListViewModel
    private var _binding: FragmentListRecipeBinding? = null

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

        //val textView: TextView = binding.textNotifications
        recipesListViewModel.getRecipes().observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}