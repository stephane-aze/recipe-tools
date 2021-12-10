package com.master.recipestools.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.master.recipestools.R
import com.master.recipestools.data.model.Instruction
import com.master.recipestools.data.model.Recipe
import com.master.recipestools.extension.inflate

class PreparationAdapter: RecyclerView.Adapter<PreparationViewHolder>() {
    var preparationListener: ((Instruction) -> Unit)? = null
    var preparations = mutableListOf<Instruction>()
        set(value) {
            field = value
            notifyDataSetChanged()

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreparationViewHolder {
        return PreparationViewHolder(
                parent.inflate(R.layout.item_recipe))
    }

    override fun onBindViewHolder(holder: PreparationViewHolder, position: Int) {
        val preparation = preparations[position]
        holder.bind(preparation)
        holder.itemView.setOnClickListener { preparationListener?.invoke(preparation) }
    }

    override fun getItemCount()= preparations.size
}
class PreparationViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(preparation: Instruction) {

    }

}