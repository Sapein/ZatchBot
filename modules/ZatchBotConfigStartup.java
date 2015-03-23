package modules;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import main.ZatchBotConfig;


public class ZatchBotConfigStartup extends ZatchBotConfig{
	
	//Stage 1 startup Variables
	private String Channel;
	private String Server;
	private String[] Channels;
	
	/*
	 * As it's name implies this generates the config on the first run, or if the config file is deleted.
	 */
	public void generateConfig() throws Exception{
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
	
	/*
	 * This beings the loading process for the configuration file. Stage 1 is simply getting the Server and the Channels to
	 * connect to at first. This is then followed by Stage 2 afterwards. 
	 */
	public void loadConfigStage1() throws Exception{
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
		    Channels = Channel.split(",");
	}
	
	public void loadConfigState2() throws Exception{
		ZatchBotLogging logging = new ZatchBotLogging();
		
		String configVersion;
		BufferedReader saveFile;
		saveFile = new BufferedReader(new FileReader("Config.txt"));
		configVersion = saveFile.readLine(); //1st line;
		if(!configVersion.startsWith("Config Version")){
			saveFile.readLine(); //3nd line 
			saveFile.readLine(); //4rd line
		    saveFile.readLine(); //5th line
		    saveFile.readLine(); //6th line
		    saveFile.readLine(); //7th line
		    saveFile.readLine(); //8th line
		    saveFile.readLine(); //9th line
		    OpHostnameUsed = Boolean.parseBoolean(saveFile.readLine()); //10th line
		    saveFile.readLine(); //11th line
		    OpNickUsed = Boolean.parseBoolean(saveFile.readLine()); //12th line
		    saveFile.readLine(); //13th line
		    BotNick = saveFile.readLine(); //14th line
		    saveFile.readLine(); //15th line
		    Master = saveFile.readLine(); //16th line
		    saveFile.readLine(); //17th line
		    OpNick = saveFile.readLine(); //18th line
		    saveFile.readLine(); //19th line
		    OpHostname = saveFile.readLine(); //10h line 
		    saveFile.readLine(); //21th line 
		    boolean logsMode = Boolean.parseBoolean(saveFile.readLine()); //22st line
		    saveFile.readLine(); //23nd Line
		    if(logsMode == true){
		    	isLoggingActive(true);
		    	logging.setLoggingLocation(saveFile.readLine()); //24rd line
		    }
		    else{
		    	isLoggingActive(false);
		    	saveFile.readLine(); //24rd line
		    }
		    saveFile.close();
		}else{
			saveFile.readLine(); //2nd line
			saveFile.readLine(); //3nd line 
			saveFile.readLine(); //4rd line
		    saveFile.readLine(); //5th line
		    saveFile.readLine(); //6th line
		    saveFile.readLine(); //7th line
		    saveFile.readLine(); //8th line
		    saveFile.readLine(); //9th line
		    OpHostnameUsed = Boolean.parseBoolean(saveFile.readLine()); //10th line
		    saveFile.readLine(); //11th line
		    OpNickUsed = Boolean.parseBoolean(saveFile.readLine()); //12th line
		    saveFile.readLine(); //13th line
		    BotNick = saveFile.readLine(); //14th line
		    saveFile.readLine(); //15th line
		    Master = saveFile.readLine(); //16th line
		    saveFile.readLine(); //17th line
		    OpNick = saveFile.readLine(); //18th line
		    saveFile.readLine(); //19th line
		    OpHostname = saveFile.readLine(); //10h line 
		    saveFile.readLine(); //21th line 
		    boolean logsMode = Boolean.parseBoolean(saveFile.readLine()); //22st line
		    saveFile.readLine(); //23nd Line
		    if(logsMode == true){
		    	isLoggingActive(true);
		    	logging.setLoggingLocation(saveFile.readLine()); //24rd line
		    }
		    else{
		    	isLoggingActive(false);
		    	saveFile.readLine(); //24rd line
		    }
		    saveFile.close();
		}
	}
	
	public String getServer(){
		return Server;
	}
	
	public String getChannel(){
		return Channel;
	}
	
	public String[] getChannels(){
		return Channels;
	}
}