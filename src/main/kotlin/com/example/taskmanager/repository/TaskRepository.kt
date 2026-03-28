package com.example.taskmanager.repository

import com.example.taskmanager.model.*
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.time.LocalDateTime

@Repository
class TaskRepository(private val jdbcClient: JdbcClient) {

    fun save(task: Task): Task {
        val id = jdbcClient.sql("""
        INSERT INTO tasks (title, description, status, created_at, updated_at)
        VALUES (:title, :description, :status, :createdAt, :updatedAt)
        """)
            .param("title", task.title)
            .param("description", task.description)
            .param("status", task.status.name)
            .param("createdAt", task.createdAt)
            .param("updatedAt", task.updatedAt)
            .update()

        return findLastInserted()
    }

    fun findLastInserted(): Task {
        return jdbcClient.sql("""
        SELECT * FROM tasks ORDER BY id DESC LIMIT 1
    """)
            .query { rs, _ -> mapRow(rs) }
            .single()
    }

    fun findById(id: Long): Task? =
        jdbcClient.sql("SELECT * FROM tasks WHERE id = :id")
            .param("id", id)
            .query { rs, _ -> mapRow(rs) }
            .optional()
            .orElse(null)

    fun deleteById(id: Long) {
        jdbcClient.sql("DELETE FROM tasks WHERE id = :id")
            .param("id", id)
            .update()
    }

    private fun mapRow(rs: ResultSet): Task {
        return Task(
            id = rs.getLong("id"),
            title = rs.getString("title"),
            description = rs.getString("description"),
            status = TaskStatus.valueOf(rs.getString("status")),
            createdAt = rs.getTimestamp("created_at").toLocalDateTime(),
            updatedAt = rs.getTimestamp("updated_at").toLocalDateTime()
        )
    }

    fun updateStatus(id: Long, status: TaskStatus): Task? {
        jdbcClient.sql("""
        UPDATE tasks
        SET status = :status, updated_at = :updatedAt
        WHERE id = :id
    """)
            .param("id", id)
            .param("status", status.name)
            .param("updatedAt", LocalDateTime.now())
            .update()

        return findById(id)
    }
}