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
        if(session == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        /*if(request.getParameter("Submit") == null){
            response.sendRedirect("index.jsp");
            return;
        }
        else if(request.getParameter("Submit").equals("editUser")){
            editUser(request,response);
            return;
        }*/

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
        request.getRequestDispatcher("userList.jsp").forward(request,response);


    }

    protected void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(DATASOURCE_CONTEXT);
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("UPDATE login SET ")
        }*/
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
