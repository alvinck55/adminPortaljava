package com.adminportal.servlets;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet",urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final static String DATASOURCE_CONTEXT = "jdbc/adminPortalDatabase";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Login login = new Login();
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(request.getParameter("username"));
        loginBean.setPassword(request.getParameter("password"));
        int intResponse = login.loginAttempt(loginBean);
        if (intResponse == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("user",loginBean);
            response.sendRedirect("home.jsp");
            return;
        }
        else if(intResponse == 2){
            HttpSession session = request.getSession();
            session.setAttribute("user",loginBean);
            response.sendRedirect("adminHome.jsp");
            return;
        }
        else if(intResponse == 3){
            HttpSession session = request.getSession();
            session.setAttribute("user",loginBean);
            response.sendRedirect("profHome.jsp");
            return;
        }
        else if(intResponse == -1){
            request.setAttribute("responsefromServer", "Invalid Credentials");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        else{
            request.setAttribute("responsefromServer", "Invalid Credentials");
            response.sendRedirect("index.jsp");
            request.getRequestDispatcher("index.jsp").forward(request,response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }
}

