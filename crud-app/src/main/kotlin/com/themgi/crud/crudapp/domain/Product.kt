package com.themgi.crud.crudapp.domain

import com.themgi.crud.crudapp.util.areObjectsEqual
import javax.persistence.*

@Entity
@Table(name = "product")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    var productId: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @Column(nullable = false)
    var description: String? = null,

    @Column(nullable = false)
    var qtyAvailable: Long? = null,

    @Column(nullable = false)
    var pricePerUnit: Double? = null,

    @Column(nullable = false)
    var unitName: String? = null,

    @OneToOne
    @JoinColumn(
        name = "category_id",
        referencedColumnName = "category_id"
    )
    var category: Category? = null,

    @Column(
        name = "image_url",
        nullable = false,
        unique = true
    )
    var imageURL: String? = null
) : BaseMasterEntity() {
    override fun equals(other: Any?): Boolean {
        return areObjectsEqual(this, other)
    }

    override fun hashCode(): Int {
        return productId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Product(productId=$productId, name=$name, description=$description, qtyAvailable=$qtyAvailable, pricePerUnit=$pricePerUnit, unitName=$unitName, category=$category, imageURL=$imageURL)"
    }
}