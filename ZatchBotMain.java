//Created by Sapein
//Zatchbot v 2.0 BETA

import java.io.*;
import org.jibble.pircbot.*;

public class ZatchBotMain {
	static String Server = "";
	static String Channel = "";

	public static void main(String[] arg) throws Exception {
		   BufferedReader saveFile=
		        new BufferedReader(new FileReader("Config.txt"));
		   // Throw away the blank line at the top.
		   saveFile.readLine(); 
		   // Get the integer value from the String.
		    Server = saveFile.readLine();
		    saveFile.readLine();
		    Channel = saveFile.readLine();
		    // Not needed, but read blank line at the bottom.
		    saveFile.readLine(); 
		    saveFile.close();
	//	    System.out.println(Server + "\n");
	//	    System.out.println(Channel + "\n");
	//	    System.out.println();
		    
    ZatchBot bot = new ZatchBot();      // this starts your bot
    bot.setVerbose(true);             // enable debugging, useful during programming
    bot.connect(Server);
    bot.joinChannel(Channel);
  }
}