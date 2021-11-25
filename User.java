package oopProject;

import java.io.*;
import java.util.*;

public class User {
	protected int decider;//to decide whether user is manager or customer
	protected String name;
	protected String userName;
	protected String password;
	protected int Balance;//Initial balance is Rs 2000 
	public User(String name, String userName, String password,int Balance) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.Balance = Balance;
	}
	void showBalance(){
		System.out.println("Your available Balance is: "+Balance);
	}
	void UpdateBalance(int amt)
	{
		this.Balance+=amt;
	}
	void UpdateBalance(int b,int c) {
		this.Balance-=b*c;
	}
	void UpdateBalance(int a,int b,int c)
	{
		this.Balance+=(b-a)*c;
	}
	void search(int StartingDate,int NumberofNights){
		ArrayList<Property> possible=new ArrayList <Property>();
		Scanner s = new Scanner(System.in);
		for(Property property:Control.AllProperties)
		{
			boolean temp=true;
			for(int i=StartingDate;i<StartingDate+NumberofNights;i++)
			{
				if(property.availableDates.available_dates.contains(i)==false)
				{
					temp=false;
					break;
				}
			}
			if(temp)
			{	
				possible.add(property);
				System.out.println(property.getName()+" "+property.getAddress()+" "+property.getPrice_per_night());
			}	
		}
		if(possible.size()==0)
		{
			System.out.println("Sorry no properties found :("+'\n'+"Enter 1 to do a Re-Search or 2 to return back to main menu");
			int x=s.nextInt();
			if(x==1)
			{
				System.out.println("Enter Starting Date ");
				int x1=s.nextInt();
				System.out.println("Enter Number of Nights");
				int x2=s.nextInt();
				search(x1,x2);
			}
			else if(x==2)
				return;
			else
				return;
		}
		
		else {
		System.out.println("Search completed!");
		System.out.println("Enter 1 to Re-Search, 2 to return back to main menu and 3 to continue");
		int x=s.nextInt();
		if(x==1)
		{
			System.out.println("Enter Starting Date ");
			int x1=s.nextInt();
			System.out.println("Enter Number of Nights");
			int x2=s.nextInt();
			search(x1,x2);
		}
		else if(x==2)
			return;
		else if(x==3)
		{
		System.out.println("Enter name of property which you want to book");
		String buff=s.nextLine();
		String to_book = s.nextLine();
		System.out.println("Confirm Booking for "+to_book+" for "+StartingDate +"->"+(StartingDate+NumberofNights)+" y/n");
		if(s.next().equals("y"))
		{
			book(to_book,StartingDate,NumberofNights);
		}
		else
		{
			return;
		}
	}
		}
	}
	void book(String to_book,int StartingDate,int NumberofNights ) {
		boolean successful=false;
		for(Property p:Control.AllProperties) {
			if(p.name.equalsIgnoreCase(to_book))
			{
				if(Balance>=p.price_per_night*(NumberofNights)) {
					{
						UpdateBalance(p.price_per_night,NumberofNights);
						for(User u:Control.UserInfo)
						{
							if(u.userName.equals(p.username))
							{
								u.UpdateBalance(NumberofNights*p.price_per_night);
							}
						}
					}
				for(int i=StartingDate;i<StartingDate+NumberofNights;i++)
				{
					p.availableDates.available_dates.remove(Integer.valueOf(i));
				}
				System.out.println("Booking Successful!");
				successful=true;
				booking e=new booking(this.userName,p.name,p.address,StartingDate,NumberofNights);
				Control.AllBookings.add(e);
				Control.addToFile(e,true);
				showBalance();
				break;
				}
				else
				{
					System.out.println("Sorry Insufficient Balance");
					showBalance();
					//goto
				}
			}
		}
		if(!successful)
		{
			System.out.println("Not found Try Again");
		}
	}
	void CancelBooking(String to_book,int StartingDate,int NumberofNights){
		
		for(Property p:Control.AllProperties) {
			if(p.name.equalsIgnoreCase(to_book))
			{
				for(User u:Control.UserInfo)
				{
					if(u.userName.equals(p.username))
					{
						u.UpdateBalance(NumberofNights*(100-p.price_per_night));
					}
				}
				UpdateBalance(100,p.price_per_night,NumberofNights);
				for(int i=StartingDate;i<StartingDate+NumberofNights;i++)
				{
					p.availableDates.available_dates.add(i);
				}
				
				System.out.println("Cancellation successful");
				
				showBalance();
				}
			}
		}
	@Override
	public String toString() {
		return "User [name=" + name + ", userName=" + userName + ", password=" + password + ", Balance=" + Balance
				+ "]";
	}
}
class Customer extends User{
	public Customer(String name, String userName, String password, int Balance) {
		super(name, userName, password, Balance);
		// TODO Auto-generated constructor stub
		decider=0;
	}
	
}
class Manager extends User{

	public Manager(String name, String userName, String password, int Balance) {
		super(name, userName, password, Balance);
		// TODO Auto-generated constructor stub
		decider=1;
	}
	public void AddProperty(Property p) {
		Control.AllProperties.add(p);
	}
}
