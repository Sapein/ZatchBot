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
//Created by Sapein
//Zatchbot v 1.0

import java.io.*;

import org.jibble.pircbot.*;

public class ZatchBotMain {
	static String Server; //This will be replaced by what is inputed in the Config upon start. This is the IRC server 
	static String Channel; //This will be replaced by what is inputted in the Channel Line upon Start. These are the channels to Auto-Connect to. 
	

	public static void main(String[] arg) throws Exception {
		//Begin Config Creation Code
		File file = new File("Config.txt"); //sets the file name
	
		// NOTE: That the defaults set here are to prevent the bot from erroring out upon start-up, your changes will not be overwritten.
		if (!file.exists()) { //checks to see if it exists, if not it writes this
			file.createNewFile(); //creates the file

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("		=--Connection--=" +"\r\n");
			bw.write("--IRC Server--" + "\r\n"); //puts this on the first line of the file
			bw.write("irc.rizon.net" + "\r\n"); //puts this on the second line of the file
			bw.write("--Channels--" + "\r\n"); //puts this on the third line of the file
			bw.write("== To add Multiple Channels use a comma(,) inbetween with no spaces ==" + "\r\n"); //puts this on the fourth line of the file
			bw.write("#chat" + "\r\n"); //puts this on the fifth line of the file
			bw.write("		==--IRC Bot--==" + "\r\n"); //creates the sixth line of the file
			bw.write("--Toggle Hostname Verification for Ops--" + "\r\n"); //puts this on the Seventh line of the file
			bw.write("false " + "\r\n"); //makes Hostname Verification False by default
			bw.write("--Toggle Nick Verification for Ops--" + "\r\n"); //puts this on the Ninth line of the file
			bw.write("true " + "\r\n"); //Sets the Nick verification to true
			bw.write("--Bot Name--" +"\r\n"); //puts this on the Eleventh line of the file
			bw.write("Zatch" + "\r\n"); //puts this on the twelfth line of the file
			bw.write("--Master--" + "\r\n"); //puts this on the thirteenth line of the file
			bw.write("Chanku" + "\r\n"); //puts this on the fourteenth line of the file
			bw.write("--Op Nicks--" + "\r\n"); //puts this on the fifteenth line of the file
			bw.write("\r\n"); //generates a blank space
			bw.write("--Op Hostnames" + "\r\n"); //puts this on the Seventeenth line of the file
			bw.write("\r\n"); //generates blank space on the Eighteenth line of the file
			bw.write("--Toggle Logs" +"\r\n"); 
			bw.write("false" + "\r\n");
			bw.write("--Logs Location--" + "\r\n");
			bw.write("\r\n");
			bw.close(); //closes the writer. 
		}
		//End Config Creation Code  
		
		//Begin Config Access Code
		BufferedReader saveFile=
		        new BufferedReader(new FileReader("Config.txt"));
		   // Get the integer value from the String.
			saveFile.readLine();
			saveFile.readLine();
			Server = saveFile.readLine();
		    saveFile.readLine(); 
		    saveFile.readLine(); 
		    Channel = saveFile.readLine();
		    // Not needed, but read blank line at the bottom.
		    saveFile.close();
		    String[] Channels = Channel.split(",");
		//End Config Access Code
		    

    ZatchBot bot = new ZatchBot();      // this starts your bot
    bot.setVerbose(true);             // enable debugging, useful during programming
    bot.connect(Server); //Connect to the Server(The Server Variable is called here)
    for(int ChannelAmount = 0; ChannelAmount < Channels.length; ++ChannelAmount) { //This makes sure that all Channels listed are joined as we stored it in a variable earlier.
		bot.joinChannel(Channels[ChannelAmount]); //Actually Joins the channels. 
  		}
	}
}