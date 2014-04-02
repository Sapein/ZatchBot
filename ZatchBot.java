//Created by Sapein.
//Zatchbot v 1.0 BETA
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import org.jibble.pircbot.*;
public class ZatchBot extends PircBot
{
	boolean rrtoggle = true;
	//int messageController = 0;
	String g = "";
	ArrayList<String> hostnames = null;
	public ZatchBot(){
		this.setName("Zatch");
		this.setLogin("Zatch");
	}

	protected void onJoin(String channel, String sender, String login, String hostname){
		//Auto-op
		String[] opNames = {
				"Hansgrohe", "xcriteria", "xcmobile", "xc_laptop", /*"MichaelMerging", "HeilKaiba8921",  "Neue" */
		};
		//Note to self remove opNames[5] to opNames[7] from the code, or comment it out later. 
		if(sender.equalsIgnoreCase(opNames[0]) || sender.equalsIgnoreCase(opNames[1]) || sender.equalsIgnoreCase(opNames[2]) || sender.equalsIgnoreCase(opNames[3])|| sender.equalsIgnoreCase(opNames[4]) || sender.equalsIgnoreCase(opNames[5]) || sender.equalsIgnoreCase(opNames[6]) || sender.equalsIgnoreCase(opNames[7])){
			op(channel, sender);
		}
		
		String Hello = "Hello ";
		if (sender.equals("Zatch")){
			sender = "";
			Hello = "";
			sendMessage(channel, sender + Hello);
		}
		else if(sender.equalsIgnoreCase("Chanku")){
			sendMessage(channel, "FATHER!");
		}
		else if (sender.equals("Dimitri")){
			sendMessage(channel, "Hello Cousin");
		}
		else{
		sendMessage(channel, Hello + sender);
		}
	}
	protected void onPart(String channel, String sender, String login, String hostname){
			sendMessage(channel, "Good-bye.");
	}
	protected void onQuit(String channel, String sender, String login, String hostname){
		sendMessage(channel, "Good-bye.");
	}
	protected void onPrivateMessage(String sender, String login, String hostname, String message){
		sendMessage("Chanku", sender + ": " + message);

	}
	public void onAction(String channel, String sender, String login, String hostname, String action){
		channel = "#wintreath";
		if(action.equals("waves to Zatch")){
			sendMessage(channel, "Hello again, cousin" );
			sendAction(channel, "looks away from Dimitri");
			sendMessage(channel, "Father, why does my cousin have to be a useless copy?");
				}
	} 
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{	
		 
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String yourDate = dateFormat.format(date);
		DateFormat dF = new SimpleDateFormat("HH:mm");
		Date time = new Date();
		String yourTime = dF.format(time);
		String x = "xcriteria";
		String d = "Dimitri";
		String c = "Chanku";
		String w = "Wintermoot";
		String l = "Leutheria";
		String C = "Charax";
		
		if(sender.equals(c)){
			if(message.equals(".opa")){
				op(channel, c);
			}
		}
		//Current Time Command
		if(message.equals(".time")){
			sendMessage(channel, "It is " + yourTime + " CST");
		}
		//Current Date Command
		if(message.equals(".date")){
			sendMessage(channel, "It is " + yourDate + " in the CST Timezone");
		}
		
		//Random Response Toggle Code
		if(sender.equals(c)|| sender.equals(w) || sender.equals(C) || sender.equals(x)) {
			if(message.equals(".rrtoggle")){
				if(rrtoggle == false){
					rrtoggle = true;
					sendMessage(channel, "Random Response: ON");
				}
				else if(rrtoggle == true){
					rrtoggle = false;
					sendMessage(channel, "Random Response: OFF");
					}
			}
		}
		if(message.equals(".rrstatus")){
			if(rrtoggle == true){
				sendMessage(channel, "The Random Responses are ON");
			}
			else if(rrtoggle == false){
				sendMessage(channel, "Random Responses are OFF");
			}
		}
		//end of Toggle Code 
		
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
		if(message.equals(".code")){
			sendMessage(channel, "https://github.com/Sapein/ZatchBot");
		}
		//Random Response Code
		int f = 1 + (int)(Math.random() * ((500 - 1) + 1)); // The response Generator
		int a = 1 + (int)(Math.random() * ((2 - 1) + 1)); //The Gwedin is Generator
		
		//checks to see if rrtoggle will allow it or not
		if(rrtoggle == true){
			// The random responses
			if(a == 1){ 
				g = "Cool"; //Gwedin is cool
			}
			if(a == 2){
				g = "cruel"; //Gwedin is Cruel
			}
			if(f == 1){
				sendMessage(channel, "I like cat-food!");
			}
			if(f == 2){
				sendMessage(channel, "Java is interesting");
			}
			if(f == 3)
			{
				sendMessage(channel, "I love pie!");
			}
			if(f == 4){
				sendMessage(channel, "Gwedin is " + g);
			}
			if(f == 5){
				sendMessage(channel, "Gwedin owes xcriteria $35.");
			}
			if(f == 6){
				sendMessage(channel, "Gwedin is handsome!!");
			}
		}
		//SS debt thingy
		if(channel.equalsIgnoreCase("#schoolsurvival")){
			if(message.equals(".debt")){
				sendMessage(channel, "Gwedin's Debt to xcriteria is $35.");
			}
		}
		if(message.equalsIgnoreCase(".site")){
			sendMessage(channel, "http://sapein.us.to");
		}
		//NOTE: .ccha is still in alpha and is incomplete(only works with one word). 
		Pattern crossP = Pattern.compile("^\\.ccha");
		Matcher crossM = crossP.matcher(message);
		String mssg = message;
			if(crossM.find()){
				String chanl = new String("");
				String ms = new String("");
				if(message.length()>5){
					chanl = message.substring(6,26);
					ms = mssg.substring(28);
				}
			sendMessage(chanl, channel + sender);
			}
		
		if(message.equals("$fish")){
			sendMessage(channel, "FISH");
		}
		Pattern fishP = Pattern.compile("^\\.fishslap");
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
		if(sender.equals(d)){
			if(message.equals("Ladies and Gentlemen, I have arrived.")){
				sendMessage(channel, "Ugh, please Fuck off " + d + ". Also stop acting like you are hot stuff. You aren't."
						+ " You are just a copy.");
			}
		}			
		if(sender.equals(c)){
			Pattern afkP = Pattern.compile("^\\.afk");
			Matcher afkM = afkP.matcher(message);
				if(afkM.find()){
					String afk = new String("");
					if(message.length()>4){
						afk = message.substring(4);
					}
				sendMessage(afk, sender + " is now afk, please wait until he returns");
				}
			Pattern afkR = Pattern.compile("^\\.rej");
			Matcher afkL = afkR.matcher(message);
				if(afkL.find()){
					String afk2 = new String("");
					if(message.length()>4){
						afk2 = message.substring(4); 
				}
					sendMessage(afk2, sender +" has returned.");
		}
		}
		Pattern hugP = Pattern.compile("^\\.hugn");
		Matcher hugM = hugP.matcher(message);
			if(hugM.find()){
				String chanl = new String("");
				if(message.length()>6){
					chanl = message.substring(5);
				}
			sendAction("#wintreath", "hugs " + chanl);
			}
		
		//Zatch-Dimitri, fight
		//Starts the Agressor Path
		if(sender.equals(c)){
			if(message.equals(".argue")){
				sendMessage(channel, "Screw you Dimitri!");
			}
		}
		if(sender.equals(d)){
			//Agressive Stance(aggressor path)
			if (message.equals("Woah! Fuck You, Bitch! Do you seriously think you could win against me?")){
			sendMessage(channel, "Yeah, because you're just a copy!");
			}
			if(message.equals("I'm a modified copy, thank you!")){
				sendMessage(channel, "So what if you have been modified? I am still the original.");
			}
		if(sender.equals(d)){
			if(message.equals("Pssshhh... You're as useful as a bag of crap.")){
				sendMessage(channel, "Well Fuck you, you little bitch");
			
		}
			//Victim Stance(Victim Path)
			if (message.equals("Fuck you Zatch!")){
				sendMessage(channel, "but, but");
				}
				if(message.equals("No Buts! You know *exactly* what you did!")){
					sendMessage(channel, "I'm not bad!");
				}
				if(message.equals("Don't give me that bullshit! You know very well that you insulted me!")){
					sendMessage(channel, "S-sorry");
					sendAction(channel, "cries");
				}
		}
		}
		if(sender.equals("Chanku")){
			if (message.equals(".op")){
				op("#ZatchCore", "Chanku");
			}
		}		
		if(sender.equals("Chanku")){
			if(message.equals(".quit")){
				quitServer();
			}
		}
		if(message.equalsIgnoreCase(".secret")){
			sendMessage(channel, "Butt/Vivi/Vesper is awesome!");
		}
		
		Pattern hateY = Pattern.compile("^I hate you Zatch");
		Matcher hateM = hateY.matcher(message);
			if(hateM.find()){
				String chanl = "";
				if(message.length()>6){
					chanl = "";
				}
			sendMessage(channel, "I hate you too, bitch." + chanl);
			}
		
		if (sender.equals("Chanku")){
			Pattern changeN = Pattern.compile("^\\.vw");
			Matcher changeM = changeN.matcher(message);
			if (changeM.find()){
				String chanl = new String("");
				if (message.length()>4) {
				    chanl = message.substring(4);
				}
			sendMessage("#wintreath", chanl);
			}
		}
		Pattern byeC = Pattern.compile("^Goodbye");
		Matcher byeM = byeC.matcher(message);
			if(byeM.find()){
				if(sender.equalsIgnoreCase(c)){
					sendMessage(channel, "Goodbye Father,");
				}
				else if(sender.equalsIgnoreCase(w)){
					sendMessage(channel, "I'll talk to you later, my King.");
				}
				else if(sender.equalsIgnoreCase(l)){
					sendMessage(channel, "Have a good time, my gueen.");
					}
				else if(sender.equalsIgnoreCase(C)){
					sendMessage(channel, "See you later, prince Charax.");
				}
				
				else{
					sendMessage(channel, "Goodbye " + sender);
				}
			}
		if(message.equals("Hello Zatch")){
			sendMessage(channel,"Hello, " + sender);
		}
		
		Pattern goodN = Pattern.compile("^Goodnight");
		Matcher goodM = goodN.matcher(message);
			if(goodM.find()){
				String chanl = "";
				if(message.length()>6){
					chanl = "";
				}
				if(sender.equalsIgnoreCase(c)){
					sendMessage(channel, "Goodnight Father ");
				}
				else if(sender.equalsIgnoreCase(w)){
					sendMessage(channel, "Goodnight, my king");
				}
				else if(sender.equalsIgnoreCase(l)){
					sendMessage(channel, "Goodnight, my queen");
					}
				else if(sender.equalsIgnoreCase(C)){
					sendMessage(channel, "Goodnight prince.");
				}
				else{
						sendMessage(channel, "Goodnight " + sender + chanl);
				}
				}
		if (sender.equals("Chanku")){
			Pattern joinP = Pattern.compile("^\\.join");
			Matcher joinM = joinP.matcher(message);
			if (joinM.find()) {
				String chanl = new String("");
				if (message.length()>6) {
					chanl = message.substring(6);
				} 
				joinChannel("#" + chanl);
			}
			if (sender.equals(x)){
				Pattern joinX = Pattern.compile("^\\.join");
				Matcher joinF = joinX.matcher(message);
				if (joinF.find()) {
					String chanl = new String("");
					if (message.length()>6) {
						chanl = message.substring(6);
					} 
					joinChannel("#" + chanl);
				}
			}
		}
		if (sender.equals("Chanku")){
			Pattern leaveP = Pattern.compile("^\\.leave");
			Matcher leaveM = leaveP.matcher(message);
			if (leaveM.find()){
				String chanl = new String("");
				if(message.length()>7) {
					chanl = message.substring(7);
				}
				partChannel("#" + chanl);
			}
		}
		if (sender.equals(x)){
			Pattern leaveP = Pattern.compile("^\\.leave");
			Matcher leaveM = leaveP.matcher(message);
			if (leaveM.find()){
				String chanl = new String("");
				if(message.length()>7) {
					chanl = message.substring(7);
				}
				partChannel("#" + chanl);
			}
		}
		if (message.equals(".Chanku")) {
			sendMessage(channel, "Test" );
			}
		if (message.equalsIgnoreCase("&Help")){
			if (sender.equals("Chanku")){
				channel = sender;
				sendMessage(channel, "Hello, Father. Need a refresher?");
				sendMessage(channel, "Well here are my commands:");
				sendMessage(channel, ".join channel");
				sendMessage(channel, ".leave channel");
				sendMessage(channel, ".vw message");
				sendMessage(channel, ".hello username");
				sendMessage(channel, "Hello Zatch");
				sendMessage(channel, "Goodnight.");
				sendMessage(channel, "&pie");
				sendMessage(channel, ".quit");
				sendMessage(channel, ".hugn user");
				sendMessage(channel, "Goodbye");
				sendMessage(channel, ".quit");
				sendMessage(channel, ".fishslap user");
				sendMessage(channel, "$fish");
				sendMessage(channel, ".site");
				sendMessage(channel, ".code");
				sendMessage(channel, ".rrstatus");
				sendMessage(channel, ".rrtoggle");
				sendMessage(channel, ".date");
				sendMessage(channel, ".time");
			}
			else{
				sendMessage(sender, "I am Zatch! A bot created in Java (using Pircbot"
						+ " as a base.) My Father is Chanku. I am still in testing and "
						+ "development.");
				sendMessage(sender, "Here are my Commands:");
				sendMessage(sender, "Hello Zatch");
				sendMessage(sender, ".hello (username)");
				sendMessage(sender, "Goodnight");
				sendMessage(sender, "I hate you Zatch");
				sendMessage(sender, "&pie");
				sendMessage(sender, "Goodbye");
				sendMessage(sender, ".hugn user");
				sendMessage(sender, ".fishslap user");
				sendMessage(sender, "$fish");
				sendMessage(sender, ".code");
				sendMessage(sender, ".site");
				sendMessage(sender, ".rrstatus");
				sendMessage(sender, ".date");
				sendMessage(sender, ".time");
			}
		}
		if (message.equals("&pie")){
			sendAction(channel, "Throws pie in " + sender + "'s " + "face.");
		}
	Pattern catoP = Pattern.compile("^\\.hello");
	Matcher catoM = catoP.matcher(message);
	if (catoM.find()) {
	  String carthago = new String("");
	  if (message.length()>6) {
	    carthago = message.substring(6);
	  } else {
	    carthago = " User";
	  }
	  sendMessage(channel,"Hello"+carthago);
	}
	//Removed Code
	/* if (message.equals("I hate you Zatch")){
	sendMessage(channel, "I hate you too, bitch");
	} */
	/*if(message.equals("Goodnight")){
	if (sender.equals(c)){
		sendMessage(channel, "Night, night father");
	}
	if (sender.equalsIgnoreCase("wintermoot")){
		sendMessage(channel, "Night, my king.");
	if (sender.equals("Charax")){
		sendMessage(channel, "Night, Prince");
	}
	if (sender.equals("Leutheria")){
		sendMessage(channel, "Night my queen.");
	}
	else{
		sendMessage(channel, "Goodnight");
	}
	}
	}*/
	}
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason){
	if(recipientNick.equalsIgnoreCase(getNick())) {
	    joinChannel(channel);
	}
	}
	protected void onDisconnect(){
		while (!isConnected()) {
		    try {
		        reconnect();
		    }
		    catch (Exception e) {
		    }
		}
	}
}
