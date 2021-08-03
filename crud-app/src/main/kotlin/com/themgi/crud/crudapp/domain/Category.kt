package com.themgi.crud.crudapp.domain

import javax.persistence.*

@Entity
@Table(name = "category")
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    var categoryId: Long? = null,

    var categoryName: String? = null
) : BaseMasterEntity() {


    override fun toString(): String {
        return "Category(id=$categoryId, categoryName=$categoryName)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (categoryId != other.categoryId) return false
        if (categoryName != other.categoryName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = categoryId?.hashCode() ?: 0
        result = 31 * result + (categoryName?.hashCode() ?: 0)
        return result
    }
}