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
//Zatchbot v 2.0-alpha
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.jibble.pircbot.*;

public class ZatchBot extends PircBot
{
	static ZatchBotLogging logging = new ZatchBotLogging();
	static ZatchBotDateAndTime dateandtime = new ZatchBotDateAndTime();
	static String chann; 
    static String[] OpNicks; //creates the array for the Nicks
    static String[] OpHostnames; //creates an array for the hostnames
    //ArrayList<String> OpAddHostnames = null; 
    ArrayList<String> includedChannels;
    final static String version = "1.1";
	
	public ZatchBot() throws Exception{
		ZatchBotConfigStartup ConfigStart = new ZatchBotConfigStartup();
		ZatchBotConfig Config = new ZatchBotConfig();
		ConfigStart.loadConfigState2();
		String OpNick = Config.getOpNick();
		String OpHostname = Config.getOpHostname();
		String BotNick = Config.getBotNick();
		
	    OpNicks = OpNick.split(",");
	    OpHostnames = OpHostname.split(",");

	    this.setName(BotNick);
		this.setLogin("Zatch");
		
	}
	protected void onJoin(String channel, String sender, String login, String hostname){
		ZatchBotConfig Config = new ZatchBotConfig();
		String BotNick = Config.getBotNick();
		String Master = Config.getMaster();
		boolean OpHostnameUsed = Config.getOpHostnameUsed();
		boolean OpNickUsed = Config.getOpNickUsed();
		//logging.logging
		logging.logging(" ", channel, sender, "join", " ");
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
		logging.logging(" ", channel, sender, "part", " ");
		sendMessage(channel, "Good-bye.");
	}
	protected void onPrivateMessage(String sender, String login, String hostname, String message){
		ZatchBotConfig Config = new ZatchBotConfig();
		String Master = Config.getMaster();
		sendMessage(Master, sender + ": " + message); 
	}
	protected void onAction(String sender, String login, String hostname, String target, String action){
		logging.logging(action, target, sender, "action", " ");
	}
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{	
		ZatchBotConfigCommands ConfigCommands = new ZatchBotConfigCommands();
		ZatchBotConfig Config = new ZatchBotConfig();
		ZatchBotHelp Help = new ZatchBotHelp();
		ZatchBotCrossChannel xChan = new ZatchBotCrossChannel();
		
		//Begin Variables
		boolean OpNickUsed = Config.getOpNickUsed();
		String BotNick = Config.getBotNick();
		String Master = Config.getMaster();
		boolean OpHostnameUsed = Config.getOpHostnameUsed();
		String yourDate;
		String yourTime;
		//Begin Time and Date Variables
		yourDate = dateandtime.getDate();
		yourTime = dateandtime.getTime();
		
		//End Time and Date Variables
		
		//End Variables
		
		//Begin Help Code
		//Help code stays on top
		Help.Help(message, sender, OpNicks, Master, BotNick, OpHostnameUsed);
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
		//Begin Code for Cross Channel Communication Commands
		xChan.xchan(message, channel, sender); //this contains the command xChan
		xChan.channelLinkingCommands(message, channel, sender, Master, OpHostnames, OpNicks, OpHostnameUsed, OpNickUsed);
		xChan.crossChannelSend(channel, sender, message);
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
		logging.logging(message, channel, sender, "message",  " ");
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
					ConfigCommands.loadConfig(channel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(sender.equalsIgnoreCase(Master)){
			if(message.equalsIgnoreCase("&updateConfig")){
				try {
					ConfigCommands.updateConfig(channel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		//End Processes that Zatch Does Automatically
	}
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason){
		logging.logging(reason, channel, kickerNick, "kick", recipientNick);
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
	 * This is done to get the mode of the change of channel things...this isn't as descriptive as I would want it to be though :/
	 */	
	protected void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode){
		logging.logging(mode, channel, sourceNick, "mode", " ");
	}
	
	
	protected void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason){
		logging.logging(reason, chann, sourceNick, "discon", " ");
	}
	
	protected void onNickChange(String oldNick, String login, String hostname, String newNick){
		logging.logging(newNick, chann, oldNick, "nChange", " ");
	}
	
	
	protected void onTopic(String channel, String topic, String setBy, long date, boolean changed){
		if(changed == false){
			logging.logging(setBy, channel, topic, "topic", " ");
		}else{
			logging.logging(topic, channel, setBy, "topicChange", " ");
		}
	}
	
	

	
}	



