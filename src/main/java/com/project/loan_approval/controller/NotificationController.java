package com.project.loan_approval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.loan_approval.entity.Notification;
import com.project.loan_approval.repository.NotificationRepository;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/notifications")
    public List<Notification> getUserNotifications(@RequestParam Long userId) {
        return notificationRepository.findByUserIdOrderByDueDateAsc(userId);
    }
}
