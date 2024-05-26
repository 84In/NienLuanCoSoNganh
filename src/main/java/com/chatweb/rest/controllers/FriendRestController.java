/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.chatweb.rest.controllers;

import com.chatweb.services.FriendServiceInterface;
import com.chatweb.services.impl.FriendService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ACER
 */
@WebServlet(name = "FriendRestController", urlPatterns = {"/FriendRestController"})
public class FriendRestController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private FriendServiceInterface friendServiceInterface = FriendService.getInstance();

    public FriendRestController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Set request character encoding to UTF-8
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action.contains("addfriend")) {
            String user1 = request.getParameter("user1").trim();
            String user2 = request.getParameter("user2").trim();

            friendServiceInterface.saveFriend(user1, user2);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(true);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter printWriter = response.getWriter();
            printWriter.print(json);
            printWriter.flush();
        }
        if (action.contains("acceptfriend")) {
            String user1 = request.getParameter("user1").trim();
            String user2 = request.getParameter("user2").trim();

            friendServiceInterface.updateFriendByUsername(user1, user2, 2);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(true);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter printWriter = response.getWriter();
            printWriter.print(json);
            printWriter.flush();
        }
    }
}
