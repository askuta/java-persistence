package com.epam.quiz.repository

import com.epam.quiz.entity.Quiz
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface QuizRepository : JpaRepository<Quiz, Long> {

    fun findAllByTopic_Id(topicId: Long, pageable: Pageable): Page<Quiz>
}
