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
    fun saveCategory(@RequestBody category: Category): ResponseEntity<Category> {
        val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/category").toUriString())
        return ResponseEntity.created(uri).body(this.categoryService.saveCategory(category))
    }

    @GetMapping
    fun getAllCategories(): ResponseEntity<List<Category>> {
        return ResponseEntity.ok().body(this.categoryService.getAllCategories())
    }

    @DeleteMapping("/{id}")
    fun deleteCategoryById(@PathVariable id: Long): ResponseEntity<Any> {
        this.categoryService.deleteCategoryById(id)
        return ResponseEntity("Category  Deleted Successfully", HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateCategoryById(@PathVariable id: Long, @RequestBody category: Category): ResponseEntity<Any> {
        val updatedProduct = this.categoryService.updateCategoryById(id, category)
        if (updatedProduct != null) {
            return ResponseEntity.ok().body(updatedProduct);
        }
        return ResponseEntity("ProductID not found", HttpStatus.NOT_FOUND)
    }

    @PostMapping("/bulk/save")
    fun saveBulkCategories(@RequestBody categories: Array<Category>): ResponseEntity<Any> {
        val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/category/bulk/save").toUriString())
        val allCats = this.categoryService.getAllCategories();
        for (category : Category in categories) {
            if (allCats.contains(category)) {
                this.categoryService.updateCategoryById(category.categoryId!!, category)
            } else {
                this.categoryService.saveCategory(category)
            }
        }
        return ResponseEntity.created(uri).body("Categories Added Successfully");
    }
}