package com.controller;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Company;
import com.model.Recruiter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomeRecruiter1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/project";
        String username = "root";
        String password = "143Amma143*";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a database connection
            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();

            // Retrieve data from the database table
            String sql = "SELECT * FROM company";
            rs = stmt.executeQuery(sql);

            // Create a list to store the retrieved data
            List<Company> recruiters = new ArrayList<>();

            // Iterate over the result set and create Recruiter objects
            while (rs.next()) {
                String name = rs.getString("name");
                Blob imageBlob = rs.getBlob("image");
                byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
                String contact = rs.getString("contact");
                String email=rs.getString("email");
                String details = rs.getString("details");
                String branch = rs.getString("branch");

                Company recruiter = new Company();
                recruiter.setName(name);
                recruiter.setImage(imageBytes);
                recruiter.setContact(contact);
                recruiter.setDetails(details);
                recruiter.setBranch(branch);
                recruiter.setEmail(email);
                recruiters.add(recruiter);
            }

            // Store the data in request attribute
            request.setAttribute("recruiters", recruiters);

response.sendRedirect("join.html");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
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
