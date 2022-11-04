package com.vogella.maven.eclipse;

import org.fluttercode.datafactory.impl.DataFactory;
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

import static org.junit.Assert.assertTrue;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static char [] specialCharacters = {'!', '@', '#', '$', '%', '^', '*', '(', ')'};
	
	private static String[] areaCodes = {"205", "907", "480", "479", "209", "303", 
			"203", "302", "239", "229", "808", "208", "217", "219", "319",
		"316", "859", "225", "207", "240", "339", "231", "218", "228", "314",
		"406", "308", "702", "603", "201", "505", "212", "252", "701", "513",
		"405", "458", "215", "401", "803", "605", "423", "210","385", "802",
		"276", "206", "304", "262", "307"};
	
	private static String[] stateAbvs = {"AL", "AK", "AZ", "AR", "CA", "CO", 
			"CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA",
		"KS", "859", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO",
		"MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "513",
		"OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX","UT", "VT",
		"VA", "WA", "WV", "WI", "WY"};
	
	private static String[] emailHosts = { "gma1l", "hotma1l", "yah00",
		"somema1l", "everyma1l", "ma1lbox", "b1zmail", "ma1l2u" };
	
	private static String[] tlds = { "org", "net", "com", "biz", "us", "co.uk" };

	
	private static String city;
	private static String state;    	
	
	
	private static String[] firstNames = {"Emma", "Sophie", "Isabell", "Chloe", "Eleanor", "Ivy",
		"Elizabeth", "Abigail", "Zoey", "Emily", "Delilah", "Dianna", "Melissa",
		"Veronica", "Gabbi", "Autumn", "Lexie", "Alexa", "Madison", "Leah",
		"Yvette", "Audrey", "Melanie", "Natalie", "Jennifer", "Allison", "Ellie",
		"Haley", "Andrea", "Eva", "Savannah", "Niki", "Nicole", "Brooke",
		"Megan", "Claire", "Katherine", "Lucy", "Jade", "Valerie", "Maria",
		"Ariel", "Melody", "Jasmine", "Alyssa", "Alexandra", "Amy", "Julia",
		"Angela", "Quinn", "Iris", "Michelle", "Kylie", "Gabriella", "Ana",
		"Vanessa", "Mackenzie", "Jessica", "Stephanie", "Bailey", "Jacqueline",
		"Juliet", "Annabelle", "Miranda", "Sydney", "Lauren", "Laura", 
		"Catherine", "Amanda", "Alicia", "Norah", "Lydia", "Camilla", "Rachel",
		"Rebecca", "Mary", "Sierra", "Sage", "April", "Lilly", "Joy", "Hope",
		"Grace", "Aileen", "Kate", "Carla", "Tiffany", "Chelsea", "Trinity",
		"Daniella", "Ruth", "Carolina", "Marilyn", "Clarissa", "Larissa",
		"Helen", "Maggie", "Michaela", "Sasha", "Dana", "Megan", "Molly",
		"Karen", "Eileen", "Cassandra", "Melinda", "Scarlet", "Heidi",
		"Josie", "Bethany", "Evie", "Helena", "Crystal", "Jamie", "Jaime",
		"Angie", "Erin", "Kelly", "Abby", "Freya", "Hanna", "Annabella",
		"Anabelle", "Brianna", "Ophelia", "Monica", "Jessie", "Felicity",
		"Makenzie", "Linda", "Eve", "Averie", "Angelica", "Angelique",
		"Liv", "Wendy", "Mina", "Sandra", "Cindy", "Raven", "Shelby",
		"Alisa", "Kelsey", "Brittany", "Katie", "Sylvia", "Adrianna",
		"Christina", "Erika", "Jenny", "Lara", "Kathryn", "Justice", 
		"Zelda", "Ellen", "Dana", "Judith", "Magdalena", "Marishka",
		"Vivianna", "Nadine", "Tara", "Janet", "Claudia", "Danica",
		"Donna", "Tina", "Kathy", "Valerie", "Jewel", "Katrina"};
		
	private static String[] lastNames = {"Baggins", "Aragorn", "Arwen", "Balin", 
    	"Brandybuck", "Bracegirdle", "Cotton", "Celeborn", "Cirdan", "Deagol",
    	"Denethor", "Gamgee", "Elendil", "Elrond", "Eomer", "Eowyn", "Faramir",
    	"Bolger", "Galadriel", "Gandalf", "Gimli", "Gollum", "Wormtongue",
    	"Hamfast", "Nazgul", "Legolas", "Khamul", "Isildur", "Maggot", "Theoden",
    	"Shelob", "Took", "Treebeard", "Proudfoot", "Sackville", "Sandyman",
    	"Butterbur", "Lurtz", "Shagrat", "Gothmog", "Gorbag", "Sharku", 
    	"Hurin", "Gloin", "Smeagol", "Shadowfax", "Dwalin", "Celebrimbor",
    	"Feanor", "Finwe", "Miriel", "Maedhros", "Beren", "Melian",
    	"Ungoliant", "Felagund", "Turambar", "Turin", "Idril", "Tuor",
    	"Oakenshield", "Thingol", "Thranduil", "Morgoth", "Fili",
    	"Kili", "Dori", "Nori", "Ori", "Bifur", "Bofur", "Bombur",
    	"Goldberry", "Bombadil", "Imrahil", "Halbarad", "Elladan",
    	"Elrohir", "Ferny", "Alatar", "Annatar", "Radagast", "Pollando",
    	"Sauruman", "Willow", "Quickbeam", "Gildor", "Glorfindel",
    	"Erestor", "Erkenbrand", "Beregond", "Mablung", "Damrod",
    	"Elfhelm", "Sauron", "The Grey", "The White", "The Brown",
    	"Olorin", "Mithrandir", "Grahame", "Stormcrow", "Amrod",
    	"Angrod", "Pharazon", "Barahir", "Bregolas", "Curufin", 
    	"Durin", "Ecthelion", "Elros", "Elwing", "Earendil", "Finarfin",
    	"Fingolfin", "Finrod", "Gil-Galad", "Huan", "Ancalagon", "Eru",
    	"Illuvatar", "Luthien", "Mandos", "Manwe", "Maeglin", "Melkor",
    	"Nesa", "Nimloth", "Nimrodel", "Orodreth", "Orome", "Ingwe",
    	"Telchar", "Thorondor", "Aldarion", "Anarion", "Palantir",
    	"Alcarin", "Nienna", "Yavanna", "Varda", "Morgoth", "Ulmo",
    	"Sulimo", "Tulkas", "Valaquenta", "Elentari", "Kementari"};
	
	private static final String STRING_ARRAY_SAMPLE = "./psr-database-customers.csv";
	
    public static void main( String[] args ) throws IOException
    {	    	
    	
    	Thread.dumpStack();
    	
    	List<String[]> csvData = generateCustomerData();
    	
    	 try (
	        Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));
	
	        CSVWriter csvWriter = new CSVWriter(writer,
            CSVWriter.DEFAULT_SEPARATOR,
            CSVWriter.NO_QUOTE_CHARACTER,
            CSVWriter.DEFAULT_ESCAPE_CHARACTER,
            CSVWriter.DEFAULT_LINE_END);
	    ) {
    		 
    		String[] headerRecord = {"ID", "Username", "Password", "FirstName",
    				"LastName", "PhoneNumber", "Email","IsReviewer", "IsAdmin"};
	        
	        csvWriter.writeNext(headerRecord);
	        Random rand = new Random();
	
	        for (int i = 0; i < csvData.size(); i++) {
	        	String csvLine [] = new String[csvData.get(0).length + 1];
	        	int id = i + 1;
	        	csvLine[0] = Integer.toString(id);
	        	for (int j = 1; j < csvLine.length; j++)
	        	{
	        		csvLine[j] = csvData.get(i)[j-1];
	        		if (j == 5) 
	        			if (rand.nextInt(100) > 85) csvLine[j] = null;
	        		if (j == 6)
	        			if (rand.nextInt(100) > 90) csvLine[j] = null;
	        			
	        		
	        	}
	        	csvWriter.writeNext(csvLine);
	        }	        
	
	    }
    	
    	

    }
    
    
    
    

    
    public static ArrayList<String[]> generateCustomerData() {
    	
    	DataFactory df = new DataFactory();
    	
    	final int NUMBER_OF_COLUMNS = 8;
    	final int NUMBER_OF_CUSTOMERS = 250;
    	ArrayList<String[]> dataLines = new ArrayList<>();
    	String firstName, lastName, emailAddress, phoneNumber,
    		userName;
    	String password = "";
    	int isReviewer, isAdmin;
    	
    	Random rand = new Random();
    	
    	for (int i = 0; i < NUMBER_OF_CUSTOMERS; i ++)	{
    		
    		firstName = df.getItem(firstNames, 99, "Aphrodite");
    		lastName = df.getItem(lastNames, 99, "King");
    		
    		if (rand.nextInt(100) > 80)
    			emailAddress = firstName + "." + lastName;
    		else 
    			emailAddress = firstName.charAt(0) + lastName;
    		
    		if (rand.nextInt(100) > 25)
    			emailAddress += Integer.toString(rand.nextInt(100));
    		
    		emailAddress += "@" + emailHosts[rand.nextInt(emailHosts.length)] +
    				tlds[rand.nextInt(tlds.length)];
    		
    		
    		
    		phoneNumber = areaCodes[rand.nextInt(50)] + "-" + df.getNumberText(3) +
    				"-" + df.getNumberText(4);
    		
    		userName = firstName.substring(0,1) + lastName + df.getNumberText(2);
    		
    		isReviewer = rand.nextInt(2);
    		isAdmin = rand.nextInt(2);
    		
    		
    	
    		char [] tempPassword = df.getRandomText(5,9).toCharArray();    		
    		
    		
    		
    		int tempUppercaseIndex = rand.nextInt(tempPassword.length);
    		
    		
    		
    		int specialCharIndex = rand.nextInt(tempPassword.length);
    		char specialChar = specialCharacters[rand.nextInt(specialCharacters.length)];
    		String uppercaseChar = Character.toString(tempPassword[tempUppercaseIndex]).toUpperCase();
    		for (int j = 0; j < tempPassword.length; j++)
    		{
    			if (j == tempUppercaseIndex) password += uppercaseChar;
    			else password += Character.toString(tempPassword[j]);
    			
    			if (j == specialCharIndex) password += Character.toString(specialChar);
    		}
    		
    		password += df.getNumberText(2);
    		
    		
    		
    		dataLines.add(new String[] {userName, password, firstName, lastName, phoneNumber, emailAddress, 
    				String.valueOf(isReviewer), String.valueOf(isAdmin)});
    		
    		password = "";
    	}
    	
    	for (int i = 0; i < 250; i++) {
    		for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
    			System.out.print(dataLines.get(i)[j]);
    			if (j == NUMBER_OF_COLUMNS - 1) System.out.print("\n");
    			else System.out.print(" ");
    			
    		}
    	}
    	
    	return dataLines;
    }
}

