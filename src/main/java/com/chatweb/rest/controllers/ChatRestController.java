/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.chatweb.rest.controllers;

import com.chatweb.models.dtos.MessageDTO;
import com.chatweb.services.MessageServiceInterface;
import com.chatweb.services.impl.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author ACER
 */
@WebServlet("/ChatRestController")
public class ChatRestController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MessageServiceInterface messageServiceInterface = MessageService.getInstance();

    public ChatRestController() {
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
        String sender = request.getParameter("sender");
        String receiver = request.getParameter("receiver");
        
        List<MessageDTO> messages = messageServiceInterface.getAllMessagesBySenderAndReceiver(sender, receiver);
        
        messageServiceInterface.updateStatus(sender, receiver);

        ObjectMapper objectMapper = new ObjectMapper();
        
        String json = objectMapper.writeValueAsString(messages);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }

}
