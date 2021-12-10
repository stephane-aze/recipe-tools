package com.master.recipestools.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.master.recipestools.data.model.Category
import java.util.*


fun ViewGroup.inflate(@LayoutRes layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}
val categoriesConst = listOf(
    "fruits",
    "meal",
    "crustaces",
    "alcohol",
    "lactose",
    "gluten",
    "drink",
    "fish",
)
