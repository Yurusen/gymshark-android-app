package android.angel.gymshark.data.mapper

import android.angel.gymshark.data.remote.dto.ProductDto
import android.angel.gymshark.domain.models.Product

fun ProductDto.toDomain(): Product = Product(
    id = id,
    name = name,
    price = price
)