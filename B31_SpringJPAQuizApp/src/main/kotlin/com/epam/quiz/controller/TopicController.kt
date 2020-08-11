package com.epam.quiz.controller

import com.epam.quiz.entity.Topic
import com.epam.quiz.service.TopicService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/topics")
class TopicController(val topicService: TopicService) {

    @GetMapping
    fun getAllTopics(model: Model): String {
        model["topics"] = topicService.findAllTopics()
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
