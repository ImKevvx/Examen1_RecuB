package com.example.examen1_recub.nav

sealed class Screen(val route: String) {
    object PantallaCompra : Screen("pantalla_compra")
    object PantallaCheckout : Screen("pantalla_checkout")
}