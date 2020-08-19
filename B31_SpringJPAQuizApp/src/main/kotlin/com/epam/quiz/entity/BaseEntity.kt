package com.epam.quiz.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotNull

@MappedSuperclass
open class BaseEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long = 0,

    @NotNull
    @CreationTimestamp
    @Column(name = "creation_timestamp", updatable = false)
    var creationTimestamp: LocalDateTime? = null
)
