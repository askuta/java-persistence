package com.epam.quiz.api

import com.epam.quiz.entity.Topic
import com.epam.quiz.service.TopicService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/topics")
class TopicRestController(val topicService: TopicService) {

    @GetMapping
    fun getAllTopics(): List<Topic> = topicService.findAllTopics()

    @GetMapping("/{topicId}")
    fun getTopic(@PathVariable("topicId") topicId: Long): Topic = topicService.findTopicById(topicId)

    @PostMapping
    fun createTopic(@RequestBody topic: Topic): Topic {
        topicService.saveTopic(topic)
        return topic
    }

    @PutMapping
    fun updateTopic(@RequestBody topic: Topic): Topic {
        topicService.saveTopic(topic)
        return topic
    }

    @DeleteMapping("/{topicId}")
    fun deleteTopicById(@PathVariable("topicId") topicId: Long) = topicService.deleteTopicById(topicId)
}
