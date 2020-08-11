package com.epam.quiz.controller

import com.epam.quiz.entity.Choice
import com.epam.quiz.entity.Quiz
import com.epam.quiz.service.QuizService
import com.epam.quiz.service.TopicService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/quizzes")
class QuizController(val topicService: TopicService, val quizService: QuizService) {

    @GetMapping("/topic/{topicId}")
    fun getQuizzesByTopicId(@PathVariable("topicId") topicId: Long, model: Model): String {
        model["topic"] = topicService.findTopicById(topicId)
        model["quizzes"] = quizService.findQuizzesByTopicId(topicId)
        return "quizzes"
    }

    @GetMapping("/topic/{topicId}/create")
    fun getCreateQuizForm(@PathVariable("topicId") topicId: Long, model: Model): String {
        val quiz: Quiz = Quiz()
        quiz.topic = topicService.findTopicById(topicId)
        quiz.addChoice(Choice())
        quiz.addChoice(Choice())
        model["quiz"] = quiz
        return "createOrUpdateQuizForm"
    }

    @GetMapping("/{quizId}/edit")
    fun getUpdateQuizForm(@PathVariable("quizId") quizId: Long, model: Model): String {
        model["quiz"] = quizService.findQuizById(quizId)
        return "createOrUpdateQuizForm"
    }

    @PostMapping("/save")
    fun saveQuiz(quiz: Quiz, result: BindingResult): String {
        return if (result.hasErrors()) {
            "createOrUpdateQuizForm"
        } else {
            val topicId: Long? = quiz.topic?.id
            quizService.saveQuiz(quiz)
            "redirect:/quizzes/topic/$topicId"
        }
    }

    @PostMapping("/save", params = ["addAnswer"])
    fun addAnswer(quiz: Quiz, result: BindingResult): String {
        quiz.addChoice(Choice())
        return "createOrUpdateQuizForm"
    }

    @PostMapping("/save", params = ["removeAnswer"])
    fun removeAnswer(quiz: Quiz, result: BindingResult, httpRequest: HttpServletRequest): String {
        val rowIndex: Int = httpRequest.getParameter("removeAnswer").toInt()
        quiz.removeChoice(rowIndex)
        return "createOrUpdateQuizForm"
    }

    @GetMapping("/{quizId}/delete")
    fun deleteQuiz(@PathVariable("quizId") quizId: Long): String {
        val topicId = quizService.findQuizById(quizId).topic?.id
        quizService.deleteQuizById(quizId)
        return "redirect:/quizzes/topic/$topicId"
    }
}
