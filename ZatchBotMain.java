//Created by Sapein
//Zatchbot v 2.0 BETA

import java.io.*;

import org.jibble.pircbot.*;

public class ZatchBotMain {
	static String Server = ""; //This will be replaced by what is inputed in the Config upon start. This is the IRC server 
	static String Channel = ""; //This will be replaced by what is inputted in the Channel Line upon Start. These are the channels to Auto-Connect to. 
	

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
		bw.write("== To add Multipul Channels use a comma(,) inbetween with no spaces ==" + "\r\n"); //puts this on the fourth line of the file
		bw.write("#SchoolSurvival" + "\r\n"); //puts this on the fifth line of the file
		bw.write("		==--IRC Bot--==" + "\r\n"); //creates the final line
		bw.write("--Bot Name--" +"\r\n");
		bw.write("Zatch" + "\r\n");
		bw.write("--Master--" + "\r\n");
		bw.write("Chanku" + "\r\n");
		bw.write("--Op Nicks--" + "\r\n");
		bw.write(""  + "\r\n");
		bw.write("--Op Hostnames" + "\r\n");
		bw.write("" + "\r\n");
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