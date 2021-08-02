package com.themgi.crud.crudapp.repository

import com.themgi.crud.crudapp.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
}