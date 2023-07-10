package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
	String url="jdbc:mysql://localhost:3306/project";
	  String username="root";
	  String password="143Amma143*";
	  Connection con=null;
	  PreparedStatement ps=null;
	  List<Student> al=new ArrayList<>();
	  
	  public List<Student> getDetails() throws Exception{
		    con=DriverManager.getConnection(url,username,password);
		    String s="select * from student";
		    ps=con.prepareStatement(s);
		    ResultSet rs=ps.executeQuery();
		    while(rs.next()) {
		      Student st=new Student();
		      st.setId(rs.getInt(1));
		      st.setPassword(rs.getString(2));
		      al.add(st); 
		    }
		    return al;
		    
		  }

	public String insertData(Student st) throws SQLException {
		 con=DriverManager.getConnection(url,username,password);
		    ps=con.prepareStatement("insert into student values(?,?);");
		    ps.setInt(1, st.getId());
		    ps.setString(2, st.getPassword());
System.out.println(ps.execute());		
return "Insertion done successfully";
	}
}
