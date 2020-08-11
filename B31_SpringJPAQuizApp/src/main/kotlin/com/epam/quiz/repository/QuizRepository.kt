package com.epam.quiz.repository

import com.epam.quiz.entity.Quiz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface QuizRepository : JpaRepository<Quiz, Long> {

    @Query("SELECT quiz FROM Quiz quiz WHERE quiz.topic.id = :topicId")
    fun findAllByTopicId(topicId: Long): List<Quiz>
}
