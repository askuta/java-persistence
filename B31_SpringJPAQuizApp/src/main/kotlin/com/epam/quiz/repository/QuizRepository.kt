package com.epam.quiz.repository

import com.epam.quiz.entity.Quiz
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface QuizRepository : JpaRepository<Quiz, Long> {

    fun findAllByTopic_Id(topicId: Long, pageable: Pageable): Page<Quiz>

    @Query("DELETE FROM Quiz quiz WHERE quiz.id = :quizId")
    fun deleteQuizById(quizId: Long): Int
}
