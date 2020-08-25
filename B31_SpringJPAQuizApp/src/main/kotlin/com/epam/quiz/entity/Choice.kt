package com.epam.quiz.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "choices")
class Choice(

    // TODO find better solution for JSON serializatioin and deserialization
    // @JsonIdentityReference(alwaysAsId = true)
    // @JsonIdentityInfo
    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    var quiz: Quiz? = null,

    @Size(min = 1, max = 255, message = "The length of choice answer must be between 1 and 255")
    @Column(name = "answer")
    var answer: String = "",

    @Column(name = "correct")
    var correct: Boolean = false
) : BaseEntity()
