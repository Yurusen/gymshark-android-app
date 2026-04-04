package android.angel.gymshark.data.repositories

import android.angel.gymshark.data.remote.api.ProductApiService
import android.angel.gymshark.domain.models.Product
import android.angel.gymshark.domain.repositories.ProductRepository
import javax.inject.Inject
import android.angel.gymshark.data.mapper.toDomain

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApiService
): ProductRepository {

    override suspend fun getProducts(): List<Product> =
        api.getProducts().map { it.toDomain() }

}