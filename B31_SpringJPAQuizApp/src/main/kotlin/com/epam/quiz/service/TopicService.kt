package com.epam.quiz.service

import com.epam.quiz.entity.Topic
import com.epam.quiz.repository.TopicRepository
import org.springframework.stereotype.Service
import java.util.NoSuchElementException
import javax.transaction.Transactional

@Service
class TopicService(val topicRepository: TopicRepository) {

    fun findAllTopics(): List<Topic> = topicRepository.findAll()

    fun findTopicById(topicId: Long): Topic =
            topicRepository.findById(topicId).orElseThrow { NoSuchElementException("Topic ID $topicId not found.") }

    @Transactional
    fun saveTopic(topic: Topic) = topicRepository.save(topic)

    @Transactional
    fun deleteTopicById(topicId: Long) {
        val topic: Topic = topicRepository.findById(topicId)
                .orElseThrow { NoSuchElementException("Topic ID $topicId not found.") }
        topicRepository.delete(topic)
    }
}
