//Created by Sapein.
//Zatchbot v 1.0 BETA
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jibble.pircbot.*;
public class ZatchBot extends PircBot
{
	public ZatchBot(){
		this.setName("Zatch");
		this.setLogin("Zatch");
	}
	protected void onJoin(String channel, String sender, String login, String hostname){
		String Hello = "Hello ";
		if (sender.equals("Zatch")){
			sender = "";
			Hello = "";
		}
		sendMessage(channel, Hello + sender);
	}
	protected void onPart(String channel, String sender, String login, String hostname){
			sendMessage(channel, "Good-bye.");
	}
	protected void onQuit(String channel, String sender, String login, String hostname){
		sendMessage(channel, "Good-bye.");
	}
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{	
		String c = "Chanku";
		String w = "Wintermoot";
		String l = "Leutheria";
		String C = "Charax";
		if (message.equals(".op")){
			op("#ZatchCore", "Chanku");
		}
		if(sender.equals("Chanku")){
			if(message.equals(".quit")){
				quitServer();
			}
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
				if (message.length()>6) {
				    chanl = message.substring(6);
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
		}
		if (sender.equals("Chanku")){
			Pattern leaveP = Pattern.compile("^\\.leave");
			Matcher leaveM = leaveP.matcher(message);
			if (leaveM.find()){
				String chanl = new String("");
				if(message.length()>6) {
					chanl = message.substring(6);
				}
				partChannel("#" + chanl);
			}
		}
		if (message.equals(".Chanku")) {
			sendMessage(channel, "Test" );
			}
		if (message.equalsIgnoreCase("$Help")){
			if (sender.equals("Chanku")){
				sendMessage(channel, "Hello, Father. Need a refresher?"
						+ " Well here are my commands"
						+ " .join #channel "
						+ " .leave #leave a channel"
						+ " .vw message"
						+ " .hello username"
						+ " Hello Zatch"
						+ " Goodnight."
						+ " $pie");
			}
			else{
				sendMessage(channel, "I am Zatch! A bot created in Java(using Pircbot"
						+ " as a base.) My Father is Chanku. I am still in testing and "
						+ "development."
						+ " Here are my Commands:"
						+ " Hello Zatch"
						+ " .hello (username)"
						+ " Goodnight"
						+ " I hate you Zatch"
						+ " $pie");
										
			}
		}
		if (message.equals("$pie")){
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