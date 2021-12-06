package com.master.recipestools.data.model

class UserBuilder private constructor(
    val email:String?,
    val password:String?,
    val username: String?,
    val crustaces: Boolean?,
    val meat: Boolean?,
    val lactose: Boolean?,
    val gluten: Boolean?,
    val alcohol: Boolean?,
    val fish: Boolean?) {

        data class Builder(
            var email:String? = null,
            var password:String? = null,
            var username: String? = null,
            var crustaces: Boolean? = null,
            var meat: Boolean? = null,
            var lactose: Boolean?= null,
            var gluten: Boolean?= null,
            var alcohol: Boolean?= null,
            var fish: Boolean?= null) {
            fun setProfil(email: String,username: String?, password: String) = apply {
                this.email=email
                this.password = password
                this.username = username
            }
            fun likeCrustaces(crustaces: Boolean) = apply { this.crustaces = crustaces }
            fun likeLactose(lactose: Boolean) = apply { this.lactose = lactose }
            fun likeMeat(meat: Boolean) = apply { this.meat = meat }
            fun likeFish(fish: Boolean) = apply { this.fish = fish }
            fun likeGluten(gluten: Boolean) = apply { this.gluten = gluten }
            fun likeAlcohol(alcohol: Boolean) = apply { this.alcohol = alcohol }
            fun build() = UserBuilder(email,password,username,crustaces, meat, lactose, gluten, alcohol, fish)
        }
}