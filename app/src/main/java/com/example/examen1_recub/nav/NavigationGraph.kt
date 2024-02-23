package com.example.examen1_recub.nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.examen1_recub.ejercicios.PantallaCheckout
import com.example.examen1_recub.ejercicios.PantallaCompra
import androidx.navigation.compose.rememberNavController
import com.example.examen1_recub.ejercicios.CompraViewModel

@Composable
fun NavigationGraph(applicationContext: Context, compraViewModel: CompraViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.PantallaCompra.route
    ) {
        composable(Screen.PantallaCompra.route) {
            PantallaCompra(navController, compraViewModel)
        }
        composable(Screen.PantallaCheckout.route) {
            PantallaCheckout(compraViewModel)
        }
    }
}
