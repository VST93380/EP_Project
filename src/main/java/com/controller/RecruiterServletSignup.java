package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Recruiter;
import com.model.RecruiterManager;
import com.model.Student;
import com.model.StudentManager;

/**
 * Servlet implementation class RecruiterServletSignup
 */
public class RecruiterServletSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruiterServletSignup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("recname");
		String email=request.getParameter("recemail");
		String password=request.getParameter("recpass");
		
		Recruiter st=new Recruiter();
		st.setName(name);
		st.setEmail(email);
		st.setPassword(password);
		
RecruiterManager em=new RecruiterManager();
		PrintWriter out=response.getWriter();
		try {
			out.println(em.insertData(st));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
