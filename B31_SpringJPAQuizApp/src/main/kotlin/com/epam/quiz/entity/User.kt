package com.epam.quiz.entity

import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column

@Entity
@Table(name = "users")
class User : BaseEntity() {

    @Column(name = "username", nullable = false)
    var username: String = ""

    @Column(name = "password", nullable = false)
    var password: String = ""

    @Column(name = "first_name")
    var firstName: String = ""

    @Column(name = "last_name")
    var lastName: String = ""

    @Column(name = "email", nullable = false)
    var email: String = ""
}
