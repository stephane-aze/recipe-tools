package com.master.recipestools.data.error.mapper

interface BaseErrorMapper {
    fun getErrorString(errorId: Int): String
    val errorsMap: Map<Int, String>
}