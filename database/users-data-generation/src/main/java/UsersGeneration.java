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

//import static org.junit.Assert.assertTrue;


public class UsersGeneration 
{
	
	// These characters will be put into generated passwords
	private static char [] specialCharacters = {'!', '@', '#', '$', '%', '^', '*', '(', ')'};
	
	// These numbers will be the first three numbers of all the phone numbers
	private static String[] areaCodes = {"205", "907", "480", "479", "209", "303", 
			"203", "302", "239", "229", "808", "208", "217", "219", "319",
		"316", "859", "225", "207", "240", "339", "231", "218", "228", "314",
		"406", "308", "702", "603", "201", "505", "212", "252", "701", "513",
		"405", "458", "215", "401", "803", "605", "423", "210","385", "802",
		"276", "206", "304", "262", "307"};
	
	// State abbreviations for user locations, selected randomly for each user
	private static String[] stateAbvs = {"AL", "AK", "AZ", "AR", "CA", "CO", 
			"CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA",
		"KS", "859", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO",
		"MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "513",
		"OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX","UT", "VT",
		"VA", "WA", "WV", "WI", "WY"};
	
	// These are the email hosts for generated user email addresses.
	private static String[] emailHosts = { "gmail", "hotmail", "yahoo",
		"somemail", "everymail", "mailbox", "bizmail", "mail2u" };
	
