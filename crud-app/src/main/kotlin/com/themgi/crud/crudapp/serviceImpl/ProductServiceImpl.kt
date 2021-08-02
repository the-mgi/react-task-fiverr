package com.themgi.crud.crudapp.serviceImpl

import com.themgi.crud.crudapp.domain.Product
import com.themgi.crud.crudapp.repository.ProductRepository
import com.themgi.crud.crudapp.service.ProductService
import com.themgi.crud.crudapp.util.copyObjectAttributes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(
    @Autowired
    private val productRepository: ProductRepository
) : ProductService {
    override fun saveProduct(product: Product): Product {
        return this.productRepository.save(product)
    }

    override fun getAllProducts(): List<Product> {
        return this.productRepository.findAll()
    }

    override fun getProductById(id: Long): Optional<Product> {
        return this.productRepository.findById(id)
    }

    override fun deleteProductById(id: Long) {
        this.productRepository.deleteById(id)
    }

    override fun updateProductById(id: Long, product: Product): Product? {
        val productFromDB = this.productRepository.findById(id)
        if (productFromDB.isPresent) {
            return copyObjectAttributes(product, productFromDB.get())
        }
        return null
    }
}