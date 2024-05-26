/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.chatweb.controllers;

import com.chatweb.daos.impl.UserDao;
import com.chatweb.models.User;
import com.chatweb.services.UserServiceInterface;
import com.chatweb.services.impl.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ACER
 */
@WebServlet(name = "chatbox", urlPatterns = {"/chatbox"})
//@WebServlet("/chatbox")
public class ChatController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserServiceInterface userService = UserService.getInstance();
    private UserDao userDao = new UserDao();

    public ChatController() {
        super();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        User currentUser = (User) request.getSession().getAttribute("user");
        if (currentUser != null) {

//            List<User> friends = userService.findFriendsByUsername(currentUser.getUsername());

            request.setAttribute("user", currentUser);
//            request.setAttribute("friends", friends);

            RequestDispatcher rd = request.getRequestDispatcher("/views/chatbox.jsp");
            rd.forward(request, response);
        }
        else{
            response.sendRedirect("login");
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Lấy request từ click function(id + username);
        //Tạo session ("receiver", addUser);
        // resquestDisPattern chat;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
}
