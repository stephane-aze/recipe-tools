package com.master.recipestools.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.master.recipestools.R
import com.master.recipestools.data.model.Recipe
import com.master.recipestools.extension.inflate

class RecipeAdapter: RecyclerView.Adapter<RecipeViewHolder>() {
    var listener: ((Recipe) -> Unit)? = null
    var context: Context?=null
    var recipes = mutableListOf<Recipe>()
        set(value) {
            field = value
            notifyDataSetChanged()

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
                parent.inflate(R.layout.item_recipe))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val product = recipes[position]
        holder.bind(product,context/*,counterFab*/)
        holder.itemView.setOnClickListener { listener?.invoke(product) }
    }

    override fun getItemCount()= recipes.size
}
class RecipeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(product: Recipe,context: Context?) {

    }

}