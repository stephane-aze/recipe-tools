package com.master.recipestools.errors

import com.master.recipestools.data.error.mapper.ErrorMapper
import com.master.recipestools.data.error.Error

import javax.inject.Inject



class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : BaseError {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}