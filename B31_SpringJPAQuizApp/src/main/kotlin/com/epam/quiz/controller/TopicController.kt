package com.epam.quiz.controller

import com.epam.quiz.entity.Topic
import com.epam.quiz.service.TopicService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/topics")
class TopicController(val topicService: TopicService) {

    @ModelAttribute("module")
    fun module(): String = "quizzes"

    @GetMapping
    fun getAllTopics(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        model: Model
    ): String {
        model["topics"] = topicService.findAllTopics(PageRequest.of(page, size))
        return "topics"
    }

    @GetMapping("/{topicId}")
    fun getTopic(@PathVariable("topicId") topicId: Long, model: Model): String {
        model["topic"] = topicService.findTopicById(topicId)
        return "quizzes"
    }

	@GetMapping("/create")
	fun getCreateTopicForm(model: Model): String {
		model["topic"] = Topic()
		return "createOrUpdateTopicForm"
	}

    @PostMapping("/create")
    fun createTopic(topic: Topic, result: BindingResult): String {
        return if (result.hasErrors()) {
            "createOrUpdateTopicForm"
        } else {
            topicService.saveTopic(topic)
            "redirect:/topics"
        }
     }

    @GetMapping("/{topicId}/edit")
    fun getUpdateTopicForm(@PathVariable("topicId") topicId: Long, model: Model): String {
        model["topic"] = topicService.findTopicById(topicId)
        return "createOrUpdateTopicForm"
    }

    @PostMapping("/{topicId}/edit")
    fun updateTopic(@PathVariable("topicId") topicId: Long, topic: Topic, result: BindingResult): String {
        return if (result.hasErrors()) {
            "createOrUpdateTopicForm"
        } else {
            topic.id = topicId
            topicService.saveTopic(topic)
            "redirect:/topics"
        }
    }

    @GetMapping("/{topicId}/delete")
    fun deleteTopic(@PathVariable("topicId") topicId: Long): String {
        topicService.deleteTopicById(topicId)
        return "redirect:/topics"
    }
}
