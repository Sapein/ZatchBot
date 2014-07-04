//Created by Sapein.
//Zatchbot v 2.0 BETA
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.jibble.pircbot.*;

public class ZatchBot extends PircBot
{
	static String Master = ""; 
	static String BotNick = "";
	static String OpNick = ""; 
	static String OpHostname = "";
	boolean OpNickUsed;
	boolean OpHostnameUsed;
    String[] OpNicks = OpNick.split(",");
    String[] OpHostnames = OpHostname.split(",");
	ArrayList<String> hostnames = null;
	
	public ZatchBot() throws Exception{
			
		BufferedReader saveFile;
		saveFile = new BufferedReader(new FileReader("Config.txt"));
		saveFile.readLine(); //1st line 
		saveFile.readLine(); //2nd 
		saveFile.readLine(); //3rd 
	    saveFile.readLine(); //4th
	    saveFile.readLine(); //5th
	    saveFile.readLine(); //6th 
	    saveFile.readLine(); //7th 
	    OpNickUsed = Boolean.parseBoolean(saveFile.readLine()); //8th 
	    saveFile.readLine(); //9th 
	    OpHostnameUsed = Boolean.parseBoolean(saveFile.readLine()); //10th 
	    saveFile.readLine(); //11th
	    BotNick = saveFile.readLine(); //12th
	    saveFile.readLine(); //13th 
	    Master = saveFile.readLine(); //14th
	    saveFile.readLine(); //15th
	    OpNick = saveFile.readLine(); //16th
	    saveFile.readLine(); //17th 
	    OpHostname = saveFile.readLine(); //18h 
	    saveFile.close();
	    
	    OpNicks = OpNick.split(",");
	    OpHostnames = OpHostname.split(",");
	    
	    this.setName(BotNick);
		this.setLogin("Zatch");
		
	}
	protected void onJoin(String channel, String sender, String login, String hostname){
		//Auto-op
		for(int OpNumber = 0; OpNumber < OpNicks.length; ++OpNumber) { //This makes sure that all Channels listed are joined as we stored it in a variable earlier.
			
			if(OpHostnameUsed == false && (sender.equalsIgnoreCase(Master) || sender.equalsIgnoreCase(OpNicks[OpNumber]))){ //Actually Joins the channels. 
				op(channel, sender);
			}
			else if(OpHostnameUsed == true && (sender.equalsIgnoreCase(Master) || sender.equalsIgnoreCase(OpNicks[OpNumber]))){
				for(int opHostnameNumber = 0; opHostnameNumber < OpHostnames.length; ++opHostnameNumber){
					if(sender.equalsIgnoreCase(Master) || hostname.equals(OpHostnames[opHostnameNumber])){
						op(channel, sender);
					}
				}
			}
	  	}
		
		String Hello = "Hello ";
		if (sender.equals(BotNick)){
			sender = "";
			Hello = "";
			sendMessage(channel, sender + Hello);
		}
		else if(sender.equalsIgnoreCase(Master)){
			sendMessage(channel, "MASTER!");
		}
		else{
		sendMessage(channel, Hello + sender);
		}
	}
	protected void onPart(String channel, String sender, String login, String hostname){
			sendMessage(channel, "Good-bye.");
	}
	protected void onPrivateMessage(String sender, String login, String hostname, String message){
		sendMessage(Master, sender + ": " + message); 
	}
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{	
		//Begin Variables
		//Begin Time and Date Variables
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy"); //Sets the date format To Month-Day-Year
		Date date = new Date(); //stores the date
		String yourDate = dateFormat.format(date); //turns the date into a variable to be called later
		DateFormat dF = new SimpleDateFormat("HH:mm"); //Sets the Time format to Hour:Minute
		Date time = new Date(); //stores the time
		String yourTime = dF.format(time); //turns the time into a variable to be called later
		//End Time and Date Variables
		
		//End Variables
		
		//Begin Help Code
		//Help code stays on top
		
		if (message.equalsIgnoreCase("&Help")){
			if (sender.equals(Master)){
				channel = sender;
				sendMessage(channel, "Hello, Master. Need a refresher?");
				sendMessage(channel, "Well here are my commands:");
				sendMessage(channel, "&join channel");
				sendMessage(channel, "&leave channel");;
				sendMessage(channel, "Hello Zatch");
				sendMessage(channel, "Goodnight.");
				sendMessage(channel, "&pie");
				sendMessage(channel, "&quit");
				sendMessage(channel, "Goodbye");
				sendMessage(channel, "&fishslap user");
				sendMessage(channel, "&fish");
				sendMessage(channel, "&code");
				sendMessage(channel, "&date");
				sendMessage(channel, "&time");
				sendMessage(channel, "&x-chan");
				sendMessage(channel, "&Op");
			}
			else{
				sendMessage(sender, "I am Zatch! A bot created in Java (using Pircbot"
						+ " as a base.) My Master is" + Master + ". I am still in testing and "
						+ "development.");
				sendMessage(sender, "Here are my Commands:");
				sendMessage(sender, "Hello Zatch");
				sendMessage(sender, "Goodnight");
				sendMessage(sender, "I hate you Zatch");
				sendMessage(sender, "&pie");
				sendMessage(sender, "Goodbye");
				sendMessage(sender, "&fishslap user");
				sendMessage(sender, "&fish");
				sendMessage(sender, "&code");
				sendMessage(sender, "&date");
				sendMessage(sender, "&time");
				sendMessage(sender, "&x-chan");
			}
		}
		//end Help Code
		
		//Begin Special Commands
		
		//Begin Channel and Server Movement Commands
		//Leave Command
		if (sender.equals(Master)){//Checks to see if the sender is Master
			Pattern leaveP = Pattern.compile("^\\&leave"); //Sets the command to &leave
			Matcher leaveM = leaveP.matcher(message); //checks the entirety of the message 
			if (leaveM.find()){ //checks the leaveM variable 
				String chan = new String(""); //Steps up the Chan variable
				if(message.length()>7) { //cuts the of the command out
					chan = message.substring(7); //stores the channel in the variable
				}
				partChannel("#" + chan); //causes the channel to be left. 
			}
		}
		
		//Join Command
		if (sender.equals(Master)){
			Pattern joinP = Pattern.compile("^\\&join");
			Matcher joinM = joinP.matcher(message);
			if (joinM.find()) {
				String chanl = new String("");
				if (message.length()>6) {
					chanl = message.substring(6);
				} 
				joinChannel("#" + chanl);
			}
		}
		//Op Command
		if(sender.equals(Master)){
			if (message.equals("&op")){
				op(channel, Master);
			}
		}		
		//quit command
		if(sender.equals(Master)){
			if(message.equals("&quit")){
				quitServer();
			}
			//Cross Channel Communication Code 
			Pattern crossP = Pattern.compile("^\\&x-chan");
			Matcher crossM = crossP.matcher(message);
				if(crossM.find()){
					String chanl = new String("");
					String ms = new String("");
					if(message.length()>5) {
						ArrayList<String> list = new ArrayList<String>(Arrays.asList(message.split(" ")));
						list.remove(0);
						chanl = list.remove(0);
						for (String s : list)
						{
							ms += s + " ";
						}
					}
					sendMessage(chanl, channel + " - " + sender + ": " + ms);
			}
			//End of Cross Channel Communication Code

				
		//End Channel and Server Movement and CommunicationCommands
		
		//Begin Trigger Statements
				
		//Goodnight
		Pattern goodN = Pattern.compile("^Goodnight");
		Matcher goodM = goodN.matcher(message);
		if(goodM.find()){
			String chanl = "";
			if(message.length()>6){
				chanl = "";
			}
			if(sender.equalsIgnoreCase(Master)){
				sendMessage(channel, "Goodnight Father ");
			}
			else{
				sendMessage(channel, "Goodnight " + sender + chanl);
			}
		}
		//End of Goodnight Code
		
		//Hello Code
		if(message.equals("Hello Zatch")){
			sendMessage(channel,"Hello, " + sender);
		}
		//End of Hello Code
		
		//Goodbye Code
		Pattern byeC = Pattern.compile("^Goodbye");
		Matcher byeM = byeC.matcher(message);
			if(byeM.find()){
				if(sender.equalsIgnoreCase(Master)){
					sendMessage(channel, "Goodbye Father,");
				}
							
				else{
					sendMessage(channel, "Goodbye " + sender);
				}
			}
		//End of Goodbye Code
			
		//"I have you" code
		Pattern hateY = Pattern.compile("^I hate you Zatch");
		Matcher hateM = hateY.matcher(message);
		if(hateM.find()){
			String chanl = "";
			if(message.length()>6){
				chanl = "";
				}
			sendMessage(channel, "I hate you too, bitch." + chanl);
			}
		}
		//End of "I Hate you Code"	
		
		//End Trigger Statements
		
		//Begin Commands About Zatch
		//auto-log code ends
		if(message.equals("&code")){
			sendMessage(channel, "https://github.com/Sapein/ZatchBot");
		}
		//Random Response Code
		//End Commands About Zatch 
		
		//Begin Fun IRC commands
		if (message.equals("&pie")){
			sendAction(channel, "Throws pie in " + sender + "'s " + "face.");
		}
		
		Pattern fishP = Pattern.compile("^\\&fishslap");
		Matcher fishM = fishP.matcher(message);
		if(fishM.find()){
			String fslap = new String("");
			if(message.length()>10){
				fslap = message.substring(10);
			}
			sendAction(channel, "must slap " +fslap+ " with a trout, because " +sender+ " wills it.");
            sendAction(channel, "slaps " +fslap+ " with a trout.");
            sendAction(channel, "laughs evily");
		}
		
		if(message.equals("&fish")){
			sendMessage(channel, "FISH");
		}
		
		//Current Time Command
		if(message.equals("&time")){
			sendMessage(channel, "It is " + yourTime + " CST");
		}
		//Current Date Command
		if(message.equals("&date")){
			sendMessage(channel, "It is " + yourDate + " in the CST Timezone");
		}
		//End Fun IRC Commands
		
		//Begin Proccess that Zatch Does Automatically
		//auto-log code begin
		Pattern change2 = Pattern.compile("^");
		Matcher change1 = change2.matcher(message);
		if (change1.find()){
			String chanl1 = new String("");
			if (message.length()>0) {
			    chanl1 = message.substring(0);
			}
			try {
	
	 
				File file = new File("C:/Documents and Settings/bob/Desktop/logs/" + channel + " " + yourDate + " " + "log.txt");
				
				if (!file.exists()) {
					file.createNewFile();
				}
	
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(channel +" " + " " + yourTime + " " + sender + ":" + chanl1 + "\r\n");
				bw.close();
	 
			} catch (IOException e) {
				e.printStackTrace();
		}
		}
		//auto-log code ends

		//End Processes that Zatch Does Automatically
	}
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason){
	if(recipientNick.equalsIgnoreCase(getNick())) { //If it gets kicked
	    joinChannel(channel); //If the bot is kicked it will rejoin the channel immediately
	}
	}
	protected void onDisconnect(){ 
		while (!isConnected()) { //if it looses connection to the server
		    try {
		        reconnect(); //it will try to reconnect
		    }
		    catch (Exception e) {
		    }
		}
	}
}



