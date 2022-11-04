package databasequeries5;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {	
	 
    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://DESKTOP-VJIARNC:1433;"
                        + "database=PRS;"                        
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
        				+ "integratedSecurity=true;"	
                        + "loginTimeout= 15;";

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            // Code here.
        	System.out.println("good");
        	Statement statement = connection.createStatement();
        	ResultSet rs = statement.executeQuery
        			("SELECT FirstName, LastName " +
        			 "FROM Users WHERE ID BETWEEN 50 AND 100");		
        			
        			 
        	while(rs.next()) {
        		/*System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + 
        				rs.getString(3) + rs.getString(4) + rs.getString(5) +
        				rs.getString(6) + rs.getString(7) + rs.getString(8));
        		*/
        		System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        	}
        	connection.close();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
