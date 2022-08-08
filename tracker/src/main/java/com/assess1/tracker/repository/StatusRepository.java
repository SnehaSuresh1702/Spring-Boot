package com.assess1.tracker.repository;

import com.assess1.tracker.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, String> {
}