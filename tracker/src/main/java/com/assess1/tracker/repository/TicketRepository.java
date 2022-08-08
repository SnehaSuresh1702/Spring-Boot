package com.assess1.tracker.repository;

import com.assess1.tracker.entity.Ticket;
import com.assess1.tracker.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
