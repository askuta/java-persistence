package com.epam.quiz.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "topics")
class Topic(

    @NotBlank(message = "Topic name is mandatory.")
    @Size(min = 2, max = 255, message = "The length of topic name must be between 2 and 255")
    @Column(name = "name", unique = true)
    var name: String = ""
) : BaseEntity()
