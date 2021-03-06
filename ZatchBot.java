/*A Simple IRC bot that uses PIRC
    Copyright (C) 2014  

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/

//Created by Sapein.
//Zatchbot v 1.0
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
	static String Master; //This is the master's name it is replaced upon the loading of the config file
	static String BotNick; //This is the bot's name, it is replaced upon the loading of the config file 
	static String OpNick; //This is the line of the Op's Nicks, this is only temporary to store it.
	static String OpHostname; //This is the entire line of the Op's Hostnames. This is only to temporarily store it
	static String LogsLocation; //This is the location where you save the logs too
	static String chann; 
	boolean OpNickUsed = false; //Checks to see if the Nicks of the Ops are to be used in authentication or not
	boolean OpHostnameUsed; //Checks to see if the hostnames should be used in authentication or not
	boolean toggleLogs; //Checks to see if you have enabled logs
    String[] OpNicks; //creates the array for the Nicks
    String[] OpHostnames; //creates an array for the hostnames
    ArrayList<String> OpAddHostnames = null; 
    ArrayList<String> includedChannels;
    final static String version = "1.1";
	
	public ZatchBot() throws Exception{
	
		loadConfig("");
	    OpNicks = OpNick.split(",");
	    OpHostnames = OpHostname.split(",");

	    this.setName(BotNick);
		this.setLogin("Zatch");
		System.out.println(toggleLogs);
		System.out.println(LogsLocation);
		
	}
	protected void onJoin(String channel, String sender, String login, String hostname){
		String time = getTime();
		String date = getDate();
		//Logging
		logging(" ", channel, date, time, sender, "join", " ");
		//Auto-op
		for(int OpNumber = 0; OpNumber < OpNicks.length; ++OpNumber) { 
			
			if(OpHostnameUsed == false && (sender.equalsIgnoreCase(Master) || sender.equalsIgnoreCase(OpNicks[OpNumber]))){ //Actually Joins the channels. 
				op(channel, sender);
			}
			if(OpHostnameUsed == true && (sender.equalsIgnoreCase(Master) || ((OpNickUsed == true && sender.equalsIgnoreCase(OpNicks[OpNumber])) || (OpNickUsed == false)))){
				for(int opHostnameNumber = 0; opHostnameNumber < OpHostnames.length; ++opHostnameNumber){
					if(hostname.equals(OpHostnames[opHostnameNumber])){
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
		String time = getTime();
		String date = getDate();
		logging(" ", channel, date, time, sender, "part", " ");
		sendMessage(channel, "Good-bye.");
	}
	protected void onPrivateMessage(String sender, String login, String hostname, String message){
		sendMessage(Master, sender + ": " + message); 
	}
	protected void onAction(String sender, String login, String hostname, String target, String action){
		String date = getDate();
		String time = getTime();
		logging(action, target, date, time, sender, "action", " ");
	}
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{	
		//Begin Variables
		chann = channel;
		String yourDate;
		String yourTime;
		//Begin Time and Date Variables
		yourDate = getDate();
		yourTime = getTime();
		
		boolean xChan = false; //Boolean for cross-channel Communication
		//End Time and Date Variables
		
		//End Variables
		
		//Begin Help Code
		//Help code stays on top
		
		if (message.equalsIgnoreCase("&Help")){
			for(int OpNumber = 0; OpNumber < OpNicks.length; ++OpNumber) {
				if (sender.equals(Master)){
					sendMessage(sender, "Hello, Master. Do you need a refresher?");
					sendMessage(sender, "Well here are my commands:");
					sendMessage(sender, "&join channel");
					sendMessage(sender, "&leave channel");;
					sendMessage(sender, "Hello Zatch");
					sendMessage(sender, "Goodnight.");
					sendMessage(sender, "&pie");
					sendMessage(sender, "&quit");
					sendMessage(sender, "Goodbye");
					sendMessage(sender, "&fishslap user");
					sendMessage(sender, "&fish");
					sendMessage(sender, "&code");
					sendMessage(sender, "&date");
					sendMessage(sender, "&time");
					sendMessage(sender, "&x-chan");
					sendMessage(sender, "&Op");
					sendMessage(sender, "&leave");
					sendMessage(sender, "&conn-start");
					sendMessage(sender, "&conn-term");
					sendMessage(sender, "&conn-add");
					sendMessage(sender, "&conn-del");
					sendMessage(sender, "&version");
				}	
				if(OpHostnameUsed == false || sender.equalsIgnoreCase(OpNicks[OpNumber])){
					sendMessage(sender, "Hello " + sender + " I am " + BotNick + ". It seems that you have been designated as an Op"
							+ " by my master, " + Master + " as such you are granted to see more commands");
					sendMessage(sender, "Operator Commands");
					sendMessage(sender, "&join");
					sendMessage(sender, "&leave");
					sendMessage(sender, "&conn-start");
					sendMessage(sender, "&conn-term");
					sendMessage(sender, "&conn-add");
					sendMessage(sender, "&conn-del");
					sendMessage(sender, " ");
					sendMessage(sender, "Standard Commands");
					sendMessage(sender, " ");
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
					sendMessage(sender, "&version");
				}
				else{
					sendMessage(sender, "Hello! I am " + BotNick + " a Zatch IRC bot! My Base was written by Sapein"
							+ "however, my master is " + Master + ".");
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
					sendMessage(sender, "&version");
				}
			}
		}
		//end Help Code
		

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
			for(int OpNumber = 0; OpNumber < OpNicks.length; ++OpNumber) {
				if(OpHostnameUsed == false && (sender.equalsIgnoreCase(Master) || sender.equalsIgnoreCase(OpNicks[OpNumber]))){
					//Begin of Channel Connection Code
					Pattern cross2P = Pattern.compile("^\\&conn-start");
					Matcher cross2M = cross2P.matcher(message);
					if(cross2M.find()){
						if(message.length()>5) {
							includedChannels = new ArrayList<String>(Arrays.asList(message.split(" ")));
							includedChannels.remove(0);
							int x = 0;
							while(x < includedChannels.size()){
								sendMessage(includedChannels.get(x), "A channel Connection has been started by: " + sender + " - " + channel);
								x++;
							}
							includedChannels.add(channel);
						}
						xChan = true;
					}
					//End of Channel Connection Code
					//Channel Communication Code Drop Begin
					Pattern cross3P = Pattern.compile("^\\&conn-term");
					Matcher cross3M = cross3P.matcher(message);
					if(cross3M.find()){
						if(message.length()>5) {
							int x = 0;
							while(x < includedChannels.size()){
								sendMessage(includedChannels.get(x), "The Connection has been terminated by: " + sender + " - " + channel);
								x++;
							}
						}
						includedChannels = null;
						xChan = false;
					}
					//Channel Communication Code Drop End
					//Channel Connection Code Add Begin
					Pattern cross4P = Pattern.compile("^\\&conn-add");
					Matcher cross4M = cross4P.matcher(message);
					if(cross4M.find()){
						if(message.length()>5) {
							int x = 0;
							ArrayList<String> list = new ArrayList<String>(Arrays.asList(message.split(" ")));
							list.remove(0);
							while(x <= list.size()){
								includedChannels.add(list.get(x));
								sendMessage(list.get(x), "You have been added to a Channel-Connection! Connection Established By: " + sender + " - " + channel);
								x++;
							}
						}
					}
					//Channel Connection Code Add End
					//Channel Connection Code Remove Begin
					Pattern cross5P = Pattern.compile("^\\&conn-del");
					Matcher cross5M = cross5P.matcher(message);
					if(cross5M.find()){
						if(message.length()>5) {
							int x = 0;
							ArrayList<String> list = new ArrayList<String>(Arrays.asList(message.split(" ")));
							list.remove(0);
							while(x <= list.size()){
								if(includedChannels.get(x).equals(list.get(x))){
									sendMessage(list.get(x), "You have been removed from the Channel-Connection! Removed By: " + sender + " - " + channel);
									includedChannels.remove(x);
								}
								x++;
							}
						}
					}
					//Channel Connection Code Remove End
					}
					if(OpHostnameUsed == true && (sender.equalsIgnoreCase(Master) || (OpNickUsed == true && sender.equalsIgnoreCase(OpNicks[OpNumber])) || (OpNickUsed == false))){
						for(int opHostnameNumber = 0; opHostnameNumber < OpHostnames.length; ++opHostnameNumber){
							//Begin of Channel Connection Code
							Pattern cross2P = Pattern.compile("^\\&conn-start");
							Matcher cross2M = cross2P.matcher(message);
							if(cross2M.find()){
								if(message.length()>5) {
									includedChannels = new ArrayList<String>(Arrays.asList(message.split(" ")));
									includedChannels.remove(0);
									int x = 0;
									while(x < includedChannels.size()){
										sendMessage(includedChannels.get(x), "A channel Connection has been started by: " + sender + " - " + channel);
										x++;
									}
									includedChannels.add(channel);
								}
								xChan = true;
							}
							//End of Channel Connection Code
							//Channel Communication Code Drop Begin
							Pattern cross3P = Pattern.compile("^\\&conn-term");
							Matcher cross3M = cross3P.matcher(message);
							if(cross3M.find()){
								if(message.length()>5) {
									int x = 0;
									while(x < includedChannels.size()){
										sendMessage(includedChannels.get(x), "The Connection has been terminated by: " + sender + " - " + channel);
										x++;
									}
								}
								includedChannels = null;
								xChan = false;
							}
							//Channel Communication Code Drop End
							//Channel Connection Code Add Begin
							Pattern cross4P = Pattern.compile("^\\&conn-add");
							Matcher cross4M = cross4P.matcher(message);
							if(cross4M.find()){
								if(message.length()>5) {
									int x = 0;
									ArrayList<String> list = new ArrayList<String>(Arrays.asList(message.split(" ")));
									list.remove(0);
									while(x <= list.size()){
										includedChannels.add(list.get(x));
										sendMessage(list.get(x), "You have been added to a Channel-Connection! Connection Established By: " + sender + " - " + channel);
										x++;
									}
								}
							}
							//Channel Connection Code Add End
							//Channel Connection Code Remove Begin
							Pattern cross5P = Pattern.compile("^\\&conn-del");
							Matcher cross5M = cross5P.matcher(message);
							if(cross5M.find()){
								if(message.length()>5) {
									int x = 0;
									ArrayList<String> list = new ArrayList<String>(Arrays.asList(message.split(" ")));
									list.remove(0);
									while(x <= list.size()){
										if(includedChannels.get(x).equals(list.get(x))){
											sendMessage(list.get(x), "You have been removed from the Channel-Connection! Removed By: " + sender + " - " + channel);
											includedChannels.remove(x);
										}
										x++;
									}
								}
							}
						}
					}
				}
					//Channel Connection Code Remove End
					//Begin of Channel Connection Send Code
					if(xChan == true){
						int x = 0;
						if(includedChannels.contains(channel)){
							while (x < includedChannels.size()){
								if(!channel.equals(includedChannels.get(x))){
									sendMessage(includedChannels.get(x), channel + " - " + sender + ": " + message);
								}
								x++;
							}
						}
					}
					//Channel Connection Send Code End 
				
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
		
		//Begin Version Command
		if(message.equalsIgnoreCase("&version")){
			sendMessage(channel, "PIRCBOT: " + VERSION);
			sendMessage(channel, "Zatch: " + version);
		}
		//End Version Command
		
		//Begin Pie Command
		if (message.equalsIgnoreCase("&pie")){
			sendAction(channel, "Throws pie in " + sender + "'s " + "face.");
		}
		//End Pie Command
		
		//Begin Fish Slap command
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
		//End Fishslap Command
		
		//Begin Fish Command
		if(message.equalsIgnoreCase("&fish")){
			sendMessage(channel, "FISH");
		}
		//End Fish Command
		
		//Current Time Command
		if(message.equalsIgnoreCase("&time")){
			sendMessage(channel, "It is " + yourTime + " CST");
		}
		//Current Date Command
		if(message.equalsIgnoreCase("&date")){
			sendMessage(channel, "It is " + yourDate + " in the CST Timezone");
		}
		//End Fun IRC Commands
		
		//Begin Proccess that Zatch Does Automatically
		//auto-log code begin
		logging(message, channel, yourDate, yourTime, sender, "message",  " ");
		//Auto-op
		for(int OpNumber = 0; OpNumber < OpNicks.length; ++OpNumber) { 
			
			if(OpHostnameUsed == false && (sender.equalsIgnoreCase(Master) || sender.equalsIgnoreCase(OpNicks[OpNumber]))){ //Actually Joins the channels. 
				op(channel, sender);
			}
			if(OpHostnameUsed == true && (sender.equalsIgnoreCase(Master) || (OpNickUsed == true && sender.equalsIgnoreCase(OpNicks[OpNumber])) || (OpNickUsed == false))){
				for(int opHostnameNumber = 0; opHostnameNumber < OpHostnames.length; ++opHostnameNumber){
					if(hostname.equals(OpHostnames[opHostnameNumber])){
						op(channel, sender);
					}
				}
			}
	  	}
		//auto-log code ends
		
		if(sender.equalsIgnoreCase(Master)){
			if(message.equalsIgnoreCase("&reloadConfigs")){
				try {
					loadConfig(channel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(sender.equalsIgnoreCase(Master)){
			if(message.equalsIgnoreCase("&updateConfig")){
				try {
					updateConfig(channel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		//End Processes that Zatch Does Automatically
	}
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason){
		String time = getTime();
		String date = getDate();
		logging(reason, channel, date, time, kickerNick, "kick", recipientNick);
		if(recipientNick.equalsIgnoreCase(getNick())) { //If it gets kicked
			joinChannel(channel); //If the bot is kicked it will rejoin the channel immediately
		}
	}
	protected void onDisconnect(){ 
		while (!isConnected()) { //To test when connection is lost
		    try {
		        reconnect(); //To have the bot reconnect
		    }
		    catch (Exception e) {
		    }
		}
	}
	
	/*
	 * This handles loading and reloading of the config file. Please note that the config version return will be moved from 
	 * this function and given it's own function as loadConfig is mainly supposed to load/reload the config. 
	 */
	protected String loadConfig(String chan) throws Exception{
		String configVersion;
		BufferedReader saveFile;
		saveFile = new BufferedReader(new FileReader("Config.txt"));
		configVersion = saveFile.readLine(); //1st line;
		sendMessage(chan, "RELOADING CONFIG");
		if(!configVersion.startsWith("Config Version")){
			saveFile.readLine(); //3nd line 
			saveFile.readLine(); //4rd line
		    saveFile.readLine(); //5th line
		    saveFile.readLine(); //6th line
		    saveFile.readLine(); //7th line
		    saveFile.readLine(); //8th line
		    saveFile.readLine(); //9th line
		    OpHostnameUsed = Boolean.parseBoolean(saveFile.readLine()); //10th line
		    saveFile.readLine(); //11th line
		    OpNickUsed = Boolean.parseBoolean(saveFile.readLine()); //12th line
		    saveFile.readLine(); //13th line
		    BotNick = saveFile.readLine(); //14th line
		    saveFile.readLine(); //15th line
		    Master = saveFile.readLine(); //16th line
		    saveFile.readLine(); //17th line
		    OpNick = saveFile.readLine(); //18th line
		    saveFile.readLine(); //19th line
		    OpHostname = saveFile.readLine(); //10h line 
		    saveFile.readLine(); //21th line 
		    toggleLogs = Boolean.parseBoolean(saveFile.readLine()); //22st line
		    saveFile.readLine(); //23nd Line
		    if(toggleLogs == true){
		    	LogsLocation = saveFile.readLine(); //24rd line
		    }
		    else{
		    	saveFile.readLine(); //24rd line
		    }
		    saveFile.close();
		    sendMessage(chan, "CONFIG SUCCESSFULLY RELOADED");
		}else{
			saveFile.readLine(); //2nd line
			saveFile.readLine(); //3nd line 
			saveFile.readLine(); //4rd line
		    saveFile.readLine(); //5th line
		    saveFile.readLine(); //6th line
		    saveFile.readLine(); //7th line
		    saveFile.readLine(); //8th line
		    saveFile.readLine(); //9th line
		    OpHostnameUsed = Boolean.parseBoolean(saveFile.readLine()); //10th line
		    saveFile.readLine(); //11th line
		    OpNickUsed = Boolean.parseBoolean(saveFile.readLine()); //12th line
		    saveFile.readLine(); //13th line
		    BotNick = saveFile.readLine(); //14th line
		    saveFile.readLine(); //15th line
		    Master = saveFile.readLine(); //16th line
		    saveFile.readLine(); //17th line
		    OpNick = saveFile.readLine(); //18th line
		    saveFile.readLine(); //19th line
		    OpHostname = saveFile.readLine(); //10h line 
		    saveFile.readLine(); //21th line 
		    toggleLogs = Boolean.parseBoolean(saveFile.readLine()); //22st line
		    saveFile.readLine(); //23nd Line
		    if(toggleLogs == true){
		    	LogsLocation = saveFile.readLine(); //24rd line
		    }
		    else{
		    	saveFile.readLine(); //24rd line
		    }
		    saveFile.close();
		    sendMessage(chan, "CONFIG SUCCESSFULLY RELOADED");
		}
	    return configVersion;
	}
	
	/*
	 * It updates the config to the latest version, this allows for the bot to check and make sure that the latest
	 * config is up-to-date. It also allows for me to make changes to the config file and not worry about breaking things
	 * or having to have users update the config themselves and have something mess up.
	 */
	public void updateConfig(String chan) throws Exception{
		String versionCheck = loadConfig("");
		File file = new File("Config.txt");
		File oldFile = new File("Config-Backup.txt");
		if(!versionCheck.equalsIgnoreCase("Config Version: " + ZatchBotMain.getConfigVersion())){
				sendMessage(chan, "Now Updating the Config file.");
				file.renameTo(oldFile);
				file.createNewFile(); //creates the file
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Config Version: " + ZatchBotMain.getConfigVersion() +  "\r\n"); 
				bw.write("		=--Connection--=" +"\r\n");
				bw.write("--IRC Server--" + "\r\n"); //puts this on the first line of the file
				bw.write(ZatchBotMain.Server + "\r\n"); //puts this on the second line of the file
				bw.write("--Channels--" + "\r\n"); //puts this on the third line of the file
				bw.write("== To add Multiple Channels use a comma(,) inbetween with no spaces ==" + "\r\n"); //puts this on the fourth line of the file
				bw.write(ZatchBotMain.Channel + "\r\n"); //puts this on the fifth line of the file
				bw.write("		==--IRC Bot--==" + "\r\n"); //creates the sixth line of the file
				bw.write("--Toggle Hostname Verification for Ops--" + "\r\n"); //puts this on the Seventh line of the file
				bw.write(OpHostnameUsed + "\r\n"); //makes Hostname Verification False by default
				bw.write("--Toggle Nick Verification for Ops--" + "\r\n"); //puts this on the Ninth line of the file
				bw.write(OpNickUsed + "\r\n"); //Sets the Nick verification to true
				bw.write("--Bot Name--" +"\r\n"); //puts this on the Eleventh line of the file
				bw.write(BotNick + "\r\n"); //puts this on the twelfth line of the file
				bw.write("--Master--" + "\r\n"); //puts this on the thirteenth line of the file
				bw.write(Master + "\r\n"); //puts this on the fourteenth line of the file
				bw.write("--Op Nicks--" + "\r\n"); //puts this on the fifteenth line of the file
				bw.write(OpNick + "\r\n"); //generates a blank space
				bw.write("--Op Hostnames" + "\r\n"); //puts this on the Seventeenth line of the file
				bw.write(OpHostname + "\r\n"); //generates blank space on the Eighteenth line of the file
				bw.write("--Toggle Logs" +"\r\n"); 
				bw.write(toggleLogs + "\r\n");
				bw.write("--Logs Location--" + "\r\n");
				bw.write(LogsLocation + "\r\n");
				bw.close(); //closes the writer. 
				sendMessage(chan, "UPDATE SUCCESSFUL");
		}else{
			sendMessage(chan, "The Config is already up to date!");
		}
		loadConfig("");
	}
	
	/*
	 * The following functions are mainly for logging and cause nothing to really occur.
	 */
	protected void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason){
		String time = getTime();
		String date = getDate();
		logging(reason, chann, date, time, sourceNick, "discon", " ");
	}
	protected void onNickChange(String oldNick, String login, String hostname, String newNick){
		String time = getTime();
		String date = getDate();
		logging(newNick, chann, date, time, oldNick, "nChange", " ");
	}
	protected void onDeVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient){
		logging(recipient, channel, getDate(), getTime(), sourceNick, "voiceRemove", " ");
	}
	protected void onVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient){
		logging(recipient, channel, getDate(), getTime(), sourceNick, "voiceGiven", " ");
	}
	protected void onSetChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask){
		logging(hostmask, channel, getDate(), getTime(), sourceNick, "banSet", " ");
	}
	protected void onRemoveChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask){
		logging(hostmask, channel, getDate(), getTime(), sourceNick, "banRemoved", " ");
	}
	protected void onSetModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname){
		logging(" ", channel, getDate(), getTime(), sourceNick, "mModeSet", " ");
	}
	protected void onRemoveModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname){
		logging(" ", channel, getDate(), getTime(), sourceNick, "mModeRemoved", " ");
	}
	protected void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient){
		logging(recipient, channel, getDate(), getTime(), sourceNick, "opGiven", " ");
	}
	protected void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient){
		logging(recipient, channel, getDate(), getTime(), sourceNick, "opRemoved", " ");
	}
	protected void onTopic(String channel, String topic, String setBy, long date, boolean changed){
		if(changed == false){
			logging(setBy, channel, getDate(), getTime(), topic, "topic", " ");
		}else{
			logging(topic, channel, getDate(), getTime(), setBy, "topicChange", " ");
		}
	}
	
	/*
	 * This simply allows us to get the date
	 */
	private String getDate(){
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy"); //Sets the date format To Month-Day-Year
		Date date = new Date(); //stores the date
		String yourDate = dateFormat.format(date); //turns the date into a variable to be called later
		return yourDate;
	}
	
	/*
	 * This simply allows us to get the time
	 */
	private String getTime(){
		DateFormat dF = new SimpleDateFormat("HH:mm"); //Sets the Time format to Hour:Minute
		Date time = new Date(); //stores the time
		String yourTime = dF.format(time); //turns the time into a variable to be called later
		return yourTime;
	}
	
	/*
	 * This handles the logging, this is in the process of being deprecated and replaced with newLogging()
	 */
	private void logging(String msg, String chan, String date, String time, String sender, String mode, String sender1){
		newLogging(msg, chan, sender, mode, sender1);
	}
	
	/*
	 * Replacement logging function, it removes the time and date handling and moves it to inside of the logging. 
	 */
	private void newLogging(String msg, String chan, String sender, String mode, String sender1){
		String time = getTime();
		String date = getDate();
		
		if(toggleLogs == true){
			Pattern change2 = Pattern.compile("^");
			Matcher change1 = change2.matcher(msg);
			if (change1.find()){
				String chanl1 = new String("");
				if (msg.length()>0) {
					chanl1 = msg.substring(0);
				}
				try {
					File file = new File(LogsLocation + chan + " " + date + " " + "log.txt");
					if (!file.exists()) {
						file.createNewFile();
					}
					FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
					BufferedWriter bw = new BufferedWriter(fw);
					if(mode == "message"){
						bw.write(chan +" " + time + " " + sender + ":" + chanl1 + "\r\n");
					}if(mode == "action"){
						bw.write(chan + " " + time + " * " + sender + " " + chanl1 + "\r\n");
					}if(mode == "join"){
						bw.write(chan + " " + time + " " + sender + " joined the channel" + "\r\n");
					}if(mode == "part"){
						bw.write(chan + " " + time + " " + sender + " left the channel" + "\r\n");
					}if(mode == "discon"){
						bw.write(chan + time + " " + sender + " quit. Reason: " + chanl1 + "\r\n");
					}if(mode == "nChange"){
						bw.write(chan + " " + time + " " + sender + " changed their Nick to " + chanl1 + "\r\n");
					}if(mode == "kick"){
						bw.write(chan + time + " " + sender + " kicked " + sender1 + " for " + chanl1 + "\r\n");
					}if(mode == "topicChange"){
						bw.write(chan + " " + time + " The topic was changed to " + chanl1 + " by " + sender + "\r\n");
					}if(mode == "mModeSet"){
						bw.write(chan + " " + time + " +m flag set for the channel(Channel muted) by: " + sender + "\r\n");
					}if(mode == "mModeRemoved"){
						bw.write(chan + " " + time + " -m flag was set for the channel(Channel Unmuted) by: " + sender + "\r\n");
					}if(mode == "banRemoved"){
						bw.write(chan + " " + time + ": " + chanl1 + " was unbanned by " + sender + "\r\n");
					}if(mode == "banSet"){
						bw.write(chan + " " + time + ": " + chanl1 + " was banned by " + sender + "\r\n");
					}if(mode == "voiceGiven"){
						bw.write(chan + " " + time + " " + chanl1 + " was set to +v (has been given voice) by " + sender + "\r\n");
					}if(mode == "voiceRemoved"){
						bw.write(chan + " " + time + " " + chanl1 + " was set to -v (has voice removed) by " + sender + "\r\n");
					}if(mode == "opGiven"){
						bw.write(chan + " " + time + " " + chanl1 + " was given Op by " + sender + "\r\n");
					}if(mode == "opRemoved"){
						bw.write(chan + " " + time + " " + chanl1 + " was removed from Op by " + sender + "\r\n");
					}if(mode == "topic"){
						bw.write(chan + " " + time + "The topic of the channel is " + sender + " and was set by " + chanl1 + "\r\n");
					}
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}	



