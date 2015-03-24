package modules;

import main.ZatchBot;

public class ZatchBotModule_Config extends ZatchBotModule{

	public void onMessage(ZatchBotMessage zatch) {
		ZatchBotModuleConfigCommands ConfigCommands = new ZatchBotModuleConfigCommands();
		if(zatch.getMessage().equalsIgnoreCase("&reloadConfigs")){
			zatch.getSender().equals(zatch.getMaster());
			zatch.getZatchBot().sendMessage(zatch.getChannel(), "RELOADING CONFIGS");
			try {
				ConfigCommands.loadConfig(zatch.getChannel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			zatch.getZatchBot().sendMessage(zatch.getChannel(), "CONFIGS RELOADED");
		}
		else if(zatch.getMessage().equalsIgnoreCase("&updateConfigs")){
			zatch.getZatchBot().sendMessage(zatch.getChannel(), "UPDATING CONFIGS");
			try {
				ConfigCommands.updateConfig(zatch.getChannel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
