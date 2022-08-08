package com.assess1.tracker.controller;

import com.assess1.tracker.DTO.TicketCommentDTO;
import com.assess1.tracker.DTO.ViewDTO;
import com.assess1.tracker.entity.Ticket;
import com.assess1.tracker.exception.ResourceNotFoundException;
import com.assess1.tracker.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Validated
@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/tickets")
    public ResponseEntity<?> createTicket(@RequestParam(name = "user_id") int user_id,
                                          @RequestBody Ticket ticket){
        String newTicket = ticketService.createTicket(ticket , user_id);
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }

    @GetMapping("/viewAll")
    public List<ViewDTO> viewAllTicket() /*throws ResourceNotFoundException*/{

        return ticketService.viewAllTicket();
    }

    @GetMapping("/viewAllUserTicket")
    public List<ViewDTO> viewAllUserTicket(@RequestParam(name = "user_id") int user_id) throws ResourceNotFoundException {
        List<ViewDTO> viewUsers = ticketService.viewAllUserTicket(user_id);
        return viewUsers;
    }

    @GetMapping("/viewTicket/{ticket_id}")
    public ViewDTO viewById(@PathVariable("ticket_id") int ticket_id) throws ResourceNotFoundException{

        return ticketService.viewById(ticket_id);
    }

    @PostMapping("/addComment")
    public ResponseEntity<?> commentOnTicket(@RequestParam(name = "ticket_id") int ticket_id,
                                             @RequestParam(name = "user_id") int user_id,
                                             @RequestBody String message)throws ResourceNotFoundException{
        String newComment = ticketService.commentOnTicket(message,ticket_id,user_id);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping("/viewTicketComment/{ticket_id}")
    public TicketCommentDTO getTicketCommentById(@PathVariable("ticket_id") int ticket_id) throws ResourceNotFoundException{

        return ticketService.getTicketCommentById(ticket_id);
    }

    @PutMapping("/changeStatus")
    public String changeStatus(@RequestParam(name = "ticket_id") int ticket_id,
                               @RequestParam(name = "admin_id") String admin_id,
                               @RequestParam(name = "status_id") String status_id) throws ResourceNotFoundException{
        return ticketService.changeStatus(status_id,ticket_id,admin_id);
    }

    @PutMapping("/changePriority")
    public String changePriority(@RequestParam(name = "ticket_id") int ticket_id,
                               @RequestParam(name = "user_id") int user_id,
                               @RequestParam(name = "priority_id") String priority_id) throws ResourceNotFoundException{
        return ticketService.changePriority(priority_id,ticket_id,user_id);
    }

    @DeleteMapping("/deleteTicket")
    public ResponseEntity<?> deleteTicket(@RequestParam(name = "ticket_id") int ticket_id,
                                            @RequestParam(name = "admin_id") String admin_id) throws ResourceNotFoundException{
        String newComment = ticketService.deleteTicket(ticket_id , admin_id);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @PutMapping("/setAssignee")
    public String setAssignee(@RequestParam(name = "admin_id") String admin_id,
                                 @RequestParam(name = "ticket_id") int ticket_id,
                                 @RequestParam(name = "user_id") int user_id) throws ResourceNotFoundException {
        return ticketService.setAssignee(admin_id,ticket_id,user_id);
    }
}