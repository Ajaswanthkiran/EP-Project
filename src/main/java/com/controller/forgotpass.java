package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/forgotpass")
public class forgotpass extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
   
    public forgotpass() {
        super();
        // TODO Auto-generated constructor stub
    }

  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    //response.getWriter().append("Served at: ").append(request.getContextPath());
    String email = request.getParameter("email");
        String passwo = request.getParameter("passwo");
        
        try {
            String Driver="com.mysql.cj.jdbc.Driver";
  
        
         String url="jdbc:mysql://localhost:3306/ajk";
            
            
            String uname="root";
            
            String password="1234";
            
            Class.forName(Driver);
            Connection con=DriverManager.getConnection(url,uname,password);
            String sql = "UPDATE project SET password = ? WHERE email = ? or username= ?";
              
             
              PreparedStatement pstmt = con.prepareStatement(sql);
              pstmt.setString(1, passwo);
              pstmt.setString(2, email);
              pstmt.setString(3, email);

              // Execute the update
              int rowsUpdated = pstmt.executeUpdate();
              
              
              
              if (rowsUpdated > 0) {
                  System.out.println("success");
                  response.sendRedirect("home.jsf");
                 
              } else {
                 System.out.println("!success not");
                 
                 response.sendRedirect("contactus.jsf");
              }
              
              
              
          
        }catch(Exception e) {
          System.out.println(e.getLocalizedMessage());
          System.out.println(e.getMessage());
        }
      
  }

 
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}