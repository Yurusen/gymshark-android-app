package android.angel.gymshark.presentation.screens.products

import android.angel.gymshark.domain.models.Product
import android.angel.gymshark.domain.usecases.GetProductsUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(ProductState())
    val state: StateFlow<ProductState> = _state

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _state.value = ProductState(isLoading = true)
            try {
                val products = getProductsUseCase()
                _state.value = ProductState(products = products)
            } catch (e: Exception) {
                _state.value = ProductState(error = e.message)
            }
        }
    }



}