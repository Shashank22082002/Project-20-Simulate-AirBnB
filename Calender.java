package oopProject;

import java.util.ArrayList;

public class Calender {
	ArrayList<Integer>available_dates=new ArrayList<Integer>();
	public Calender(ArrayList<Integer>available_dates)
	{
		this.available_dates=available_dates;
	}
	public boolean book(int Starting_date,int Number_of_Nights) {
		for(int i=Starting_date;i<Number_of_Nights;i++)
		{
			if(!available_dates.contains(i))
			return false;
		}
		for(int i=Starting_date;i<Number_of_Nights;i++)
		{
			available_dates.remove(i);
		}
		return true;
	}
	public void cancel(ArrayList<Integer>cancel_dates)
	{
		for(int date:cancel_dates)
		{
				available_dates.add(date);
		}
	}
}
