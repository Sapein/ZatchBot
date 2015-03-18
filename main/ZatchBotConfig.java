package main;

import modules.ZatchBotLogging;

public class ZatchBotConfig {

	private String configVersion = "1.0";
	
	static protected boolean OpNickUsed;
	static protected boolean OpHostnameUsed;
	static protected String BotNick;
	static protected String Master;
	static protected String OpNick;
	static protected String OpHostname;
	static protected String commandInitializer; 
	/*
	 * This is mainly designed to return the config version. This may be deprecated in the future and is only really used 
	 * for getting the configVersion Variable. 
	 */
	public String  getConfigVersion(){
		return configVersion;
	}
	
	/*
	 * This simply checks to see if logging is active. 
	 */
	public void isLoggingActive(boolean x){
		ZatchBotLogging logging = new ZatchBotLogging();
		if(x == true){
			logging.setLoggingMode(true);
		}else{
			logging.setLoggingMode(false);
		}
	}
	
	public String getMaster(){
		return Master;
	}
	
	public String getBotNick(){
		return BotNick;
	}
	
	public String getOpHostname(){
		return OpHostname;
	}
	
	public String getCommandInit(){
		return commandInitializer;
	}
	
	public String getOpNick(){
		return OpNick;
	}
	
	public boolean getOpNickUsed(){
		return OpNickUsed;
	}
	public boolean getOpHostnameUsed(){
		return OpHostnameUsed;
	}
	
}
