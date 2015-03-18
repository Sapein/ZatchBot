package modules;

import main.ZatchBot;


public class ZatchBotModuleHelp extends ZatchBotModule {

    @Override
    public void onMessage(ZatchBotMessage zatch) {
        //Begin Help Code
        //Help code stays on top
        if (zatch.getMessage().equalsIgnoreCase("&Help")){
            boolean sent = false;
            if (zatch.getSender().equals(zatch.getMaster())){
                zatch.getZatchBot().sendMessage(zatch.getSender(), "Hello, Master. Are you running me with an API and plugins?");
                sent = true;
            }
            for(int OpNumber = 0; OpNumber < zatch.getZatchBot().getOpNicks().length + 1; ++OpNumber) {
                if(!zatch.getZatchBot().isOpHostnameUsed() || (zatch.getZatchBot().getOpNicks().length > 0 && zatch.getSender().equalsIgnoreCase(zatch.getZatchBot().getOpNicks()[OpNumber]))){
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Sorry No plugins have been detected");
                    sent = true;
                }
            }
            if (!sent) {
                zatch.getZatchBot().sendMessage(zatch.getSender(), "NO PLUGINS DETECTED");
            }
        }
        //end Help Code
    }
}
