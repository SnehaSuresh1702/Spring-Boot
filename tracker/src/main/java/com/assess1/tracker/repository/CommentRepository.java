package com.assess1.tracker.repository;

import com.assess1.tracker.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment , Integer> {
}
