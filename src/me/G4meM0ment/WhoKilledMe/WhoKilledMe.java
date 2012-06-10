package me.G4meM0ment.WhoKilledMe;

import me.G4meM0ment.WhoKilledMe.EventListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class WhoKilledMe extends JavaPlugin {

	public static WhoKilledMe wkm;
	 
	@Override
	public void onEnable() {
	
	PluginDescriptionFile pdf = getDescription();
	wkm = this;
	new EventListener(this);
	
	//This creates the config, if its already created than it just load
	//the config and saves it then, needed to call them from the Config class
	this.getConfig().options().copyDefaults(true);
	this.saveConfig();
	
	//The info shown on server start
	this.getLogger().info("Successfully enabled!");
	this.getLogger().info("Version: " + pdf.getVersion());
	this.getLogger().info("By G4meM0ment");
	


	}
    
	//The reload command just calls the bukkit method reloadConfig()
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	 
		Player p = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("wkm") && args.length > 0 && args[0].equals("reload"))
		{
		 wkm.reloadConfig();			
		 p.sendMessage("WhoKilledMe reloaded!");
		 this.getLogger().info("Config reloaded!");
		 return true;
		}
		return false;
	}
	
	@Override
	public void onDisable()
	{
	this.getLogger().info("Disabled!");
	}
	
}
