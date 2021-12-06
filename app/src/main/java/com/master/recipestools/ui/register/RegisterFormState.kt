package com.master.recipestools.ui.login

/**
 * Data validation state of the login form.
 */
data class RegisterFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)