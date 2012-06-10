package me.G4meM0ment.WhoKilledMe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messenger {
	
	static WhoKilledMe wkm;

	public static void sendMessage(Player pvper, Player player, boolean msgDisplay) {
		
	   if(msgDisplay) {
		
		//Gets the path from the config
		String Attacker = wkm.getConfig().getString("KilledMessage");
		String Dead = wkm.getConfig().getString("KilledByMessage");
		String broadcastMessage = wkm.getConfig().getString("BroadcastMessage");
		Boolean broadcast = wkm.getConfig().getBoolean("ServerMessage");
		
		//Parse %d to the name of the player which gets killed and %a to the killer
		Attacker = Attacker.replace("%d", pvper.getName());
        Dead = Dead.replace("%d", player.getName());
        
        Attacker = Attacker.replace("%a", pvper.getName());
        Dead = Dead.replace("%a", player.getName());
        
        //Sends the message if the player have the permission
        if(player.hasPermission("wkm.use") || player.hasPermission("wkm.victim") || player.isOp())
        player.sendMessage(parseColors(Attacker));
        
        if(pvper.hasPermission("wkm.use") || pvper.hasPermission("wkm.killer") || pvper.isOp())
        pvper.sendMessage(parseColors(Dead));
        
        //sends the broadcast message
        if(broadcast)
        {
          broadcastMessage = broadcastMessage.replace("%d", pvper.getName());
          broadcastMessage = broadcastMessage.replace("%a", player.getName());
          Bukkit.getServer().broadcastMessage(broadcastMessage);
        }
	   }
	}
	   
		//This parses the colors to useful numbers in the config
		public static String parseColors(String message){
			message = message.replace("&4", ChatColor.DARK_RED.toString());
			message = message.replace("&c", ChatColor.RED.toString());
			message = message.replace("&e", ChatColor.YELLOW.toString());
			message = message.replace("&6", ChatColor.GOLD.toString());
			message = message.replace("&2", ChatColor.DARK_GREEN.toString());
			message = message.replace("&a", ChatColor.GREEN.toString());
			message = message.replace("&b", ChatColor.AQUA.toString());
			message = message.replace("&3", ChatColor.DARK_AQUA.toString());
			message = message.replace("&1", ChatColor.DARK_BLUE.toString());
			message = message.replace("&9", ChatColor.BLUE.toString());
			message = message.replace("&d", ChatColor.LIGHT_PURPLE.toString());
			message = message.replace("&5", ChatColor.DARK_PURPLE.toString());
			message = message.replace("&f", ChatColor.WHITE.toString());
			message = message.replace("&7", ChatColor.GRAY.toString());
			message = message.replace("&8", ChatColor.DARK_GRAY.toString());
			message = message.replace("&0", ChatColor.BLACK.toString());
			return message; 
		}
}
