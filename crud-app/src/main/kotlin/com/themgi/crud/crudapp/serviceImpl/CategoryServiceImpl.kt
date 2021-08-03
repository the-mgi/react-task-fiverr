package com.themgi.crud.crudapp.serviceImpl

import com.themgi.crud.crudapp.domain.Category
import com.themgi.crud.crudapp.repository.CategoryRepository
import com.themgi.crud.crudapp.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(
    @Autowired
    private val categoryRepository: CategoryRepository
) : CategoryService {
    override fun saveCategory(category: Category): Category {
        return this.categoryRepository.save(category)
    }

    override fun getAllCategories(): List<Category> {
        return this.categoryRepository.findAll()
    }

    override fun deleteCategoryById(id: Long) {
        this.categoryRepository.deleteById(id)
    }

    override fun updateCategoryById(id: Long, category: Category): Category?{
        val categoryFromDB = this.categoryRepository.findById(id)
        if (categoryFromDB.isPresent) {
            val finalCategory = categoryFromDB.get()
            finalCategory.categoryName = category.categoryName
            this.categoryRepository.save(finalCategory)
            return finalCategory
        }
        return null
    }
}