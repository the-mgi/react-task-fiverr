package com.themgi.crud.crudapp.repository

import com.themgi.crud.crudapp.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
}