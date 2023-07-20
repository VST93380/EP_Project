package com.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Company;
import com.model.Employee;
import com.model.Recruiter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RetrieveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/project";
        String username = "root";
        String password = "143Amma143*";
        List<Employee> emp = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Establish a database connection
            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();

            // Retrieve data from the database table
            String sql = "SELECT * FROM employee";
            rs = stmt.executeQuery(sql);

            // Create a list to store the retrieved data
            // Iterate over the result set and create Recruiter objects
            while (rs.next()) {
                Employee obj=new Employee();

                int id = rs.getInt(1);
                String fullname=rs.getString(2);
                String email=rs.getString(3);
                int backlogs=rs.getInt(4);
                String about=rs.getString(5);
                Blob imageBlob = rs.getBlob(6);
                byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
               
                obj.setId(id);
                obj.setFullname(fullname);
                obj.setEmail(email);
                obj.setBacklogs(backlogs);
                obj.setAboutYourself(about);
                obj.setResume(imageBytes);
                emp.add(obj);
            }
            // Store the data in request attribute
            request.setAttribute("list", emp);
RequestDispatcher rd=request.getRequestDispatcher("table1.jsp");
rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
        	System.out.println("Size of the list is"+emp.size());
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
