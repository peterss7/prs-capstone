import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

import com.opencsv.CSVWriter;
import java.io.Writer;

import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVReader;
import java.util.Date;
import java.util.List;
import java.nio.file.Files;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.nio.file.Paths;



public class UsersQueries {

	private static final String STRING_ARRAY_SAMPLE = "./prs-users-results.csv";
	
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
        	
        	ResultSetMetaData rsmd = rs.getMetaData();
        			
        	String[] csvData;
        	
        	System.out.println("Made connection");
        	
        	try (       			
      
        			Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));
        	        			
        	        CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
        			
        			
        	    ) {
        		
        		System.out.println("can make the file");

        		 
				
            	while(rs.next()) {
            		
            		
            		
            		String csvLine [] = new String[rsmd.getColumnCount()];
            		for (int i = 0; i < rsmd.getColumnCount(); i++) {
            			csvLine[i] = rs.getString(i + 1);
            		}
            		
            		
            		csvWriter.writeNext(csvLine);
            	}
        	}
        	catch (IOException e) {
                e.printStackTrace();
            }
        	
        	
        	
        	connection.close();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }

	}

}
