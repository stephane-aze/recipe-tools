package com.master.recipestools.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.master.recipestools.R
import com.master.recipestools.data.model.Ingredient
import com.master.recipestools.extension.inflate

class IngredientAdapter: RecyclerView.Adapter<IngredientAdapterViewHolder>() {
    var ingredientListener: ((Ingredient) -> Unit)? = null
    var ingredients = mutableListOf<Ingredient>()
        set(value) {
            field = value
            notifyDataSetChanged()

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientAdapterViewHolder {
        return IngredientAdapterViewHolder(
            parent.inflate(R.layout.item_recipe))
    }

    override fun onBindViewHolder(holder: IngredientAdapterViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.bind(ingredient)
        holder.itemView.setOnClickListener { ingredientListener?.invoke(ingredient) }
    }

    override fun getItemCount()= ingredients.size
}
class IngredientAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(ingredient: Ingredient) {

    }


}