import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class ZatchBotConfigStartupOld extends ZatchBotConfig{
	static String Server; //This will be replaced by what is inputed in the Config upon start. This is the IRC server 
	static String Channel; //This will be replaced by what is inputted in the Channel Line upon Start. These are the channels to Auto-Connect to.
	static String[] Channels; 
	public void createConfig() throws Exception{
		File file = new File("Config.txt"); //sets the file name
	
		// NOTE: That the defaults set here are to prevent the bot from erroring out upon start-up, your changes will not be overwritten.
		if (!file.exists()) { //checks to see if it exists, if not it writes this
			file.createNewFile(); //creates the file

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Config Version: " + getConfigVersion()+  "\r\n"); //This line is important as it's used to check the config version.
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
	}
	public void loadBasics() throws Exception{
		String conVersion;
		BufferedReader saveFile= new BufferedReader(new FileReader("Config.txt"));
		   // Get the integer value from the String.
		conVersion = saveFile.readLine();
		if(!conVersion.startsWith("Config Version")){
			saveFile.readLine();
			Server = saveFile.readLine();
		    saveFile.readLine(); 
		    saveFile.readLine(); 
		    Channel = saveFile.readLine();
		    // Not needed, but read blank line at the bottom.
		    saveFile.close();
		}else{
			saveFile.readLine();
			saveFile.readLine();
			Server = saveFile.readLine();
		    saveFile.readLine(); 
		    saveFile.readLine(); 
		    Channel = saveFile.readLine();
		    // Not needed, but read blank line at the bottom.
		    saveFile.close();
		}
		    String[] Channels = Channel.split(",");

	}
}
