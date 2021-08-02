package com.themgi.crud.crudapp.service

import com.themgi.crud.crudapp.domain.Product
import java.util.*

interface ProductService {
    fun saveProduct(product: Product): Product
    fun getAllProducts(): List<Product>
    fun getProductById(id: Long): Optional<Product>
    fun deleteProductById(id: Long)
    fun updateProductById(id: Long, product: Product): Product?
}