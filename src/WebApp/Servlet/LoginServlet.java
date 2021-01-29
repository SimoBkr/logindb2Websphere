package WebApp.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import WebApp.JNDI.testdb2Connection;
import WebApp.Model.User;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet{
	
	 String jdbcClassName ="com.ibm.db2.jcc.DB2Driver";
		String url ="jdbc:db2://localhost:50000/test";
		String user ="Administrator";
		String password ="simobkr97";
	 PreparedStatement preparedStatement = null;
	 ResultSet resultSet = null ;
	 boolean found = false;
	 Connection connection = null ;
	 Connection connectionJNDI = null ;
	 
	 String emailuser = null;
	 String passworduser = null;
	
	
    public LoginServlet() {}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*try {
			
			Class.forName(jdbcClassName);
			//Class.forName(jdbcClassName);
			
			connection = DriverManager.getConnection(url,user,password);
			
			Context initialContext  = new InitialContext();
			
			Context envContext  = (Context) initialContext.lookup("java:comp/env");
			
			DataSource dataSource = (DataSource) envContext.lookup("jndi/db2");
			
			connection = dataSource.getConnection();
			
			if(connection != null) {
				System.out.println("DB connected");
			}else {
				System.out.println("DB connection failed");
			}
			
			
			preparedStatement = connection.prepareStatement("select * from user");
			resultSet = preparedStatement.executeQuery();
			if(resultSet!=null) {
				
				while(resultSet.next()) {
					found = true;
					emailuser = resultSet.getString("email");
					passworduser = resultSet.getString("password");
				}*/
				
				//emailuser.equals("simo@simo.com") && passworduser.equals("simo")
				if("simo@simo.com".equals("simo@simo.com") && "simo".equals("simo")) {
					//request.getSession().setAttribute("user", new User(emailuser,passworduser));
					request.getRequestDispatcher("/WEB-INF/pages/securePage.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("/WEB-INF/pages/securePage.jsp").forward(request, response);
					}
		//	}
			
		/*	if(found == false){
				System.out.println("No information found");
				} 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}*/
		
		
		
	}
}
