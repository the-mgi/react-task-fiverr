package com.themgi.crud.crudapp.util

fun <T> copyObjectAttributes(objectToCopyFrom: T, objectToCopyTo: T): T {
    if (objectToCopyFrom == null || objectToCopyTo == null) {
        throw IllegalArgumentException("Objects cannot be null")
    }

    if (objectToCopyFrom.javaClass != objectToCopyTo.javaClass) {
        throw IllegalArgumentException("Both objects should be of same type")
    }

    if (objectToCopyFrom == objectToCopyTo) {
        return objectToCopyTo
    }

    val className = objectToCopyFrom.javaClass
    for (property in className.javaClass.declaredFields) {
        if (property.get(objectToCopyFrom) != null) {
            property.set(objectToCopyTo, property.get(objectToCopyFrom))
        }
    }
    return objectToCopyTo
}