/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.chatweb.rest.controllers;

import com.chatweb.models.dtos.UserDTO;
import com.chatweb.services.BoxChatServiceInterface;
import com.chatweb.services.MessageServiceInterface;
import com.chatweb.services.UserServiceInterface;
import com.chatweb.services.impl.BoxChatService;
import com.chatweb.services.impl.MessageService;
import com.chatweb.services.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author ACER
 */
@WebServlet(name = "UserRestController", urlPatterns = {"/UserRestController"})
public class UserRestController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserServiceInterface userServiceInterface = UserService.getInstance();
    private MessageServiceInterface messageServiceInterface = MessageService.getInstance();
    private BoxChatServiceInterface boxChatServiceInterface = BoxChatService.getInstance();
    

    public UserRestController() {
        super();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Set request character encoding to UTF-8
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action.contains("listuser")) {
            String text = request.getParameter("text").trim();
            String username = request.getParameter("username").trim();

            List<UserDTO> users = userServiceInterface.findFriendsByUsernameOrName(text, username);
            System.out.println("com.chatweb.rest.controllers.UserRestController.doGet() "+users);  
            ObjectMapper objectMapper = new ObjectMapper();

            String json = objectMapper.writeValueAsString(users);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter printWriter = response.getWriter();
            printWriter.print(json);
            printWriter.flush();
        }
        if (action.contains("acceptfriends")) {
            String username = request.getParameter("username").trim();

            List<UserDTO> users = userServiceInterface.findUserDontAcceptByUsername(username);

            ObjectMapper objectMapper = new ObjectMapper();

            String json = objectMapper.writeValueAsString(users);
//            System.out.println("com.chatweb.rest.controllers.UserRestController.doGet() "+json);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter printWriter = response.getWriter();
            printWriter.print(json);
            printWriter.flush();
        }
        if (action.contains("getfriends")) {
            String username = request.getParameter("username").trim();

            List<UserDTO> users = userServiceInterface.getListFriendsContactByUsername(username);

            for (int i = 0; i < users.size(); i++) {
                Long id = boxChatServiceInterface.findIdBySenderAndReceiver(username, users.get(i).getUsername());
//                System.out.println("com.chatweb.rest.controllers.UserRestController.doGet() " + id);
                users.get(i).setCountMess(messageServiceInterface.countMessage(users.get(i).getUsername(),id));
                System.out.println("com.chatweb.rest.controllers.UserRestController.doGet() " + users.get(i).getCountMess());
            }
            
            users=users.stream().sorted((o1, o2) -> {
                
                return o2.getCountMess()-o1.getCountMess();
                
            }).collect(Collectors.toList());

            ObjectMapper objectMapper = new ObjectMapper();

            String json = objectMapper.writeValueAsString(users);
//            System.out.println("com.chatweb.rest.controllers.UserRestController.doGet() "+json);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter printWriter = response.getWriter();
            printWriter.print(json);
            printWriter.flush();
        }
    }

}
