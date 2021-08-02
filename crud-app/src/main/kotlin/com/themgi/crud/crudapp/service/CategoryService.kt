package com.themgi.crud.crudapp.service

import com.themgi.crud.crudapp.domain.Category

interface CategoryService {
    fun saveCategory(category: Category): Category
    fun getAllCategories(): List<Category>
    fun deleteCategoryById(id: Long)
    fun updateCategoryById(id: Long, category: Category): Category?
}