package com.example.examen1_recub.ejercicios

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CompraViewModel : ViewModel() {
    private val _totalGastado = MutableStateFlow(mutableListOf<Int>())
    val totalGastado: StateFlow<List<Int>> = _totalGastado.asStateFlow()

    fun agregarCosto(costo: Int) {
        _totalGastado.value.add(costo)
    }

    fun obtenerSumaTotal(): Int {
        return _totalGastado.value.sum()
    }

}