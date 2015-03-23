package main;

/*A Simple IRC bot that uses PIRC
 Copyright (C) 2014  

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along
 with this program; if not, write to the Free Software Foundation, Inc.,
 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
//Created by Sapein
//Zatchbot v 1.0

import modules.ZatchBotConfigStartup;

public class ZatchBotMain {

	public static void main(String[] arg) throws Exception {
		ZatchBotConfigStartup ConfigStartup = new ZatchBotConfigStartup();

		ConfigStartup.generateConfig();
		ConfigStartup.loadConfigStage1();
		String Server = ConfigStartup.getServer();
		String[] Channels = ConfigStartup.getChannels();

		ZatchBot bot = new ZatchBot(); // this starts your bot
		bot.setVerbose(true); // enable debugging, useful during programming
		bot.connect(Server); // Connect to the Server(The Server Variable is
								// called here)
		for (int ChannelAmount = 0; ChannelAmount < Channels.length; ++ChannelAmount) { // This
																						// makes
																						// sure
																						// that
																						// all
																						// Channels
																						// listed
																						// are
																						// joined
																						// as
																						// we
																						// stored
																						// it
																						// in
																						// a
																						// variable
																						// earlier.
			bot.joinChannel(Channels[ChannelAmount]); // Actually Joins the
														// channels.
		}
	}
}
