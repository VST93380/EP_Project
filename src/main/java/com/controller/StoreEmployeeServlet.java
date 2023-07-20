package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.model.Employee;

@WebServlet("/StoreEmployeeServlet")
@MultipartConfig
public class StoreEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/project";
        String username = "root";
        String password = "143Amma143*";
        Connection con = null;
        PreparedStatement ps = null;

        int id = Integer.parseInt(request.getParameter("id"));
        String fullname = request.getParameter("name");
        String email = request.getParameter("email");
        int backlogs = Integer.parseInt(request.getParameter("backlogs"));
        String about_yourself = request.getParameter("about");
        Part image = request.getPart("resume");

        try {
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?);");
            ps.setInt(1, id);
            Blob imageBlob = con.createBlob();
            InputStream inputStream = image.getInputStream();
            try (InputStream inStream = image.getInputStream()) {
                byte[] buffer = new byte[1000000];
                int bytesRead;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    imageBlob.setBytes(1, buffer, 0, bytesRead);
                }
            }
            ps.setString(2, fullname);
            ps.setString(3, email);
            ps.setInt(4, backlogs);
            ps.setString(5, about_yourself);
            ps.setBlob(6, imageBlob);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("join.html");
    }
}
