package com.bcit.myminiapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// UI logic state holder

class LogState{
    var date by mutableStateOf("")
    val onDateChanged: (String)-> Unit = {
        date = it
    }

    var time by mutableStateOf("")
    val onTimeChanged: (String)-> Unit = {
        time = it
    }

    var shape by mutableStateOf("Normal")
    val onShapeChanged: (String)-> Unit = {
        shape = it
    }

    var color by mutableStateOf("Vivid ocher")
    val onColorChanged: (String)-> Unit = {
        color = it
    }

    var quantity by mutableStateOf("Normal")
    val onQuantityChanged: (String)-> Unit = {
        quantity = it
    }

    var option by mutableStateOf("")
    val onOptionChanged: (String)-> Unit = {
        option = it
    }
}