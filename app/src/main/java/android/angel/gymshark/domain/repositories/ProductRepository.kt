package android.angel.gymshark.domain.repositories

import android.angel.gymshark.domain.models.Product

interface ProductRepository {

    suspend fun getProducts(): List<Product>

}