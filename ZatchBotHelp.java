import org.jibble.pircbot.*;


public class ZatchBotHelp{
	private Queue _outQueue = new Queue();
	public final void sendMessage(String target, String message) {
		_outQueue.add("PRIVMSG " + target + " :" + message);
    }
	public void Help(String message, String sender, String[] OpNicks, String Master, String BotNick, boolean OpHostnameUsed){
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
	}	
}
