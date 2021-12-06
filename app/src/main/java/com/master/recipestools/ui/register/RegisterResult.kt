package com.master.recipestools.ui.register

/**
 * Authentication result : success (user details) or error message.
 */
data class RegisterResult(
    val success: SignedInUserView? = null,
    val error: Int? = null
)