//Created by Sapein
//Zatchbot v 1.0 BETA
import org.jibble.pircbot.*;

public class ZatchBotMain {
  public static void main(String[] args) throws Exception {
    ZatchBot bot = new ZatchBot();      // this starts your bot
    bot.setVerbose(true);             // enable debugging, useful during programming
    bot.connect("irc.esper.net");  // connect to your IRC server (fill in your own)
    //bot.joinChannel("#ZatchCore");      // join your channel
    //bot.joinChannel("#testDenth");
     bot.joinChannel("#wintreath");
    // bot.joinChannel("#schoolsurvival");
  }
}