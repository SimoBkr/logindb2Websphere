package WebApp.JNDI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;


public class testdb2Connection {

	public static void main(String[] args) {
		
		String jdbcClassName ="com.ibm.db2.jcc.DB2Driver";
		String url ="jdbc:db2://localhost:50000/testDB";
		String user ="db2admin";
		String password ="ntic";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null ;
		boolean found = false;
		Connection connection = null ;
		
		try {
			Class.forName(jdbcClassName);
			connection = DriverManager.getConnection(url,user,password);
			
            Context initialContext  = new InitialContext();
			
			Context envContext  = (Context) initialContext.lookup("java:comp/env");
			
			DataSource dataSource = (DataSource) envContext.lookup("jndi/db2");
			
			connection = dataSource.getConnection();
			
			if(connection != null) {
				System.out.println("DB connected..");
			}else {
				System.out.println("DB connection failed");
			}
			preparedStatement = connection.prepareStatement("select * from db2admin.user");
			resultSet = preparedStatement.executeQuery();
			if(resultSet!=null) {
				while(resultSet.next()) {
					found = true;
					System.out.println("Email: "+ resultSet.getString("email"));
					System.out.println("Password: "+ resultSet.getString("password"));
					System.out.println("-------------"); 
				}
			}
			if(found == false){
				System.out.println("No information found");
				} 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
