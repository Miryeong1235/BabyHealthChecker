package com.bcit.myminiapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import com.bcit.myminiapp.data.ExcretionRepository
import com.bcit.myminiapp.data.LocalExcretion
import com.bcit.myminiapp.data.MyDatabase

@Composable
fun Home(){

    val context = LocalContext.current

    val db by lazy {
        MyDatabase.getDatabase(context)
    }

    val excretionRepo by lazy {
        ExcretionRepository(db.excretionDao())
    }

    // business state
    val exceptionState = remember { ExcretionState(excretionRepo) }

    // ui state
    val logState = remember { LogState()}


    LazyColumn( // If users input lots of data, they can scroll to see the record
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item { MyText2("Date (YYYY-MM-DD)", logState.date, logState.onDateChanged) }
        item { MyText2("Time (HH:MM)", logState.time, logState.onTimeChanged) }
        item { ShapeSelection(logState) }
        item { ColorSelection(logState) }
        item { QuantitySelection(logState) }
        item { MyText2("Option", logState.option, logState.onOptionChanged) }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomButton("Add") {
                    exceptionState.add(
                        LocalExcretion(
                            date = logState.date,
                            time = logState.time,
                            shape = logState.shape,
                            color = logState.color,
                            quantity = logState.quantity,
                            option = logState.option
                        )
                    )
                }

                CustomButton("Refresh") {
                    exceptionState.refresh()
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text("Date", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Text("Detail", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Text("Memo", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            }

            HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
        }

        items(exceptionState.excretions) { excretion ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(0.7f)) {
                    Text(excretion.date, fontWeight = FontWeight.Bold)
                    Text(excretion.time)
                }

                val colorStr = excretion.color.trim()
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(parseToColor(colorStr))
                )

                Spacer(modifier = Modifier.width(30.dp))

                Text(excretion.option ?: "", modifier = Modifier.weight(1f))
            }

            HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
        }
    }

}


fun parseToColor(colorName: String?): Color {
    return when (colorName) {
        "Beige" -> Color(0xFFc4c29f)
        "Olive" -> Color(0xFFc1b976)
        "Dark yellow" -> Color(0xFFc9bd63)
        "Vivid ocher" -> Color(0xFFecbc3e)
        "Orange ocher" -> Color(0xFFcf9933)
        "Brown" -> Color(0xFFbc8134)
        "Khaki" -> Color(0xFF675c3c)
        else -> Color.Gray // Default colour
    }
}


@Composable
fun MyText2(label: String,
            value:String,
            onValueChanged: (String)-> Unit) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(label, fontSize = 16.sp) },
        textStyle = TextStyle(fontSize = 18.sp),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
    )
}

@Composable
fun CustomButton(label: String, onClick: () -> Unit) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFffdc4a),
            contentColor = Color.Black
        )
    ) {
        Text(label, fontSize = 18.sp)
    }
}

@Composable
fun ShapeSelection(logState: LogState) {
    val shapeOptions = listOf("Normal", "Watery", "Soft", "Hard")

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Shape", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            shapeOptions.forEach { option ->
                Button(
                    onClick = { logState.onShapeChanged(option) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (logState.shape == option) Color(0xFFF5F4DC) else Color.LightGray,
                        contentColor = Color.DarkGray
                    )
                ) {
                    Text(option)
                }
            }
        }
    }
}


@Composable
fun ColorSelection(logState: LogState) {
    val colorOptions = listOf(
        "Beige" to Color(0xFFc4c29f),
        "Olive" to Color(0xFFc1b976),
        "Dark yellow" to Color(0xFFc9bd63),
        "Vivid ocher" to Color(0xFFecbc3e),
        "Orange ocher" to Color(0xFFcf9933),
        "Brown" to Color(0xFFbc8134),
        "Khaki" to Color(0xFF675c3c)
    )

    val warningColors = listOf("Beige", "Olive", "Dark yellow")

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Color", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            colorOptions.forEach { (option, color) ->
                Button(
                    onClick = { logState.onColorChanged(option) },
                    colors = ButtonDefaults.buttonColors(containerColor = color),
                    modifier = Modifier
                        .padding(4.dp)
                        .size(40.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    if (logState.color == option) {
                        Text(
                            "✓",
                            color = if (color == Color(0xFF675c3c)) Color.White else Color.Black
                        )
                    }
                }
            }
        }

        // SHow Warning message if the colour of poop is white-ish (1-3 for the scale)
        if (logState.color in warningColors) {
            Text(
                "This stool color might indicate 'Biliary Atresia'. Please visit the ER immediately.",
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun QuantitySelection(logState: LogState) {
    val quantityOptions = listOf("Normal", "A lot", "A little")

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Quantity", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            quantityOptions.forEach { option ->
                Button(
                    onClick = { logState.onQuantityChanged(option) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (logState.quantity == option) Color(0xFFF5F4DC) else Color.LightGray,
                        contentColor = Color.DarkGray
                    )
                ) {
                    Text(option)
                }
            }
        }
    }
}