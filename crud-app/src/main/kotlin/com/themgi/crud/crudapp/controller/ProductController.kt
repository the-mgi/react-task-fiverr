package com.themgi.crud.crudapp.controller

import com.themgi.crud.crudapp.domain.Product
import com.themgi.crud.crudapp.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/product")
class ProductController(
    @Autowired
    private val productService: ProductService
) {
    @PostMapping
    fun saveProduct(@RequestBody product: Product): ResponseEntity<Product> {
        val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/product").toUriString())
        return ResponseEntity.created(uri).body(this.productService.saveProduct(product))
    }

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok().body(this.productService.getAllProducts())
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<Product?> {
        val product = this.productService.getProductById(id)
        if (product.isPresent) {
            return ResponseEntity.ok().body(product.get())
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/{id}")
    fun deleteProductById(@PathVariable id: Long): ResponseEntity<Any> {
        this.productService.deleteProductById(id)
        return ResponseEntity("Product Deleted Successfully", HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateProductById(@PathVariable id: Long, @RequestBody product: Product): ResponseEntity<Any> {
        val updatedProduct = this.productService.updateProductById(id, product)
        if (updatedProduct != null) {
            return ResponseEntity.ok().body(updatedProduct);
        }
        return ResponseEntity("ProductID not found", HttpStatus.NOT_FOUND)
    }
}