	// Top level domains, this is the last part of the generated email addresses
	private static String[] tlds = { ".org", ".net", ".com", ".biz", ".us", "co.uk" };

	
	// The city where the user is located, assigned randomly
	private static String city;
	// The state where the user is located, assigned randomly
	private static String state;    	
	
	
	// A name from this list is randomly given to each user.
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
		"Donna", "Tina", "Kathy", "Valerie", "Jewel", "Katrina",
		"Liam", "Noah", "Oliver", "Elijah", "Isaiah", "William", "Lucas",
		"Leo", "Erik", "Matthew", "Steven", "Dale", "Henry", "Toki",
		"Theodore", "Jack", "Levi", "Alexander", "Jackson", "Daniel"};
		
	// A last name from this list is  randomly  given to each user
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
	
	// This is the path and file name where the generated user data is stored
	private static final String STRING_ARRAY_SAMPLE = "./prs-database-users.csv";
	
    public static void main( String[] args ) throws IOException
    {	    	
    	
    	Thread.dumpStack();
    	
    	// This runs the method that generates the user data, storing it in a 
    	// list of string arrays.
    	List<String[]> csvData = generateUserData();
    	
    	 
    	// The writer is created with the properties appropriate for creating a 
    	// csv file.
    	try (
	        Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));
	
	        CSVWriter csvWriter = new CSVWriter(writer,
            CSVWriter.DEFAULT_SEPARATOR,
            CSVWriter.NO_QUOTE_CHARACTER,
            CSVWriter.DEFAULT_ESCAPE_CHARACTER,
            CSVWriter.DEFAULT_LINE_END);
	    ) {
    		 
    		// The first string array has to be the names of the columns
    		String[] headerRecord = {"ID", "Username", "Password", "FirstName",
    				"LastName", "PhoneNumber", "Email","IsReviewer", "IsAdmin"};
	        
	        csvWriter.writeNext(headerRecord);
	        
	        Random rand = new Random();
	
	        // This loop will run until every array in the list csvData has been written
	        // to the file
	        for (int i = 0; i < csvData.size(); i++) {
	        	// Every line of the csv file is made of an array from csvData.
	        	// + 1 here because the id column is not included in the generated data
	        	String csvLine [] = new String[csvData.get(0).length + 1];
	        	int id = i + 1;
	        	// The id column value is assigned here. It starts from 1 instead of 0.
	        	csvLine[0] = Integer.toString(id);
	        	// This loop goes through the line of data currently being added to the file.
	        	// It starts at 1 because 0 is for the id column
	        	for (int j = 1; j < csvLine.length; j++)
	        	{
	        		csvLine[j] = csvData.get(i)[j-1];
	        		// Here, I am giving null values to random rows of the user data
	        		if (j == 5) 
	        			if (rand.nextInt(100) > 85) csvLine[j] = null;
	        		if (j == 6)
	        			if (rand.nextInt(100) > 90) csvLine[j] = null;
	        			
	        		
	        	}
	        	// with the row of the csv file built, it is added to the file
	        	csvWriter.writeNext(csvLine);
	        }	        
	
	    }    	

    }  

    
    public static ArrayList<String[]> generateUserData() {
    	
    	// initializes the tool that helps me create the random user data
    	DataFactory df = new DataFactory();
    	
    	// There will be 8 columns of generated data
    	final int NUMBER_OF_COLUMNS = 8;
    	// I want data for 250 users
    	final int NUMBER_OF_USERS = 250;
    	// A dataline is all info on a user, and datalines are stored as 
    	// an array of strings in an array list.
    	ArrayList<String[]> dataLines = new ArrayList<>();
    	
    	String firstName, lastName, emailAddress, phoneNumber,
    		userName;
    	String password = "";
    	
    	int isReviewer, isAdmin;
    	
    	Random rand = new Random();
    	
    	// all data is assigned to a user in this loop
    	for (int i = 0; i < NUMBER_OF_USERS; i ++)	{
    		
    		// 1% chance that first name is benjamin, otherwise
    		// datafactory chooses an item from first names list for user first name.
    		firstName = df.getItem(firstNames, 99, "Benjamin");    		
    		lastName = df.getItem(lastNames, 99, "King");
    		
    		// Some last names have spaces in them, and they need to be removed when making the email address
    		// First, turn the last name into a char array
    		char [] noSpacesLastName = lastName.toCharArray();
    		String emailLastName = "";
    		
    		for (int j = 0; j < lastName.length(); j++) {
    			if (noSpacesLastName[j] != ' ')
    				emailLastName += noSpacesLastName[j];
    		}
    		
    		// ways to randomly create email address formats
    		if (rand.nextInt(100) > 80)
    			emailAddress = firstName + "." + emailLastName;
    		else 
    			emailAddress = firstName.charAt(0) + emailLastName;
    		
    		// randomly assigns random numbers, or doesn't.
    		if (rand.nextInt(100) > 25)
    			emailAddress += Integer.toString(rand.nextInt(100));
    		
    		// add random host and top domain service to email address
    		emailAddress += "@" + emailHosts[rand.nextInt(emailHosts.length)] +
    				tlds[rand.nextInt(tlds.length)];
    		
    		// phone number is made of a random area code, and then 7 random numbers    		
    		phoneNumber = areaCodes[rand.nextInt(50)] + "-" + df.getNumberText(3) +
    				"-" + df.getNumberText(4);
    		
    		// user name is the first letter of first name, plus last name, plus 2 random numbers
    		userName = firstName.substring(0,1) + lastName + df.getNumberText(2);
    		
    		// randomly determine these. not bool because sql does bits.
    		isReviewer = rand.nextInt(2);
    		isAdmin = rand.nextInt(2);
    		
    		
    	
    		// every password is made of a random word between 5 and 9 characters long.
    		// a random letter of this word is capitalized.
    		// a random special character is added to a random place in the word.
    		// 2 random numbers are added to the end of the password
    		
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
    		
    		
    		// every random value just generated is added to a string array, this is a row of the users table   		
    		dataLines.add(new String[] {userName, password, firstName, lastName, phoneNumber, emailAddress, 
    				String.valueOf(isReviewer), String.valueOf(isAdmin)});
    		
    		// reset password before continuing
    		password = "";
    	}
    	
    	// This just prints everything for debug purposes
    	for (int i = 0; i < NUMBER_OF_USERS; i++) {
    		for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
    			System.out.print(dataLines.get(i)[j]);
    			if (j == NUMBER_OF_COLUMNS - 1) System.out.print("\n");
    			else System.out.print(" ");    			
    		}
    	}
    	
    	return dataLines;
    }
}

