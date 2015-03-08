import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.jibble.pircbot.PircBot;


public class ZatchBotConfigCommands extends ZatchBotConfig{
	/*ZatchBotConfig Config = new ZatchBotConfig();
	private boolean OpHostnameUsed = Config.getOpHostnameUsed();
	private boolean OpNickUsed = Config.getOpNickUsed();
	private String BotNick = Config.getBotNick();
	private String Master = Config.getMaster();
	private String OpNick = Config.getOpNick();
	private String OpHostname = Config.getOpHostname();*/
	
	
	/*
	 * This handles loading and reloading of the config file. Please note that the config version return will be moved from 
	 * this function and given it's own function as loadConfig is mainly supposed to load/reload the config. 
	 */
	protected String loadConfig(String chan) throws Exception{
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
		    boolean logsMode = Boolean.parseBoolean(saveFile.readLine()); //22st line
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
	    return configVersion;
	}
	
	/*
	 * It updates the config to the latest version, this allows for the bot to check and make sure that the latest
	 * config is up-to-date. It also allows for me to make changes to the config file and not worry about breaking things
	 * or having to have users update the config themselves and have something mess up.
	 */
	public void updateConfig(String chan) throws Exception{
		ZatchBotLogging logging = new ZatchBotLogging();
		ZatchBotConfigStartup ConfigStartup = new ZatchBotConfigStartup();
		
		String Server = ConfigStartup.getServer();
		String Channel = ConfigStartup.getChannel(); 
		
		String versionCheck = loadConfig("");
		File file = new File("Config.txt");
		File oldFile = new File("Config-Backup.txt");
		if(!versionCheck.equalsIgnoreCase("Config Version: " )){
				file.renameTo(oldFile);
				file.createNewFile(); //creates the file
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Config Version: " + "\r\n"); 
				bw.write("		=--Connection--=" +"\r\n");
				bw.write("--IRC Server--" + "\r\n"); //puts this on the first line of the file
				bw.write(Server + "\r\n"); //puts this on the second line of the file
				bw.write("--Channels--" + "\r\n"); //puts this on the third line of the file
				bw.write("== To add Multiple Channels use a comma(,) inbetween with no spaces ==" + "\r\n"); //puts this on the fourth line of the file
				bw.write(Channel + "\r\n"); //puts this on the fifth line of the file
				bw.write("		==--IRC Bot--==" + "\r\n"); //creates the sixth line of the file
				bw.write("--Toggle Hostname Verification for Ops--" + "\r\n"); //puts this on the Seventh line of the file
				bw.write(OpHostnameUsed + "\r\n"); //makes Hostname Verification False by default
				bw.write("--Toggle Nick Verification for Ops--" + "\r\n"); //puts this on the Ninth line of the file
				bw.write(OpNickUsed + "\r\n"); //Sets the Nick verification to true
				bw.write("--Bot Name--" +"\r\n"); //puts this on the Eleventh line of the file
				bw.write(BotNick + "\r\n"); //puts this on the twelfth line of the file
				bw.write("--Master--" + "\r\n"); //puts this on the thirteenth line of the file
				bw.write(Master + "\r\n"); //puts this on the fourteenth line of the file
				bw.write("--Op Nicks--" + "\r\n"); //puts this on the fifteenth line of the file
				bw.write(OpNick + "\r\n"); //generates a blank space
				bw.write("--Op Hostnames" + "\r\n"); //puts this on the Seventeenth line of the file
				bw.write(OpHostname + "\r\n"); //generates blank space on the Eighteenth line of the file
				bw.write("--Toggle Logs" +"\r\n"); 
				bw.write(logging.getLoggingMode() + "\r\n");
				bw.write("--Logs Location--" + "\r\n");
				bw.write(logging.getLoggingLocation() + "\r\n");
				bw.close(); //closes the writer. 
		}else{
		}
		loadConfig("");
	}
}
