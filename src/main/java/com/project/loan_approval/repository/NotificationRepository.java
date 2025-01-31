package com.project.loan_approval.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.loan_approval.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdOrderByDueDateAsc(Long userId);
    boolean existsByUserIdAndDueDate(Long userId, LocalDate dueDate);
}
