package com.bcit.myminiapp.data

import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import android.util.Log

class FoodRepository(private val httpClient: HttpClient) {

    suspend fun getFoodInfo(barcode: String): FoodResponse {
        val url = "$BASE_URL$barcode"
        val response = httpClient.get(url)
        val json = response.body<String>() // Store Json as String
        Log.d("FoodRepository", "API Response: $json")

        return Gson().fromJson(json, FoodResponse::class.java) // Parse whole FoodResponse
    }

}