package modules;

import main.ZatchBot;

/**
 * Created by Jarada on 08/03/15.
 */
public class ZatchBotModuleHelp extends ZatchBotModule {

    @Override
    public void onMessage(ZatchBotMessage zatch) {
        //Begin Help Code
        //Help code stays on top

        if (zatch.getMessage().equalsIgnoreCase("&Help")){
            for(int OpNumber = 0; OpNumber < zatch.getZatchBot().getOpNicks().length; ++OpNumber) {
                if (zatch.getSender().equals(zatch.getMaster())){
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Hello, Master. Do you need a refresher?");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Well here are my commands:");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&join channel");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&leave channel");;
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Hello Zatch");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Goodnight.");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&pie");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&quit");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Goodbye");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&fishslap user");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&fish");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&code");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&date");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&time");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&x-chan");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&Op");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&leave");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&conn-start");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&conn-term");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&conn-add");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&conn-del");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&version");
                }
                if(!zatch.getZatchBot().isOpHostnameUsed() || zatch.getSender().equalsIgnoreCase(zatch.getZatchBot().getOpNicks()[OpNumber])){
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Hello " + zatch.getSender() + " I am " + zatch.getBotNick() + ". It seems that you have been designated as an Op"
                            + " by my master, " + zatch.getMaster() + " as such you are granted to see more commands");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Operator Commands");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&join");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&leave");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&conn-start");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&conn-term");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&conn-add");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&conn-del");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), " ");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Standard Commands");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), " ");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Hello Zatch");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Goodnight");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "I hate you Zatch");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&pie");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Goodbye");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&fishslap user");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&fish");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&code");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&date");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&time");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&x-chan");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&version");
                }
                else{
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Hello! I am " + zatch.getBotNick() + " a Zatch IRC bot! My Base was written by Sapein"
                            + "however, my master is " + zatch.getMaster() + ".");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Here are my Commands:");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Hello Zatch");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Goodnight");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "I hate you Zatch");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&pie");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "Goodbye");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&fishslap user");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&fish");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&code");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&date");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&time");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&x-chan");
                    zatch.getZatchBot().sendMessage(zatch.getSender(), "&version");
                }
            }
        }
        //end Help Code
    }
}
