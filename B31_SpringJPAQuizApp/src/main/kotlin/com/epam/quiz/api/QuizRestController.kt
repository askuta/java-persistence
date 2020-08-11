package com.epam.quiz.api

import com.epam.quiz.entity.Quiz
import com.epam.quiz.service.QuizService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/quizzes")
class QuizRestController(val quizService: QuizService) {

    @GetMapping
    fun getAllQuizzes(): List<Quiz> = quizService.findAllQuizzes()

    @GetMapping("/topic/{topicId}")
    fun getQuizzesByTopicId(@PathVariable("topicId") topicId: Long): List<Quiz> =
            quizService.findQuizzesByTopicId(topicId)

    @GetMapping("/{quizId}")
    fun getQuiz(@PathVariable("quizId") quizId: Long) = quizService.findQuizById(quizId)

    @PostMapping
    fun createQuiz(@RequestBody quiz: Quiz): Quiz {
        quizService.saveQuiz(quiz)
        return quiz
    }

    @PutMapping
    fun updateQuiz(@RequestBody quiz: Quiz): Quiz {
        quiz.choices.forEach { it.quiz = quiz }
        quizService.saveQuiz(quiz)
        return quiz
    }

    @DeleteMapping("/{quizId}")
    fun deleteQuizById(@PathVariable("quizId") quizId: Long) = quizService.deleteQuizById(quizId)
}
