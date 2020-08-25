package com.epam.quiz.api

import com.epam.quiz.entity.Quiz
import com.epam.quiz.service.QuizService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/quizzes")
class QuizRestController(val quizService: QuizService) {

    @GetMapping
    fun getAllQuizzes(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): Page<Quiz> =
            quizService.findAllQuizzes(PageRequest.of(page, size))

    @GetMapping("/topic/{topicId}")
    fun getQuizzesByTopicId(
        @PathVariable("topicId") topicId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): Page<Quiz> =
            quizService.findQuizzesByTopicId(topicId, PageRequest.of(page, size))

    @GetMapping("/{quizId}")
    fun getQuiz(@PathVariable("quizId") quizId: Long) = quizService.findQuizById(quizId)

    @PostMapping
    @ModelAttribute
    fun createQuiz(@RequestBody quiz: Quiz): Quiz = quizService.saveQuiz(quiz)

    @PutMapping
    fun updateQuiz(@RequestBody quiz: Quiz): Quiz = quizService.saveQuiz(quiz)

    @DeleteMapping("/{quizId}")
    fun deleteQuizById(@PathVariable("quizId") quizId: Long) = quizService.deleteQuizById(quizId)
}
