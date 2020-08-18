package com.epam.quiz.service

import com.epam.quiz.entity.Quiz
import com.epam.quiz.repository.QuizRepository
import org.springframework.stereotype.Service
import java.util.NoSuchElementException
import javax.transaction.Transactional

@Service
@Transactional
class QuizService(val quizRepository: QuizRepository) {

    fun findAllQuizzes(): List<Quiz> = quizRepository.findAll()

    fun findQuizzesByTopicId(topicId: Long) = quizRepository.findAllByTopicId(topicId)

    fun findQuizById(quizId: Long): Quiz =
            quizRepository.findById(quizId).orElseThrow { NoSuchElementException("Quiz ID $quizId not found.") }

    fun saveQuiz(quiz: Quiz) {
        quiz.choices.forEach { it.quiz = quiz }
        quizRepository.save(quiz)
    }

    fun deleteQuizById(quizId: Long) {
        val quiz: Quiz = quizRepository.findById(quizId)
                .orElseThrow { NoSuchElementException("Quiz ID $quizId not found.") }
        quizRepository.delete(quiz)
    }
}
