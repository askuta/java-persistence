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
class Attempt : BaseEntity() {

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    var quiz: Quiz? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    var result: AttemptResult? = null
}
