package com.epam.quiz.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "attempts")
class Attempt(

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    var user: User,

    @ManyToOne
    @JoinColumn(name = "quiz_id", updatable = false)
    var quiz: Quiz,

    @Enumerated(EnumType.STRING)
    @Column(name = "result", updatable = false)
    var result: AttemptResult = AttemptResult.FAILED
) : BaseEntity()
