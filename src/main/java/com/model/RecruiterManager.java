package com.model;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;
public class RecruiterManager {

		String url="jdbc:mysql://localhost:3306/project";
		  String username="root";
		  String password="143Amma143*";
		  Connection con=null;
		  PreparedStatement ps=null;
		  List<Recruiter> al=new ArrayList<>();
		  
		  public List<Recruiter> getDetails() throws Exception{
			    con=DriverManager.getConnection(url,username,password);
			    String s="select * from recruiter";
			    ps=con.prepareStatement(s);
			    ResultSet rs=ps.executeQuery();
			    while(rs.next()) {
			      Recruiter st=new Recruiter();
			      st.setName(rs.getString(1));
			      st.setEmail(rs.getString(2));
			      st.setPassword(rs.getString(3));
			      al.add(st); 
			    }
			    return al; 
			  }
		public String insertData(Recruiter st) throws SQLException {
			 con=DriverManager.getConnection(url,username,password);
			    ps=con.prepareStatement("insert into Recruiter values(?,?,?);");
			    ps.setString(1, st.getName());
			    ps.setString(2,st.getEmail());
			    ps.setString(3, st.getPassword());
	System.out.println(ps.execute());		
	return "Insertion done successfully";
		}
	}


