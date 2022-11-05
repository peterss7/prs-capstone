import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersQueries {

	public static void main(String[] args) {
        // Build the string that makes a connection with the database
		String connectionUrl =
                "jdbc:sqlserver://DESKTOP-VJIARNC:1433;" // ServerName, default instance port
                        + "database=PRS;" // database name                        
                        + "encrypt=true;" 
                        + "trustServerCertificate=true;"
        				+ "integratedSecurity=true;"	
                        + "loginTimeout= 15;";

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            
        	// This block is executed if a connection is made
        	Statement statement = connection.createStatement();
        	ResultSet rs = statement.executeQuery
        			("SELECT FirstName, LastName " +
        			 "FROM Users WHERE ID BETWEEN 50 AND 100");		
        			
        			 
        	// Loop through the results, row by row, printing to console
        	while(rs.next())        		
        		System.out.println(rs.getString(1) + " " + rs.getString(2));
        	
        	connection.close();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }

	}

}
