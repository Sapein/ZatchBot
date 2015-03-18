package modules;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ZatchBotDateAndTime {
	/*
	 * This simply allows us to get the date
	 */
	public String getDate(){
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy"); //Sets the date format To Month-Day-Year
		Date date = new Date(); //stores the date
		String yourDate = dateFormat.format(date); //turns the date into a variable to be called later
		return yourDate;
	}
	
	/*
	 * This simply allows us to get the time
	 */
	public String getTime(){
		DateFormat dF = new SimpleDateFormat("HH:mm"); //Sets the Time format to Hour:Minute
		Date time = new Date(); //stores the time
		String yourTime = dF.format(time); //turns the time into a variable to be called later
		return yourTime;
	}
}
