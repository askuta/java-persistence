package com.epam.quiz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SpringJpaQuizApplication

fun main(args: Array<String>) {
	runApplication<SpringJpaQuizApplication>(*args)
}
