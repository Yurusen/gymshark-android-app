package android.angel.gymshark.data.remote.api

import android.angel.gymshark.data.remote.dto.ProductDto
import retrofit2.http.GET

interface ProductApiService {

    @GET("/products")
    suspend fun getProducts(): List<ProductDto>

}