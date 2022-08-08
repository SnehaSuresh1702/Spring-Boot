package com.assess1.tracker.repository;

import com.assess1.tracker.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority , String> {
}
