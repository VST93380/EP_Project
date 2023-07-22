package com.controller;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

import com.model.Recruiter;
import com.model.RecruiterManager;

/**
 * Servlet implementation class HomeRecruiter
 */
@MultipartConfig
public class HomeRecruiter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeRecruiter() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Handle GET request
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("company-name");
		Part image = request.getPart("company-photo");
		String contact = request.getParameter("contact-number");
		String details = request.getParameter("company-details");
		String email = request.getParameter("email");
		String branch = request.getParameter("branch");
		String url = "jdbc:mysql://localhost:3306/project";
		String username = "root";
		String password = "143Amma143*";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			ps = con.prepareStatement("INSERT INTO company VALUES(?,?,?,?,?,?);");
			ps.setString(1, name);
			Blob imageBlob = con.createBlob();
			InputStream inputStream = image.getInputStream();
			try (OutputStream outputStream = imageBlob.setBinaryStream(1)) {
				byte[] buffer = new byte[1000000];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
			}
			ps.setBlob(2, imageBlob);
			ps.setString(3, contact);
			ps.setString(4, details);
			ps.setString(5, email);
			ps.setString(6, branch);
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
		response.sendRedirect("join1.html");
	}
}
