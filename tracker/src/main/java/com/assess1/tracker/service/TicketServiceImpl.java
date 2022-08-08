package com.assess1.tracker.service;

import com.assess1.tracker.DTO.CommentDTO;
import com.assess1.tracker.DTO.TicketCommentDTO;
import com.assess1.tracker.DTO.ViewDTO;
import com.assess1.tracker.entity.*;
import com.assess1.tracker.exception.ResourceNotFoundException;
import com.assess1.tracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private AdminTeamRepository adminTeamRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private PriorityRepository priorityRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public String createTicket(Ticket ticket, int user_id) {

        Boolean isValid = false;

        Ticket newTicket = new Ticket();
        newTicket.setReporter_Id(user_id);

        Optional<User> byId = userRepository.findById(user_id);
        User user = null;
        if (byId.isPresent()) {
            user = byId.get();
        } else {
            return "Failed to create ticket";
        }

        newTicket.setCategory_id(ticket.getCategory_id());
        newTicket.setSub_category_id(ticket.getSub_category_id());

        List<SubCategory> subCategoryList = subCategoryRepository.findAll();

        for (SubCategory subCategory : subCategoryList) {
            if ((subCategory.getSub_category_id().equals(ticket.getSub_category_id())) &&
                    (subCategory.getCategory_id().equals(ticket.getCategory_id()))) {
                isValid = true;
                System.out.println(subCategory.getSub_category_id());
                System.out.println(subCategory.getCategory_id());
            }
        }

        if (isValid == false) {
            return "Failed to create ticket";
        }
        newTicket.setSubject(ticket.getSubject());
        newTicket.setDescription(ticket.getDescription());
        newTicket.setPriority_id(null);
        newTicket.setStatus_id("Stat1");

        ticketRepository.save(newTicket);

        return "Ticket-id " + newTicket.getTicket_id() + " created successfully" + " and URL "
                + "http://localhost:8080/viewTicket/" + Integer.toString(newTicket.getTicket_id());
    }

    @Override
    public List<ViewDTO> viewAllTicket() /*throws ResourceNotFoundException*/ {

        ticketRepository.findAll();

        List<Ticket> ticketList = ticketRepository.findAll();

            /*if(ticketList.isEmpty()){
                throw new ResourceNotFoundException("No data found");
            }*/

        List<ViewDTO> newList = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            ViewDTO view = new ViewDTO();

            view.setTicket_id(ticket.getTicket_id());
            view.setCategory_desc(categoryRepository.findById(ticket.getCategory_id())
                    .get().getCategory_desc());
            view.setSub_category_desc(subCategoryRepository.findById(ticket.getSub_category_id())
                    .get().getSub_category_desc());

            String oldAssignee = "null";
            oldAssignee = ticket.getAssignee_Id();
            if (oldAssignee != null) {
                view.setAssignee(String.valueOf(adminTeamRepository.findById(
                        String.valueOf(ticket.getAssignee_Id()))));
            } else {
                view.setAssignee(oldAssignee);
            }

            view.setSubject(ticket.getSubject());
            view.setDescription(ticket.getDescription());
            view.setStatus(statusRepository.findById(ticket.getStatus_id()).get().getStatus());

            String oldPriority = "null";
            oldPriority = ticket.getPriority_id();
            if (oldPriority != null) {
                view.setPriority(String.valueOf(priorityRepository.findById(
                        String.valueOf(ticket.getPriority_id()))));
            } else {
                view.setPriority(oldPriority);
            }

            view.setUrl("http://localhost:8080/viewTicket/" + Integer.toString(view.getTicket_id()));

            newList.add(view);
        }
        return newList;
    }

    @Override
    public List<ViewDTO> viewAllUserTicket(int user_id) throws ResourceNotFoundException {

        Optional<User> byId = userRepository.findById(user_id);
        User user = null;

        if (byId.isPresent()) {
            user = byId.get();
        } else {
            throw new ResourceNotFoundException("UserID not found");
        }

        List<Ticket> ticketList = ticketRepository.findAll();

        List<ViewDTO> newList = new ArrayList<>();
        for (Ticket ticket : ticketList) {

            if (user_id == (ticket.getReporter_Id())) {

                ViewDTO viewUser = new ViewDTO();

                viewUser.setTicket_id(ticket.getTicket_id());
                viewUser.setCategory_desc(categoryRepository.findById(ticket.getCategory_id())
                        .get().getCategory_desc());
                viewUser.setSub_category_desc(subCategoryRepository.findById(ticket.getSub_category_id())
                        .get().getSub_category_desc());

                String oldAssignee = "null";
                oldAssignee = ticket.getAssignee_Id();
                if (oldAssignee != null) {
                    viewUser.setAssignee(String.valueOf(adminTeamRepository.findById(
                            String.valueOf(ticket.getAssignee_Id()))));
                } else {
                    viewUser.setAssignee(oldAssignee);
                }

                viewUser.setSubject(ticket.getSubject());
                viewUser.setDescription(ticket.getDescription());
                viewUser.setStatus(statusRepository.findById(ticket.getStatus_id()).get().getStatus());

                String oldPriority = "null";
                oldPriority = ticket.getPriority_id();
                if (oldPriority != null) {
                    viewUser.setPriority(String.valueOf(priorityRepository.findById(
                            String.valueOf(ticket.getPriority_id()))));
                } else {
                    viewUser.setPriority(oldPriority);
                }

                viewUser.setUrl("http://localhost:8080/viewTicket/" + Integer.toString(viewUser.getTicket_id()));

                newList.add(viewUser);
            }
        }
        return newList;
    }

    public ViewDTO viewById(int ticket_id) throws ResourceNotFoundException {

        Optional<Ticket> byId = ticketRepository.findById(ticket_id);
        Ticket ticket = null;

        if (byId.isPresent()) {
            ticket = byId.get();
        } else {
            throw new ResourceNotFoundException("TicketID not found");
        }

        ViewDTO viewUser = new ViewDTO();

        viewUser.setTicket_id(ticket.getTicket_id());
        viewUser.setCategory_desc(categoryRepository.findById(ticket.getCategory_id())
                .get().getCategory_desc());
        viewUser.setSub_category_desc(subCategoryRepository.findById(ticket.getSub_category_id())
                .get().getSub_category_desc());

        String oldAssignee = "null";
        oldAssignee = ticket.getAssignee_Id();
        if (oldAssignee != null) {
            viewUser.setAssignee(String.valueOf(adminTeamRepository.findById(
                    String.valueOf(ticket.getAssignee_Id()))));
        } else {
            viewUser.setAssignee(oldAssignee);
        }

        viewUser.setSubject(ticket.getSubject());
        viewUser.setDescription(ticket.getDescription());
        viewUser.setStatus(statusRepository.findById(ticket.getStatus_id()).get().getStatus());

        String oldPriority = "null";
        oldPriority = ticket.getPriority_id();
        if (oldPriority != null) {
            viewUser.setPriority(String.valueOf(priorityRepository.findById(
                    String.valueOf(ticket.getPriority_id()))));
        } else {
            viewUser.setPriority(oldPriority);
        }
        viewUser.setUrl("http://localhost:8080/viewTicket/" + Integer.toString(viewUser.getTicket_id()));

        return viewUser;
    }

    @Override
    public String commentOnTicket(String message, int ticket_id, int user_id) throws ResourceNotFoundException {

        Optional<Ticket> byId = ticketRepository.findById(ticket_id);
        Ticket ticket = null;

        Optional<User> byUserId = userRepository.findById(user_id);
        User user = null;

        if (byUserId.isPresent() && byId.isPresent()) {
            user = byUserId.get();
            ticket = byId.get();

        } else if (byUserId.isPresent() == false && byId.isPresent() == false) {
            throw new ResourceNotFoundException("UserID and TicketID not found");

        } else if (byId.isPresent() && byUserId.isPresent() == false) {
            throw new ResourceNotFoundException("UserID not found");

        } else if (byId.isPresent() == false && byUserId.isPresent()) {
            throw new ResourceNotFoundException("TicketID not found");
        }

        Comment newComment = new Comment();

        newComment.setUser_id(user_id);
        newComment.setTicket_id(ticket_id);
        newComment.setMessage(message);

        commentRepository.save(newComment);

        return "Successfully added comment on " + ticket_id;
    }

    public String changeStatus(String status_id, int ticket_id, String admin_id) throws ResourceNotFoundException {

        Optional<Ticket> byId = ticketRepository.findById(ticket_id);
        Ticket ticket = null;

        if (byId.isPresent()) {
            ticket = byId.get();
            System.out.println(ticket);

        } else {
            throw new ResourceNotFoundException("ID not found");
        }

        Optional<AdminTeam> byAdminId = adminTeamRepository.findById(admin_id);
        AdminTeam adminTeam = null;

        if (byAdminId.isPresent()) {
            adminTeam = byAdminId.get();

        } else
            throw new ResourceNotFoundException("Incorrect ID");

        Boolean isValid = false;

        List<Status> statusList = statusRepository.findAll();

        for (Status status : statusList) {
            if (status.getStatus_id().equals(status_id)) {
                isValid = true;
            }
        }

        if (isValid == false) {
            throw new ResourceNotFoundException("ID not found");
        }

        if ((ticket.getStatus_id().equals("Stat1")) || (ticket.getStatus_id().equals("Stat2"))
                || (ticket.getStatus_id().equals("Stat3"))) {

            String oldStatus = statusRepository.findById(ticket.getStatus_id()).get().getStatus();
            ticket.setStatus_id(status_id);
            ticket.setLast_modified_datetime(LocalDateTime.now());
            ticketRepository.save(ticket);

            return "Status changed from " + oldStatus + " to " + statusRepository.findById(ticket.getStatus_id())
                    .get().getStatus();

        } else {
            return "Cannot change Status when completed.";
        }
    }

    @Override
    public String changePriority(String priority_id, int ticket_id, int user_id) throws ResourceNotFoundException {

        Optional<Ticket> byId = ticketRepository.findById(ticket_id);
        Ticket ticket = null;

        if (byId.isPresent()) {
            ticket = byId.get();

        } else {
            throw new ResourceNotFoundException("ID not found");
        }

        Optional<User> byUserId = userRepository.findById(user_id);
        User user = null;

        if (byUserId.isPresent()) {
            user = byUserId.get();
        } else {
            throw new ResourceNotFoundException("ID not found");
        }

        Boolean isValid = false;

        List<Priority> priorityList = priorityRepository.findAll();
        for (Priority priority : priorityList) {
            if (priority.getPriority_id().equals(priority_id)) {
                isValid = true;
            }
        }

        if (isValid == false) {
            throw new ResourceNotFoundException("ID not found");
        }

        if (ticket.getStatus_id().equals("Stat1")) {
            String oldPriority = "null";
            oldPriority = ticket.getPriority_id();

            if (oldPriority != null)
                oldPriority = priorityRepository.findById(ticket.getPriority_id()).get().getPriority();

            ticket.setPriority_id(priority_id);

            ticket.setLast_modified_datetime(LocalDateTime.now());
            System.out.println(priority_id);
            ticketRepository.save(ticket);


            return "Priority changed from " + oldPriority + " to " +
                    priorityRepository.findById(ticket.getPriority_id()).get().getPriority();
        } else {
            return "Ticket is not in Open Status.";
        }
    }

    public String deleteTicket(int ticket_id, String admin_id) throws ResourceNotFoundException {

        Optional<Ticket> byId = ticketRepository.findById(ticket_id);
        Ticket ticket = null;

        Optional<AdminTeam> byAdminId = adminTeamRepository.findById(admin_id);
        AdminTeam adminTeam = null;

        if (byId.isPresent() && byAdminId.isPresent()) {
            ticket = byId.get();
            adminTeam = byAdminId.get();

        } else if (byId.isPresent() == false && byAdminId.isPresent() == false) {
            throw new ResourceNotFoundException("TicketID and AdminID is invalid");

        } else if (byId.isPresent() && byAdminId.isPresent() == false) {
            throw new ResourceNotFoundException("AdminId not found");

        } else if (byId.isPresent() == false && byAdminId.isPresent()) {
            throw new ResourceNotFoundException("TicketID not found");
        }
        // LocalDateTime now = LocalDateTime.now()
        // LocalDateTime now1 = now.minusDays(6);
        if (ticket.getStatus_id().equals("Stat4")) /*&& ticket.getLast_modified_datetime().isBefore(now1)*/
            ticketRepository.deleteById(ticket_id);

        return "Ticket deleted successfully";
    }

    @Override
    public String setAssignee(String admin_id, int ticket_id, int user_id) throws ResourceNotFoundException {

        Optional<Ticket> byId = ticketRepository.findById(ticket_id);
        Ticket ticket = null;

        Optional<AdminTeam> byAdminId = adminTeamRepository.findById(admin_id);
        AdminTeam adminTeam = null;

        Optional<User> byUserId = userRepository.findById(user_id);
        User user = null;

        if (byId.isPresent() == false && byAdminId.isPresent() == false && byUserId.isPresent() == false)
            throw new ResourceNotFoundException("Incorrect TicketID, UserID and AdminID");

        if (byId.isPresent()) {
            ticket = byId.get();
        } else {
            throw new ResourceNotFoundException("Incorrect TicketID");
        }

        if (byAdminId.isPresent()) {
            adminTeam = byAdminId.get();
        } else {
            throw new ResourceNotFoundException("Incorrect AdminID");
        }

        if (byUserId.isPresent()) {
            user = byUserId.get();
        } else {
            throw new ResourceNotFoundException("Incorrect UserID");
        }

        ticket.setAssignee_Id(admin_id);
        ticket.setLast_modified_datetime(LocalDateTime.now());
        ticketRepository.save(ticket);

        return "Ticket " + ticket_id + " assigned to " +
                adminTeamRepository.findById(ticket.getAssignee_Id()).get().getAssignee();
    }

    public TicketCommentDTO getTicketCommentById(int ticket_id) throws ResourceNotFoundException {

        TicketCommentDTO ticketCommentDTO = new TicketCommentDTO();

        Optional<Ticket> byId = ticketRepository.findById(ticket_id);
        Ticket ticket = null;

        if (byId.isPresent()) {
            ticket = byId.get();
        } else {
            throw new ResourceNotFoundException("TicketID not found");
        }

        if(ticket.getAssignee_Id() != null) {
            Optional<AdminTeam> byAdminId = adminTeamRepository.findById(ticket.getAssignee_Id());
            AdminTeam adminTeam = null;

            if (byAdminId.isPresent()) {
                adminTeam = byAdminId.get();
                ticketCommentDTO.setAssignee(adminTeam.getAssignee());
            }
        }else{
            ticketCommentDTO.setAssignee("null");
        }


        ticketCommentDTO.setTicket_id(ticket.getTicket_id());
        ticketCommentDTO.setCategory_desc(categoryRepository.findById(ticket.getCategory_id())
                .get().getCategory_desc());
        ticketCommentDTO.setSub_category_desc(subCategoryRepository.findById(ticket.getSub_category_id())
                .get().getSub_category_desc());

        ticketCommentDTO.setSubject(ticket.getSubject());
        ticketCommentDTO.setDescription(ticket.getDescription());
        ticketCommentDTO.setStatus(statusRepository.findById(ticket.getStatus_id()).get().getStatus());

        if(ticket.getPriority_id() != null) {
            Optional<Priority> byPriorityId = priorityRepository.findById(ticket.getPriority_id());
            Priority priority = null;

            if (byPriorityId.isPresent()) {
                priority = byPriorityId.get();
                ticketCommentDTO.setPriority(priority.getPriority());
            }
        }else{
            ticketCommentDTO.setPriority("null");
        }

        ticketCommentDTO.setUrl("http://localhost:8080/viewTicket/" + Integer.toString(ticketCommentDTO.getTicket_id()));

        List<Comment> commentList = commentRepository.findAll();
        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (Comment comments : commentList) {
            if (ticket_id == (comments.getTicket_id())) {

                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setUser_id(comments.getUser_id());
                commentDTO.setMessage(comments.getMessage());

                commentDTOList.add(commentDTO);
            }
            ticketCommentDTO.setComments(commentDTOList);
        }
        return ticketCommentDTO;
    }

}


