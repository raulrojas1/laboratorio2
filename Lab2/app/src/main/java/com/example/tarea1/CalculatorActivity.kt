package com.example.tarea1

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tarea1.ui.theme.Tarea1Theme

class CalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tarea1Theme {
                // Fondo suave con degradado
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        ),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Tarjeta central con elevación
                    Card(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        CalculatorScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val activity = LocalActivity.current

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Calculadora Básica",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        OutlinedTextField(
            value = num1,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    num1 = it
                }
            },
            label = { Text("Número 1") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = num2,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    num1 = it
                }
            },
            label = { Text("Número 2") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    val a = num1.toFloatOrNull() ?: 0f
                    val b = num2.toFloatOrNull() ?: 0f
                    result = (a + b).toString()
                },
                modifier = Modifier
                    .size(56.dp),
                shape = RoundedCornerShape(50)
            ) { Text("+") }

            Button(
                onClick = {
                    val a = num1.toFloatOrNull() ?: 0f
                    val b = num2.toFloatOrNull() ?: 0f
                    result = (a - b).toString()
                },
                modifier = Modifier
                    .size(56.dp),
                shape = RoundedCornerShape(50)
            ) { Text("−") }

            Button(
                onClick = {
                    val a = num1.toFloatOrNull() ?: 0f
                    val b = num2.toFloatOrNull() ?: 0f
                    result = (a * b).toString()
                },
                modifier = Modifier
                    .size(56.dp),
                shape = RoundedCornerShape(50)
            ) { Text("×") }

            Button(
                onClick = {
                    val a = num1.toFloatOrNull() ?: 0f
                    val b = num2.toFloatOrNull() ?: 0f
                    result = if (b != 0f) (a / b).toString() else "Error"
                },
                modifier = Modifier
                    .size(56.dp),
                shape = RoundedCornerShape(50)
            ) { Text("÷") }
        }

        Text(
            text = "Resultado: $result",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Button(
            onClick = { activity?.finish() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Regresar")
        }
    }
}
