package com.bcit.myminiapp.data

import com.google.gson.annotations.SerializedName


data class FoodResponse (
    @SerializedName("code")
    val barcode: String,
    @SerializedName("product")
    val product: Product,

)

data class Product(
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("allergens_from_ingredients")
    val allergens: String?,
    @SerializedName("ingredients_text")
    val ingredientsText: String?,
    @SerializedName("image_front_small_url")
    val image: String?
)