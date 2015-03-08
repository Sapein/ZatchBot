package main;

public class ZatchBotConfig {

	private String configVersion = "1.0";
	
	protected boolean OpNickUsed;
	protected boolean OpHostnameUsed;
	protected String BotNick;
	protected String Master;
	protected String OpNick;
	protected String OpHostname;
	protected String commandInitializer; 
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
			boolean y = true;
			logging.setLoggingMode(y);
		}else{
			boolean y = false;
			logging.setLoggingMode(y);
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
