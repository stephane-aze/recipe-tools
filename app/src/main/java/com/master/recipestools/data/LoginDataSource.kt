package com.master.recipestools.data

import com.master.recipestools.data.model.LoggedInUser
import com.master.recipestools.service.NetworkAPI
import com.master.recipestools.service.provider.NetworkProvider
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            //NetworkProvider.ge
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}