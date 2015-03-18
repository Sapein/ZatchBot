package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ZatchBotLogging {
	
	private  static boolean isLoggingActive = false;
	private static String loggingLocation = null;
	
	/*
	 * This is the logging.logging function, which handles all of the bot's logging capabilities, as all things that are logged
	 * are done so through here.  
	 */
	public void logging(String msg, String chan, String sender, String mode, String sender1){
		ZatchBotDateAndTime dateAndTime = new ZatchBotDateAndTime();
		String time = dateAndTime.getTime();
		String date = dateAndTime.getDate();
		if(isLoggingActive == true){
			Pattern change2 = Pattern.compile("^");
			Matcher change1 = change2.matcher(msg);
			if (change1.find()){
				String chanl1 = new String("");
				if (msg.length()>0) {
					chanl1 = msg.substring(0);
				}
				try {
					File file = new File(loggingLocation + chan + " " + date + " " + "log.txt");
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
					}if(mode == "topic"){
						bw.write(chan + " " + time + "The topic of the channel is " + sender + " and was set by " + chanl1 + "\r\n");
					}if(mode == "mode"){
						bw.write(chan + " " + time + " some user flags were changed! Flags and users are: " + chanl1 + " this was done by: " + sender );
					}
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setLoggingMode(boolean isLoggingActive){
		this.isLoggingActive = isLoggingActive;
	}
	
	public boolean getLoggingMode(){
		return isLoggingActive;
	}
	
	public void setLoggingLocation(String loggingLocation){
		this.loggingLocation = loggingLocation;
	}
	public String getLoggingLocation(){
		return loggingLocation;
	}
}
