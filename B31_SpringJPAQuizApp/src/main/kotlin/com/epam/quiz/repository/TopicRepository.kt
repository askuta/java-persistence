package com.epam.quiz.repository

import com.epam.quiz.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface TopicRepository : JpaRepository<Topic, Long> {

    @Modifying
    @Query("DELETE FROM Topic topic WHERE topic.id = :topicId")
    fun deleteTopicById(topicId: Long): Int
}
