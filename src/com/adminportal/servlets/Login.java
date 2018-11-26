package com.adminportal.servlets;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class Login {

    private final static String DATASOURCE_CONTEXT = "jdbc/adminPortalDatabase";

    public int loginAttempt(LoginBean loginBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(DATASOURCE_CONTEXT);
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM login WHERE username=? AND password=?");
            pstmt.setString(1, loginBean.getUsername());
            pstmt.setString(2, loginBean.getPassword());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                loginBean.setuserID(rs.getInt(1));
                loginBean.setAccessLevel(rs.getInt(3));
                setAccessName(loginBean);
                return loginBean.getAccessLevel();
            }
            else{
                return -1;
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
        return -1;
    }

    public void setAccessName(LoginBean loginBean){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(DATASOURCE_CONTEXT);
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("SELECT user_type FROM user_types WHERE accessLevel=?");
            pstmt.setInt(1, loginBean.getAccessLevel());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                loginBean.setAccessName(rs.getString(1));
            }
        }
        catch (SQLException | NamingException e){
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}