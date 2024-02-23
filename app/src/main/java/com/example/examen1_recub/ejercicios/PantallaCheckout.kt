package com.example.examen1_recub.ejercicios

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.examen1_recub.R

@Composable
fun PantallaCheckout(viewModel: CompraViewModel){
    val totalGastado: List<Int> by viewModel.totalGastado.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var filtroSeleccionado by remember { mutableStateOf("") }

    Column {
        Text(text = "Estos son todas las compras que has hecho! Puedes filtrar seleccionando los botones")
        Row {
            Button(onClick = { filtroSeleccionado = "pobres" }, modifier = Modifier.padding(1.dp)) {
                Text(text = "Pobres", fontWeight = if (filtroSeleccionado == "pobres") FontWeight.Bold else null)
            }
            Button(onClick = { filtroSeleccionado = "medias" }, modifier = Modifier.padding(1.dp)) {
                Text(text = "Medias", fontWeight = if (filtroSeleccionado == "medias") FontWeight.Bold else null)
            }
            Button(onClick = { filtroSeleccionado = "grandes" }, modifier = Modifier.padding(1.dp)) {
                Text(text = "Grandes", fontWeight = if (filtroSeleccionado == "grandes") FontWeight.Bold else null)
            }
            Button(onClick = { filtroSeleccionado = "todos" }, modifier = Modifier.padding(1.dp)) {
                Text(text = "Todos", fontWeight = if (filtroSeleccionado == "todos") FontWeight.Bold else null)
            }
        }

        Column {
            totalGastado.filter { costo ->
                when {
                    filtroSeleccionado.isEmpty() -> true
                    filtroSeleccionado == "pobres" && costo <= 50 -> true
                    filtroSeleccionado == "medias" && costo > 50 && costo < 100 -> true
                    filtroSeleccionado == "grandes" && costo >= 100 -> true
                    else -> false
                }
            }.forEach { costo ->
                when {
                    costo >= 100 -> {
                        Image(
                            painter = painterResource(id = R.drawable.very_rich),
                            contentDescription = "Elemento",
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                        )
                    }
                    costo > 50 && costo < 100 -> {
                        Image(
                            painter = painterResource(id = R.drawable.rich),
                            contentDescription = "Elemento",
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                        )
                    }
                    else -> {
                        Image(
                            painter = painterResource(id = R.drawable.poor),
                            contentDescription = "Elemento",
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                        )
                    }
                }
                Text(text = "Se hizo una compra de elementos con una suma total de: $costo")
            }
        }

        Button(onClick = { showDialog = true }, modifier = Modifier.padding(4.dp)) {
            Text(text = "Dinero total gastado")
        }
        if (showDialog) {
            Dialog(
                onDismissRequest = {
                    showDialog = false
                }
            ) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp)
                ) {
                    val sumaTotal = viewModel.obtenerSumaTotal()
                    Text("El precio total es $sumaTotal CACHING")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}
