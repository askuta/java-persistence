package com.epam.quiz.entity

import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class User(

    @Size(min = 3, max = 50, message = "The length of username must be between 3 and 50")
    @Column(name = "username", unique = true)
    var username: String = "",

    @Size(min = 3, max = 255, message = "The length of password must be between 3 and 255")
    @Column(name = "password")
    var password: String = "",

    @Column(name = "first_name")
    var firstName: String = "",

    @Column(name = "last_name")
    var lastName: String = "",

    @Size(min = 5, max = 100, message = "The length of email must be between 5 and 100")
    @Column(name = "email")
    var email: String = ""
) : BaseEntity()
