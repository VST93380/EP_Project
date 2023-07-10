package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Student;
import com.model.StudentManager;

/**
 * Servlet implementation class StudentService
 */
public class StudentService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int id=Integer.parseInt(request.getParameter("stuid"));
		String password=request.getParameter("stupass");
		StudentManager obj=new StudentManager();
		List<Student> l = null;
		try {
			l=obj.getDetails();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean val=false;
		for(int i=0;i<l.size();i++)
		{
			if(id==l.get(i).getId()&&password.equals(l.get(i).getPassword()))
			{
				val=true;
				break;
			}
		}
//		String contextPath = request.getContextPath();
//		System.out.println(contextPath);
//	    String pagePath = contextPath + "/index.html";	
//	    String pagePath1 = contextPath + "src/main/webapp/index.html";
		if(val)
		{
//		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
//		    dispatcher.forward(request, response);
			response.sendRedirect("homepage.html");
//			PrintWriter pw=response.getWriter();
//			pw.print("valid login");
			}
		else {
//			RequestDispatcher rd=request.getRequestDispatcher("NewFile.html");
//			rd.forward(request, response);
			response.sendRedirect("invalidlogin.html");
//			PrintWriter pw=response.getWriter();
//			pw.print("invalid login");
		}
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.print("Hi");
			}

}
