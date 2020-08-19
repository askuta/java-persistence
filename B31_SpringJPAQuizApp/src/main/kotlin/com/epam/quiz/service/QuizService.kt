package com.epam.quiz.service

import com.epam.quiz.entity.Quiz
import com.epam.quiz.repository.QuizRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.NoSuchElementException
import javax.transaction.Transactional

@Service
@Transactional
class QuizService(val quizRepository: QuizRepository) {

    fun findAllQuizzes(pageable: Pageable): Page<Quiz> = quizRepository.findAll(pageable)

    fun findQuizzesByTopicId(topicId: Long, pageable: Pageable) = quizRepository.findAllByTopic_Id(topicId, pageable)

    fun findQuizById(quizId: Long): Quiz =
            quizRepository.findById(quizId).orElseThrow { NoSuchElementException("Quiz ID $quizId not found.") }

    fun saveQuiz(quiz: Quiz): Quiz {
        quiz.choices.forEach { it.quiz = quiz }
        return quizRepository.save(quiz)
    }

    fun deleteQuizById(quizId: Long) {
        val quiz: Quiz = quizRepository.findById(quizId)
                .orElseThrow { NoSuchElementException("Quiz ID $quizId not found.") }
        quizRepository.delete(quiz)
    }
}
