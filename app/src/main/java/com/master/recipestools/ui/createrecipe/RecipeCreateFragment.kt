package com.master.recipestools.ui.createrecipe

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.master.recipestools.R
import com.master.recipestools.adapter.IngredientAdapter
import com.master.recipestools.adapter.PreparationAdapter
import com.master.recipestools.data.model.Food
import com.master.recipestools.data.model.Ingredient
import com.master.recipestools.data.model.Instruction
import com.master.recipestools.data.model.Unity
import com.master.recipestools.databinding.FragmentRecipeCreateBinding
import com.master.recipestools.extension.categoriesConst
import com.master.recipestools.service.dto.*
import com.master.recipestools.service.mapper.IngredientMapper
import com.master.recipestools.service.mapper.PreparationsMapper
import com.master.recipestools.service.provider.NetworkListener
import com.master.recipestools.service.provider.NetworkProvider
import com.master.recipestools.session.SessionManager

class RecipeCreateFragment : Fragment() {

    private var _binding: FragmentRecipeCreateBinding? = null
    private lateinit var instructionsLayoutManager: LinearLayoutManager
    private lateinit var instructionsRecyclerView: RecyclerView
    private val preparationAdapter by lazy { PreparationAdapter() }
    private lateinit var ingredientLayoutManager: GridLayoutManager
    private lateinit var ingredientRecyclerView: RecyclerView
    private val ingredientAdapter by lazy { IngredientAdapter() }
    private var listIngredient: MutableList<Ingredient> = mutableListOf<Ingredient>()
    private var listInstruction = mutableListOf<Instruction>()
    private lateinit var unitySelected: String
    private var arrayFoods = emptyArray<Food>()
    private lateinit var textSelectedFood: Food
    private lateinit var ingredientText: String
    private lateinit var sessionManager: SessionManager
    private val binding get() = _binding!!


    override fun onResume() {
        super.onResume()
        getFoods()
    }

    private fun getFoods() {
        NetworkProvider.getFoods(sessionManager.fetchAuthToken()?:"", object: NetworkListener<List<Food>>{
            override fun onSuccess(data: List<Food>) {
                arrayFoods = data as Array<Food>

            }

            override fun onError(throwable: Throwable) {
                Toast.makeText(requireContext(),"Erreur:${throwable.localizedMessage}",Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun showSimpleProgressDialog() {
        val visibility = if (binding.progressBar4.visibility == View.GONE) View.VISIBLE else View.GONE
        binding.progressBar4.visibility = visibility
    }
    private fun createPreparation(){
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialog_AppCompat)
        // Get the layout inflater
        val view: View = LayoutInflater.from(requireContext()).inflate(R.layout.edit_preparation_dialog, null)
        var instructionDescription: EditText = view.findViewById(R.id.editTextTextMultiLine)

        builder.setView(view)
            .setTitle("Ajouter une étape")
            // Add action button
            .setPositiveButton("Ajouter")
            { _, _ ->

               InstructionDTO(stepNumber = listInstruction.size+1,instructionDescription.text.toString())

            }
            .setNegativeButton("Annuler")
            { _, _ ->

            }
        val alertDialog=builder.create()
        alertDialog.show()
    }
    private fun createRecipe(){

        val recipeDTO = RecipeDTO(title = binding.titleRecipeCreate.text.toString(),IngredientMapper().mapReverse(listIngredient),PreparationsMapper().mapReverse(listInstruction))
        NetworkProvider.createRecipe(sessionManager.fetchAuthToken()?:"",recipeDTO,object: NetworkListener<String> {


            override fun onError(throwable: Throwable) {
                removeSimpleProgressDialog()
                Toast.makeText(requireContext(),"Erreur:${throwable.localizedMessage}",Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(data: String) {
                removeSimpleProgressDialog()
                Toast.makeText(requireContext(), "Recette ajouté",Toast.LENGTH_SHORT).show()
            }
        })

    }
    private fun createIngredient(){
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialog_AppCompat)
        // Get the layout inflater
        val view: View = LayoutInflater.from(requireContext()).inflate(R.layout.edit_product_dialog, null)
        val product: EditText = view.findViewById(R.id.name)
        // spinner
        val categoriesSpinner: Spinner = view.findViewById(R.id.category)

        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner, categoriesConst)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categoriesSpinner.adapter=arrayAdapter
        var textSelected: String? = null
        categoriesSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                if(position > 0){
                    textSelected = categoriesConst[position]


                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        builder.setView(view)
            .setTitle("Ajouter dans la base un aliment")
            // Add action button
            .setPositiveButton("Ajouter")
            { _, _ ->
                textSelected?.let {
                    val food =FoodDTO(product.text.toString(), CategoryDTO(it))
                    NetworkProvider.createFood(token = sessionManager.fetchAuthToken()?:"",food,object : NetworkListener<String>{
                        override fun onSuccess(data: String) {
                            Toast.makeText(requireContext(),"Produit ajouté", Toast.LENGTH_SHORT).show()
                        }

                        override fun onError(throwable: Throwable) {
                            Toast.makeText(requireContext(),"Erreur:${throwable.localizedMessage}",Toast.LENGTH_SHORT).show()
                        }

                    })


                }

            }
            .setNegativeButton("Annuler")
            { _, _ ->

            }
        val alertDialog=builder.create()
        alertDialog.show()

    }
    private fun removeSimpleProgressDialog() {
        if (binding.progressBar4.visibility==View.VISIBLE) {
            binding.progressBar4.visibility=View.GONE
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRecipeCreateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerViews()
        initSpinner()
        binding.btnAddIngredient.setOnClickListener {
            addIngredient()
        }
        binding.btnCreatePreparation.setOnClickListener {
            createPreparation()
        }


        binding.floatingActionButton.setOnClickListener {
            createRecipe()

        }
        sessionManager = SessionManager(requireContext())
        binding.btnCreateIngredient.setOnClickListener {
            createIngredient()
        }
        return root
    }

    private fun addIngredient() {

        val ingredient=IngredientDTO(food = FoodDTO(textSelectedFood.name,categoryDTO = CategoryDTO(textSelectedFood.category.name)),quantity = binding.editTextNumber.text.toString().toDouble(),
            Unity.valueOf(unitySelected))
       ingredientAdapter.ingredients.add(IngredientMapper().transform(ingredient))
    }

    private fun initSpinner() {
        val unitySpinner: Spinner = binding.listUnite
        val unities = enumValues<Unity>()
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner, unities)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitySpinner.adapter=arrayAdapter

        unitySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                if(position > 0){
                    unitySelected = unities[position].toString()


                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        //
        val ingredientSpinner: Spinner = binding.listIngredient

        val arrayAdapterFoods = ArrayAdapter(requireContext(), R.layout.custom_spinner, arrayFoods.map{it.name})
        arrayAdapterFoods.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitySpinner.adapter=arrayAdapter

        unitySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                if(position > 0){
                    textSelectedFood = arrayFoods[position]


                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun initRecyclerViews() {
        instructionsRecyclerView = binding.instructionsRecyclerview
        ingredientRecyclerView =binding.ingredientsRecyclerview

        ingredientLayoutManager = GridLayoutManager(
            context,
            2
        )
        instructionsLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        ingredientRecyclerView.apply {
            adapter = ingredientAdapter

        }
        instructionsRecyclerView.apply {
            adapter = ingredientAdapter

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}