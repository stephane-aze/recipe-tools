package com.master.recipestools.ui.register

/**
 * User details post authentication that is exposed to the UI
 */
data class SignedInUserView(
    val displayName: String
    //... other data fields that may be accessible to the UI
)