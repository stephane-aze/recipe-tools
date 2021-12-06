package com.master.recipestools.errors
import com.master.recipestools.data.error.Error
interface BaseError {
    fun getError(code: Int): Error
}