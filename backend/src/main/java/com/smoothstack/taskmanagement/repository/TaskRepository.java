package com.smoothstack.taskmanagement.repository;

import com.smoothstack.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE (:status IS NULL OR t.status = :status) AND (:assigneeId IS NULL OR t.assignee.id = :assigneeId)")
    List<Task> findByStatusAndAssignee(@Param("status") String status, @Param("assigneeId") Long assigneeId);
}