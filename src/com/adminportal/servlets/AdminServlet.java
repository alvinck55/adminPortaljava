package com.adminportal.servlets;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    private final static String DATASOURCE_CONTEXT = "jdbc/adminPortalDatabase";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session = request.getSession(false);
        String submitRequest = request.getParameter("Submit");
        if(session == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        if(submitRequest == null){
            response.sendRedirect("index.jsp");
            return;
        }
        else if(submitRequest.equals("Edit")){
            editUser(request,response);
            return;
        }
        else if(submitRequest.equals("Create/Edit/Delete/View Users")){
            getUsers(request,response);
            displayUsers(request,response);
        }
        else if(submitRequest.equals("Confirm Edit")){
            confirmEdit(request,response);
            getUsers(request,response);
            displayUsers(request,response);
        }
        else if(submitRequest.equals("Delete")){
            deleteUser(request,response);
            getUsers(request,response);
            displayUsers(request,response);
        }
        else if(submitRequest.equals("Create User")){
            createUser(request,response);
        }
        else if(submitRequest.equals("Confirm Creation")){
            confirmCreate(request,response);
            getUsers(request,response);
            displayUsers(request,response);
        }
        else{
            request.getRequestDispatcher("adminHome.jsp").forward(request,response);
        }



    }

    protected void getUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<LoginBean> results = new ArrayList<LoginBean>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(DATASOURCE_CONTEXT);
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM login ");
            ResultSet rs = pstmt.executeQuery();
            LoginBean user = null;
            while(rs.next()){
                user = new LoginBean();
                user.setuserID(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setAccessLevel(rs.getInt(3));
                results.add(user);
            }
        } catch (SQLException | NamingException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(pstmt != null){
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        request.setAttribute("userList",results);
    }

    protected void displayUsers(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        request.getRequestDispatcher("userList.jsp").forward(request,response);
    }

    protected void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userID",request.getParameter("userID"));
        request.setAttribute("username",request.getParameter("username"));
        request.setAttribute("accessLevel",request.getParameter("accessLevel"));
        request.getRequestDispatcher("editList.jsp").forward(request,response);
    }

    protected void confirmEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(DATASOURCE_CONTEXT);
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("UPDATE login SET username=?,accessLevel=? WHERE userID=? ");
            pstmt.setString(1, request.getParameter("username"));
            pstmt.setString(2, request.getParameter("accessLevel"));
            pstmt.setString(3, request.getParameter("userID"));
            pstmt.executeUpdate();
        } catch (SQLException | NamingException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(pstmt != null){
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
    protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(DATASOURCE_CONTEXT);
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("DELETE FROM login WHERE userID=?");
            pstmt.setString(1,request.getParameter("userID"));
            pstmt.executeUpdate();
        } catch (SQLException | NamingException e){
            e.printStackTrace();
        } finally{
            try{
                if(pstmt != null){
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    protected void createUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("createUser.jsp").forward(request,response);
    }

    protected void confirmCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(DATASOURCE_CONTEXT);
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO login(username,accesslevel,password) VALUES (?,?,?)");
            pstmt.setString(1, request.getParameter("username"));
            pstmt.setString(2, request.getParameter("accessLevel"));
            pstmt.setString(3, request.getParameter("password"));
            pstmt.executeUpdate();
        } catch (SQLException | NamingException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(pstmt != null){
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
