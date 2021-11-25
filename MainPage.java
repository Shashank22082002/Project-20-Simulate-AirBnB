package oopProject;

import java.io.*;
import java.util.*;

class Control{
	public  Control(){
		Scanner sc = null;
		try {
			sc=new Scanner(new File("UserData.txt"));
			 // Check if there is another line of input
			while(sc.hasNextLine())
			{
				String str = sc.nextLine();
				// parse each line using delimiter
				parseData(str);
			}
		}
		catch(IOException exp)
		{
			// TODO Auto-generated catch block
		      exp.printStackTrace();
			System.out.println("Cannot Write "+exp );
		}
		finally {
			if(sc!=null)
				sc.close();
		}
		Scanner sc1 = null;
		try {
			sc1=new Scanner(new File("Allproperties.txt"));
			 // Check if there is another line of input
			while(sc1.hasNextLine())
			{
				String str1 = sc1.nextLine();
				// parse each line using delimiter
				parseData1(str1);
			}
		}
		catch(IOException exp)
		{
			// TODO Auto-generated catch block
		      exp.printStackTrace();
			System.out.println("Cannot Write "+exp );
		}
		finally {
			if(sc1!=null)
				sc1.close();
		}
		Scanner sc2=null;
		try {
			sc2=new Scanner(new File("bookings.txt"));
			while(sc2.hasNextLine()) {
				String str = sc2.nextLine();
			parseData2(str);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
}
	public static User u;
		private static void parseData(String str){	
	    String name,username,password;int balance,b;
	    Scanner lineScanner = new Scanner(str);
	    lineScanner.useDelimiter(",");
	    while(lineScanner.hasNext()){
	    	b = lineScanner.nextInt();
	      name = lineScanner.next();
	      username = lineScanner.next();
	      password = lineScanner.next();
	      balance = lineScanner.nextInt();
	      if(b==0)
	    	  u=new Customer(name,username,password,balance);
	      else if(b==1)
	    	  u=new Manager(name,username,password,balance);
		UserInfo.add(u);
		UserCredentials.put(username, password);
	    }
	    lineScanner.close();
	  }
		private static void parseData1(String str)
		{
			String name,address,username; int price; int n; ArrayList<Integer> available_dates;
			Scanner lineScanner = new Scanner(str);
		    lineScanner.useDelimiter(",");
		    while(lineScanner.hasNext()) {
		    	username=lineScanner.next();
		    	name=lineScanner.next();
		    	address=lineScanner.next();
		    	price=lineScanner.nextInt();
		    	n=lineScanner.nextInt();
		    	available_dates=new ArrayList<Integer>();
		    	for(int i=0;i<n;i++)
		    	available_dates.add(lineScanner.nextInt());
		    	AllProperties.add(new Property(username,name,address,price,available_dates));
		    }
		    lineScanner.close();
		}
		private static void parseData2(String str)
		{
			String username,name,address; int starting_date,number_of_nights;
			Scanner lineScanner = new Scanner(str);
		    lineScanner.useDelimiter(",");
		    while(lineScanner.hasNext()) {
		    	username=lineScanner.next();
		    	name=lineScanner.next();
		    	address=lineScanner.next();
		    	starting_date=lineScanner.nextInt();
		    	number_of_nights=lineScanner.nextInt();
		    	AllBookings.add(new booking(username,name,address,starting_date,number_of_nights));
		    }
		    lineScanner.close();
		}
	private String GreetingMessage="Welcome to our portal";

	public void DisplayWindow() {
		System.out.println(GreetingMessage);
		System.out.println("Enter 0 for login or 1 for SignUp");
	}
	
	public void setGreetingMessage(String greetingMessage) {
		GreetingMessage = greetingMessage;
	}
	
	public static HashMap <String,String> UserCredentials = new HashMap<String,String>();
	
	public void SignUp()
	{
		System.out.println("Are you a customer or a manager?"+'\n'+"Enter 0 if customer and 1 if a manager");
		Scanner sc=new Scanner(System.in);
		int b=sc.nextInt();//exception handling to be done
		System.out.println("Enter your Name");
		String buff=sc.nextLine();
		String Name=sc.nextLine();
		System.out.println("Hello "+Name+", "+GreetingMessage);
		System.out.println("Enter your UserName");
		String UserName=sc.next();
		System.out.println("Enter your Password");
		String Password=sc.next();
		int Balance=2000;
		if(UserCredentials.containsKey(UserName)) {
			System.out.println("Sorry, This UserName already exists");
			SignUp();
		}
		else
		{
			if(b==0)
				SignUp(new Customer(Name,UserName,Password,Balance));
			else
				SignUp(new Manager(Name,UserName,Password,Balance));
		}
		sc.close();
	}
	public void SignUp(User u)
	{
		addToFile(u,true);
		UserInfo.add(u);
		UserCredentials.put(u.userName,u.password);
		System.out.println("HeLLo NeW UseR :)");
		System.out.println("Enter Your Credentials for Secured Login");
		Login();
	}
	static int k=1;//temporary variable to keep track of number of attempts made to login
	public void Login() {
		k++;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your UserName");
		
		String UserName=sc.next();
		System.out.println("Enter your Password");
		String Password=sc.next();
		boolean y=VerifyLogin(UserName,Password);
		if(y==false&&k<=3)
			Login();
		else if(k>3)
		{
			Logout();
		}
		else
		{
			System.out.println("Login Successful :)");
			for(User u1:UserInfo)
			{
				if(u1.userName.equals(UserName))
					u=u1;
			}
			System.out.println("		**Our Motto  --	Providing best services**");
			Scanner s = new Scanner(System.in);
			//int x=s.nextInt();
//			if(u.decider==0)
//			{
				int x=7;
				while(x!=5) {
				System.out.println("Enter"+'\n'+"0 for search/Book Properties"+'\n'+"1 to cancel any Past booking"+'\n'+"2 to see your current bookings"+'\n'+"3 to check Balance"+'\n'+"4 to add Balance"+'\n'+"5 to Logout");
				if(u.decider==1)System.out.println("6 to add new Property");
				x=s.nextInt();
				if(x==0)
				{
					System.out.println("Enter Starting Date ");
					int StartingDate=s.nextInt();
					System.out.println("Enter Number of Nights");
					int NumberofNights=s.nextInt();
					u.search(StartingDate,NumberofNights);
				}
				else if(x==1)
				{
					String to_book; int StartingDate,NumberofNights;
					System.out.println("Enter property Name");
					String buff=s.nextLine();
					to_book=s.nextLine();
					
					System.out.println("Enter StartingDate");
					StartingDate=s.nextInt();
					System.out.println("Enter Number of Nights");
					NumberofNights=s.nextInt();
					boolean found=false;
					for(booking b:AllBookings)
					{
						if(b.username.equals(u.userName)&&b.name.equalsIgnoreCase(to_book)&&(b.Number_of_nights==NumberofNights)&&(b.Starting_Date==StartingDate))
								{
							found=true;
							u.CancelBooking(to_book, StartingDate, NumberofNights);
							AllBookings.remove(b);
							break;
								}
					}
					if(!found)
						System.out.println("Booking Not found");
					
				}
				else if(x==2)
				{
					ArrayList<booking>temp=new ArrayList<booking>();
					for(booking a:AllBookings)
					{
						if(a.username.equals(u.userName))
						{
							temp.add(a);
							System.out.println("Name: "+a.name+" Address: "+a.address+" Interval: "+a.Starting_Date+"->"+(a.Starting_Date+a.Number_of_nights));
						}
					}
					if(temp.isEmpty())
						System.out.println("No current bookings");
				}
				else if(x==3)
				{
					u.showBalance();
				}
				else if(x==4)
				{	
					System.out.println("Enter amount");
					int amt=sc.nextInt();
					u.UpdateBalance(amt);
					u.showBalance();
				}
				else if(x==5)
				{
					Logout();
				}
				else if(x==6&&u.decider==1)
				{
					System.out.println("Do you want to add new Property?"+'\n'+"y/n");
					if(s.next().equals("y"))
					{
						System.out.println("Enter Name of Property");
						String buff=s.nextLine();
						String name=s.nextLine();
						System.out.println("Enter Address");
						String address=s.nextLine();
						System.out.println("Enter Price Per Night");
						int price=s.nextInt();
						s.nextLine();
						System.out.println("Enter Number of available Dates");
						int n=s.nextInt();
						System.out.println("Enter Dates(seperated by spaces only)");
						ArrayList<Integer>availability=new ArrayList<Integer>();
						for(int i=0;i<n;i++)
						{
							availability.add(s.nextInt());
						}
						Property p = new Property(u.userName,name,address,price,availability);
						AllProperties.add(p);
						System.out.println("Ok, Property added");
						addtofile(p,true);
					}
					else
					{
						Logout();
					}
				}
				else
				{
					System.out.println("Wrong input");
				}
			}
		}
	}
	public boolean VerifyLogin(String userName, String password)
	{
		if(UserCredentials.containsKey(userName)&&UserCredentials.get(userName).equals(password))
		return true;
		return false;
		
	}
	public void NeedHelp(){
		//Show list of places to visit
		//display contact Us
		//display AboutUs
	}
	public static ArrayList <User> UserInfo=new ArrayList<User>();
	public static ArrayList <booking> AllBookings = new ArrayList<booking>();
	private String AboutUs="We are oop learners";
	public String getAboutUs() {
		return AboutUs;
	}
	public void setAboutUs(String aboutUs) {
		AboutUs = aboutUs;
	}
	public static ArrayList<Property> AllProperties=new ArrayList<Property>();
	void addToFile(User u,boolean b){//to add userData to file
		BufferedWriter br=null;
		try {
		br=new BufferedWriter(new FileWriter("UserData.txt",b));
		br.write(u.decider+","+u.name+","+u.userName+","+u.password+","+u.Balance+'\n');
		br.close();
		}
		catch (Exception e) {
			System.out.println("Cannot write");
		}
	}
	void addtofile(Property p,boolean b) {//to add property data to file
		BufferedWriter br=null;
		String s="";
		try {
		br=new BufferedWriter(new FileWriter("Allproperties.txt",b));
		for(int i=0;i<p.availableDates.available_dates.size();i++)
			{
			s=s+","+p.availableDates.available_dates.get(i);
			}
		br.write(p.username+","+p.name+","+p.address+","+p.price_per_night+","+p.availableDates.available_dates.size()+s+'\n');
		br.close();
		}
		catch (Exception e) {
			System.out.println("Cannot write "+s);
		}
	}
	static void addToFile(booking b,boolean bb)//to add bookings to file
	{
		BufferedWriter br =null;
		try {
			br=new BufferedWriter(new FileWriter("bookings.txt",bb));
			br.write(b.username+","+b.name+","+b.address+","+b.Starting_Date+","+b.Number_of_nights+'\n');
			br.close();
		}
		catch(Exception e)
		{
			System.out.println("Can't write");
		}
	}
	public void Logout() {
		
		boolean check=false;
		for(Property p:AllProperties)
			{
				
				addtofile(p,check);
				if(!check)
					check=true;
			}
		check=false;
		for(User u:UserInfo)
			{
				addToFile(u,check);
				if(!check)
					check=true;
			}
		check=false;
		for(booking b:AllBookings)
		{
			addToFile(b,check);
			if(!check)
				check=true;
		}
		System.out.println("~~Thanks for visiting:)");
		System.exit(0);
	}
	
}
public class MainPage {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Control obj = new Control();
		Scanner sc=new Scanner(System.in);
		obj.DisplayWindow();
		int x=0;
		try {
		x=sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Wrong Input "+e);
		}
		if(x==0)
		{
			obj.Login();
		}
		else if(x==1)
		{
			obj.SignUp();
		}
		sc.close();
	}
}
