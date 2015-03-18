import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jibble.pircbot.PircBot;


public class ZatchBotCrossChannel extends PircBot{
	private ArrayList<String>includedChannels;
	private boolean xChan;
	
	public void xchan(String message, String channel, String sender){
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
	}
	public void channelLinkingCommands(String message, String channel, String sender, String Master, String[]OpHostnames, String[] OpNicks, boolean OpHostnameUsed, boolean OpNickUsed){
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
	}
	
	public void crossChannelSend(String channel, String sender, String message){
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
	}
}
