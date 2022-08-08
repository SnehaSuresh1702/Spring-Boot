package com.assess1.tracker.repository;

import com.assess1.tracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , String> {
}
