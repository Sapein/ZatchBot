//Created by Sapein.
//Zatchbot v 1.0 BETA
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jibble.pircbot.*;
public class ZatchBot extends PircBot
{
	String m = new String("");
	public ZatchBot(){
		this.setName("Zatch");
		this.setLogin("Zatch");
	}
	protected void onJoin(String channel, String sender, String login, String hostname){
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
		String d = "Dimitri";
		String c = "Chanku";
		String w = "Wintermoot";
		String l = "Leutheria";
		String C = "Charax";
		
		
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
					chanl = message.substring(4);
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
		if (message.equals(".Chanku")) {
			sendMessage(channel, "Test" );
			}
		if (message.equalsIgnoreCase("$Help")){
			if (sender.equals("Chanku")){
				sendMessage(channel, "Hello, Father. Need a refresher?");
				sendMessage(channel, " Well here are my commands:");
				sendMessage(channel, " .join #channel");
				sendMessage(channel, " .leave #leave a channel");
				sendMessage(channel, " .vw message");
				sendMessage(channel, " .hello username");
				sendMessage(channel, " Hello Zatch");
				sendMessage(channel, " Goodnight.");
				sendMessage(channel,  " $pie");
			}
			else{
				sendMessage(channel, "I am Zatch! A bot created in Java (using Pircbot"
						+ " as a base.) My Father is Chanku. I am still in testing and "
						+ "development.");
				sendMessage(channel, " Here are my Commands:");
				sendMessage(channel, " Hello Zatch");
				sendMessage(channel, " .hello (username)");
				sendMessage(channel, " Goodnight");
				sendMessage(channel, " I hate you Zatch");
				sendMessage(channel, "$pie");
										
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