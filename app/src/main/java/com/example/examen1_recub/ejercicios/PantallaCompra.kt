package com.example.examen1_recub.ejercicios

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examen1_recub.R
import com.example.examen1_recub.nav.Screen

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCompra(navController: NavController, viewModel: CompraViewModel){

    var cantidadBananas by remember { mutableStateOf("0") }
    var cantidadTomates by remember { mutableStateOf(0f) }
    var isChecked = false
    val elementosVendidos = arrayOf(
        mutableStateOf(0), // Para bananas
        mutableStateOf(0), // Para tomates
        mutableStateOf(0)  // Para checkbox
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        item{
            Text(
                text = "Bienvenido a la tienda, haz tus compras!\nIntroduce el número de bananas"
            )
            Image(
                painter = painterResource(id = R.drawable.banana),
                contentDescription = "banana",
                modifier = Modifier
                    .height(240.dp)
                    .fillMaxWidth()
            )
            TextField(
                value = cantidadBananas,
                onValueChange = {
                    cantidadBananas = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Text(
                text = "¿Cuantos tomatitos?"
            )
            Image(
                painter = painterResource(id = R.drawable.tomate),
                contentDescription = "tomate",
                modifier = Modifier
                    .height(240.dp)
                    .fillMaxWidth()
            )
            Slider(
                value = cantidadTomates,
                onValueChange = {
                    cantidadTomates = it
                },
                valueRange = 0f..5f,
                steps = 4
            )
            Text(
                text = "¿Un asado con la pulga e Ibai?"
            )
            Image(
                painter = painterResource(id = R.drawable.asado),
                contentDescription = "asado",
                modifier = Modifier
                    .height(240.dp)
                    .fillMaxWidth()
            )

            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = {
                    val cantidadBananasInt = cantidadBananas.toIntOrNull() ?: 0
                    val costoBananas = cantidadBananasInt * 2
                    val costoTomates = (cantidadTomates * 5).toInt()
                    val costoCheckbox = if (isChecked) 100 else 0
                    val costoTotal = costoBananas + costoTomates + costoCheckbox

                    viewModel.agregarCosto(costoTotal)

                    elementosVendidos[0].value += cantidadBananas.toIntOrNull() ?: 0
                    elementosVendidos[1].value += cantidadTomates.toInt()
                    elementosVendidos[2].value += if (isChecked) 1 else 0

                    navController.navigate(Screen.PantallaCheckout.route)
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Comprar!")
            }
        }
    }
}