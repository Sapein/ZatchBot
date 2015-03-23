package modules;

import main.ZatchBot;

/**
 * Created by Jarada on 08/03/15.
 */
public class ZatchBotMessage {

    private ZatchBot zatchBot;
    private ZatchBot_Config config;
    private ZatchBotConfigCommands commands;
    private String botNick;
    private String master;

    private String channel;
    private String sender;
    private String login;
    private String hostname;
    private String message;

    public ZatchBotMessage(ZatchBot zatchBot, ZatchBot_Config config, ZatchBotConfigCommands commands, String botNick, String master,
                           String channel, String sender, String login, String hostname, String message) {
        this.zatchBot = zatchBot;
        this.config = config;
        this.commands = commands;
        this.botNick = botNick;
        this.master = master;
        this.channel = channel;
        this.sender = sender;
        this.login = login;
        this.hostname = hostname;
        this.message = message;
    }

    public ZatchBot getZatchBot() {
        return zatchBot;
    }

    public void setZatchBot(ZatchBot zatchBot) {
        this.zatchBot = zatchBot;
    }

    public ZatchBot_Config getConfig() {
        return config;
    }

    public void setConfig(ZatchBot_Config config) {
        this.config = config;
    }

    public ZatchBotConfigCommands getCommands() {
        return commands;
    }

    public void setCommands(ZatchBotConfigCommands commands) {
        this.commands = commands;
    }

    public String getBotNick() {
        return botNick;
    }

    public void setBotNick(String botNick) {
        this.botNick = botNick;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}