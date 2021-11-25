package oopProject;
import java.util.*;

public class Property {
	public String username;//to store username of manager who added the property
	public String name;
	public String address;
	public int price_per_night;
	public Calender availableDates;
	public Property(String username,String name, String address, int price_per_night, ArrayList<Integer>Availability) {
		this.username=username;
		this.name = name;
		this.address = address;
		this.setPrice_per_night(price_per_night);
		availableDates=new Calender(Availability);
	}
	@Override
	public String toString() {
		return "Property [name=" + name + ", address=" + address + ", price_per_night=" + price_per_night
				+ ", availableDates=" + availableDates + "]";
	}
	public String getName() {
		return "Name: "+name;
	}
	public String getAddress() {
		return "Address: "+address;
	}
	public String getPrice_per_night() {
		return "Price(per night): "+price_per_night;
	}
	public void setPrice_per_night(int price_per_night) {
		this.price_per_night = price_per_night;
	}
}
class booking{
	String username;
	String name;
	String address;
	int Starting_Date;
	int Number_of_nights;
	public booking(String username, String name, String address, int starting_Date, int number_of_nights) {
		super();
		this.username = username;
		this.name = name;
		this.address = address;
		Starting_Date = starting_Date;
		Number_of_nights = number_of_nights;
	}
}
