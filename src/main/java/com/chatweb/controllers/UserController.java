/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.chatweb.controllers;

import com.chatweb.models.User;
import com.chatweb.security.AES256;
import com.chatweb.services.UserServiceInterface;
import com.chatweb.services.impl.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author ACER
 */
@WebServlet("/register")
@MultipartConfig
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;

    private String secretKey = "123@ABC";
    private String salt = "chatbox";

    private UserServiceInterface userService = UserService.getInstance();

    public UserController() {
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
        User user = (User) request.getSession().getAttribute("user");

        if (user == null || request.getSession() == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/register.jsp");
            rd.forward(request, response);
        }else{
            response.sendRedirect("/ChatWeb");
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
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = AES256.encrypt(request.getParameter("password"), secretKey, salt);
        String name = request.getParameter("name");
        Part filePart = request.getPart("avatar");
        
        User user = userService.findUserLogin(username, password);

        String destPage = "/ChatWeb";

        if (user == null) {
            userService.saveUser(username, password, name, filePart.getInputStream());
            System.out.println("com.chatweb.controllers.UserController.doPost() Save thành công");
        }
        response.sendRedirect(destPage);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
