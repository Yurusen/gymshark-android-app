package android.angel.gymshark.presentation.screens.products

import android.angel.gymshark.domain.models.Product

data class ProductState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)
