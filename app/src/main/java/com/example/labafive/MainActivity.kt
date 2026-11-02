package com.example.labafive

import androidx.compose.material3.RadioButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.TextField
import androidx.compose.foundation.layout.Column
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.labafive.ui.theme.LabaFiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabaFiveTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val orderAmount = remember { mutableStateOf("") }
    val dishCount = remember { mutableStateOf("") }
    val tipPercent = remember { mutableStateOf(0f) }

    val dishCountNumber = dishCount.value.toIntOrNull() ?: 0
    val discountPercent = when {
        dishCountNumber in 1..2 -> 3
        dishCountNumber in 3..5 -> 5
        dishCountNumber in 6..10 -> 7
        dishCountNumber > 10 -> 10
        else -> 0
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp, vertical = 32.dp)) {

        Text(text = "Калькулятор чаевых")

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Сумма заказа:")

            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = orderAmount.value,
                onValueChange = { orderAmount.value = it },
                label = { Text("Введите сумму") }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Количество блюд:")

            TextField(
                value = dishCount.value,
                onValueChange = { dishCount.value = it },
                label = { Text("Введите количество") }
            )
        }

        Text(text = "Чаевые: ${tipPercent.value.toInt()}%")
        Column {
            Slider(
                value = tipPercent.value,
                onValueChange = { tipPercent.value = it },
                valueRange = 0f..25f
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "0")
                Text(text = "25")
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Скидка:")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf(3, 5, 7, 10).forEach { discount ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = discountPercent == discount,
                            onClick = null
                        )
                        Text(text = "$discount%")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabaFiveTheme {
        Greeting("Android")
    }
}