package android.angel.gymshark.domain.usecases

import android.angel.gymshark.domain.models.Product
import android.angel.gymshark.domain.repositories.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(): List<Product> = repository.getProducts()

}