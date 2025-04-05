package com.bcit.myminiapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bcit.myminiapp.data.FoodRepository
import com.bcit.myminiapp.data.client
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.launch
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ButtonDefaults


@Composable
fun AllergyInfo() {

    val foodRepository = remember { FoodRepository(client) }
    val foodState = remember { FoodState(foodRepository) }
    var barcode by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var noDataMessage by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            "Enter Barcode",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = barcode,
            onValueChange = { barcode = it },
            label = { Text("e.g. 3017624010701 for Nutella") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            errorMessage = ""
            noDataMessage = ""

            coroutineScope.launch {
                try {
                    println("Fetching food info for barcode: $barcode")
                    foodState.getFoodInfo(barcode)
                    println("Fetched food info: ${foodState.foodInfo}")
                } catch (e: Exception) {
                    errorMessage = "Error: ${e.message}"
                    println("Error fetching food info: ${e.message}")
                }
            }
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF5F4DC),
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Fetch Product Info")
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (errorMessage.isNotEmpty()) {
            Text("Error: $errorMessage")
        }


        foodState.foodInfo?.let { food ->
            val productName = food.productName.ifEmpty { "N/A" } // ← null or "N/A" if it's empty
            val allergens = food.allergens?.ifEmpty { "N/A" } ?: "N/A"
            val ingredients = food.ingredientsText?.ifEmpty { "N/A" } ?: "N/A"

            Text(
                text = "Product Details",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            InfoRow(label = "Product Name", value = productName)
            InfoRow(label = "Allergens", value = allergens)
            InfoRow(label = "Ingredients", value = ingredients)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
