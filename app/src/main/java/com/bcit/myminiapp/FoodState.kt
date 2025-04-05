package com.bcit.myminiapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bcit.myminiapp.data.FoodRepository
import com.bcit.myminiapp.data.Product

class FoodState(private val foodRepository: FoodRepository) {

    var foodInfo by mutableStateOf<Product?>(null)

    suspend fun getFoodInfo(barcode: String){
        try {
            val response = foodRepository.getFoodInfo(barcode) // suspending
            foodInfo = response.product
        } catch (e: Exception) {
            Log.e("FoodState", "Failed to fetch product info: ${e.message}")
            foodInfo = null
        }
    }
}