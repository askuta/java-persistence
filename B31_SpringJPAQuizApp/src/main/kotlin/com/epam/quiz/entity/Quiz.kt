package com.epam.quiz.entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.Size

@Entity
@Table(name = "quizzes")
class Quiz(

    @ManyToOne
    @JoinColumn(name = "topic_id")
    var topic: Topic,

    @Column(name = "description")
    var description: String = "",

    @Size(min = 1, max = 255, message = "The length of quiz question must be between 1 and 255")
    @Column(name = "question")
    var question: String = "",

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "quiz", orphanRemoval = true)
    var choices: MutableList<Choice> = ArrayList()
) : BaseEntity() {

    fun addChoice(choice: Choice) {
        choices.add(choice)
        choice.quiz = this
    }

    fun removeChoice(index: Int) {
        choices.removeAt(index)
    }
}
