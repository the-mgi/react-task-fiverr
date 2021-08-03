package com.themgi.crud.crudapp.controller

import com.themgi.crud.crudapp.domain.Category
import com.themgi.crud.crudapp.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@CrossOrigin
@RestController
@RequestMapping("/category")
class CategoryController(
    @Autowired
    private val categoryService: CategoryService
) {
    @PostMapping
    fun saveProduct(@RequestBody category: Category): ResponseEntity<Category> {
        val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/category").toUriString())
        return ResponseEntity.created(uri).body(this.categoryService.saveCategory(category))
    }

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<Category>> {
        return ResponseEntity.ok().body(this.categoryService.getAllCategories())
    }

    @DeleteMapping("/{id}")
    fun deleteProductById(@PathVariable id: Long): ResponseEntity<Any> {
        this.categoryService.deleteCategoryById(id)
        return ResponseEntity("Category  Deleted Successfully", HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateProductById(@PathVariable id: Long, @RequestBody category: Category): ResponseEntity<Any> {
        val updatedProduct = this.categoryService.updateCategoryById(id, category)
        if (updatedProduct != null) {
            return ResponseEntity.ok().body(updatedProduct);
        }
        return ResponseEntity("ProductID not found", HttpStatus.NOT_FOUND)
    }
}