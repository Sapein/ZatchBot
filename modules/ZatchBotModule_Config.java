package modules;

import main.ZatchBot;

public class ZatchBotModule_Config extends ZatchBotModule{
	
	public static void startUp(int initStage){
		ZatchBotModuleConfigStartup ConfigStartup = new ZatchBotModuleConfigStartup();
		switch (initStage){
			case 0: 
				try {
					ConfigStartup.generateConfig();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 1:
				try {
					ConfigStartup.loadConfigStage1();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					ConfigStartup.loadConfigStage2();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("No init stage given...aborting...");
		}
	}
	
	
	
	/*
	 * This allows us to use commands to reload or update the configs.
	 * @see modules.ZatchBotModule#onMessage(modules.ZatchBotMessage)
	 */
	public void onMessage(ZatchBotMessage zatch) {
		ZatchBotModuleConfigCommands ConfigCommands = new ZatchBotModuleConfigCommands(); 
		if(zatch.getMessage().equalsIgnoreCase("&reloadConfigs")){
			if(zatch.getSender().equals(zatch.getMaster())){
				zatch.getZatchBot().sendMessage(zatch.getChannel(), "RELOADING CONFIGS");
				try {
					ConfigCommands.loadConfig(zatch.getChannel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				zatch.getZatchBot().sendMessage(zatch.getChannel(), "CONFIGS RELOADED");
			}else{
				zatch.getZatchBot().sendMessage(zatch.getChannel(), "You are not authorized to issue this command!");
			}
		}
		else if(zatch.getMessage().equalsIgnoreCase("&updateConfigs")){
			if(zatch.getSender().equals(zatch.getMaster())){
				zatch.getZatchBot().sendMessage(zatch.getChannel(), "RELOADING UPDATED");
				try {
					ConfigCommands.updateConfig(zatch.getChannel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				zatch.getZatchBot().sendMessage(zatch.getChannel(), "CONFIGS UPDATED");
			}else{
				zatch.getZatchBot().sendMessage(zatch.getChannel(), "You are not authorized to issue this command!");
			}
		}
	}

}
