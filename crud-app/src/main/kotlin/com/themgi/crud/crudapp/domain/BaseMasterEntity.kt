package com.themgi.crud.crudapp.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
open class BaseMasterEntity(
    @Column(
        name = "created_time_stamp",
        columnDefinition = "timestamp default CURRENT_TIMESTAMP",
    )
    var createdTimestamp: Timestamp? = Timestamp(System.currentTimeMillis()),

    @Column(
        name = "last_updated_time_stamp",
        columnDefinition = "timestamp default CURRENT_TIMESTAMP",
    )
    var lastUpdateTimestamp: Timestamp? = Timestamp(System.currentTimeMillis()),
) {

    @PreUpdate
    protected fun onUpdate() {
        this.lastUpdateTimestamp = Timestamp(System.currentTimeMillis())
    }

    @PrePersist
    protected fun onCreate() {
        this.createdTimestamp = Timestamp(System.currentTimeMillis())
    }

    override fun toString(): String {
        return "BaseMasterEntity(createdTimestamp=$createdTimestamp, updatedTimestamp=$lastUpdateTimestamp)"
    }
}