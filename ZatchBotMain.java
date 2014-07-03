//Created by Sapein
//Zatchbot v 1.0 BETA
import org.jibble.pircbot.*;

public class ZatchBotMain {
  public static void main(String[] args) throws Exception {
    ZatchBot bot = new ZatchBot();      // this starts your bot
    bot.setVerbose(true);             // enable debugging, useful during programming
    //Esper
    //bot.connect("irc.esper.net");
    //bot.joinChannel("#ZatchCore");      // join your channel
    //bot.joinChannel("#testDenth");
    //bot.joinChannel("#wintreath");
    //Rizon
    bot.connect("irc.rizon.net");
    //bot.joinChannel("#schoolsurvival"); 
    bot.joinChannel("#ZatchTest");
  }
}