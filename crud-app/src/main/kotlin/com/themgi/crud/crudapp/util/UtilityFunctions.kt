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

fun areObjectsEqual(o1: Any?, o2: Any?): Boolean {
    o1 as Any
    o2 as Any

    if (o1 == o2) return true
    if (o1.javaClass == o2.javaClass) {
        val className = o1.javaClass
        for (property in className.javaClass.declaredFields) {
            if (property.get(o1) != property.get(o2)) {
                return false
            }
        }
        return true
    }
    return false
}