package com.assess1.tracker.service;

import com.assess1.tracker.DTO.TicketCommentDTO;
import com.assess1.tracker.DTO.ViewDTO;
import com.assess1.tracker.entity.Ticket;
import com.assess1.tracker.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    String createTicket(Ticket ticket, int userid);

    List<ViewDTO> viewAllTicket() /*throws ResourceNotFoundException*/;

    List<ViewDTO> viewAllUserTicket(int user_id) throws ResourceNotFoundException;

    ViewDTO viewById(int ticket_id) throws ResourceNotFoundException;

    String commentOnTicket(String message, int ticket_id , int user_id) throws ResourceNotFoundException;

    TicketCommentDTO getTicketCommentById(int ticket_id) throws ResourceNotFoundException;

    String changeStatus(String status_id, int ticket_id, String admin_id) throws ResourceNotFoundException;

    String changePriority(String priority_id, int ticket_id, int user_id) throws ResourceNotFoundException;

    String deleteTicket(int ticket_id , String admin_id) throws ResourceNotFoundException;

    String setAssignee(String admin_id, int ticket_id, int user_id) throws ResourceNotFoundException;
}
