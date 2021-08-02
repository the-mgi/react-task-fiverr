package com.themgi.crud.crudapp.domain

import com.themgi.crud.crudapp.util.areObjectsEqual
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
    override fun equals(other: Any?): Boolean {
        return areObjectsEqual(this, other)
    }

    override fun hashCode(): Int {
        return categoryId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Category(id=$categoryId, categoryName=$categoryName)"
    }
}