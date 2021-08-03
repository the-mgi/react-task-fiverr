package com.themgi.crud.crudapp.domain

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
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (productId != other.productId) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (qtyAvailable != other.qtyAvailable) return false
        if (pricePerUnit != other.pricePerUnit) return false
        if (unitName != other.unitName) return false
        if (category != other.category) return false
        if (imageURL != other.imageURL) return false

        return true
    }

    override fun hashCode(): Int {
        var result = productId?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (qtyAvailable?.hashCode() ?: 0)
        result = 31 * result + (pricePerUnit?.hashCode() ?: 0)
        result = 31 * result + (unitName?.hashCode() ?: 0)
        result = 31 * result + (category?.hashCode() ?: 0)
        result = 31 * result + (imageURL?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Product(productId=$productId, name=$name, description=$description, qtyAvailable=$qtyAvailable, pricePerUnit=$pricePerUnit, unitName=$unitName, category=$category, imageURL=$imageURL)"
    }
}