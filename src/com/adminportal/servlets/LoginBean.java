package com.adminportal.servlets;

public class LoginBean {
    private int userID;
    private String username;
    private String password;
    private int accessLevel;
    private String accessName;

    public int getuserID() { return userID; }
    public void setuserID(int userID) { this.userID = userID; }
    public String getUsername(){ return username; }
    public void setUsername(String username){ this.username = username; }
    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password = password; }
    public int getAccessLevel(){ return accessLevel; }
    public void setAccessLevel(int accessLevel){ this.accessLevel = accessLevel; }
    public void setAccessName(String accessName) { this.accessName = accessName; }
    public String getAccessName() { return accessName; }
}
