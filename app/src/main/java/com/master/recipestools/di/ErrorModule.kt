package com.master.recipestools.di

import com.master.recipestools.data.error.mapper.BaseErrorMapper
import com.master.recipestools.data.error.mapper.ErrorMapper
import com.master.recipestools.errors.BaseError
import com.master.recipestools.errors.ErrorManager

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

// with @Module we Telling Dagger that, this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): BaseError

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): BaseErrorMapper
}