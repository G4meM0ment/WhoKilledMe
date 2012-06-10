package me.G4meM0ment.WhoKilledMe;
	
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;


	public class EventListener implements org.bukkit.event.Listener {
		
		public static HashMap<Player, Player> data = new HashMap<Player, Player>();		
		
		WhoKilledMe wkm;
		public EventListener(WhoKilledMe WhoKilledMe) {
			
		Bukkit.getServer().getPluginManager().registerEvents(this, WhoKilledMe);
		wkm = WhoKilledMe;
		}
		
		//The Listener checks if a player damages
		//if true it will puts the player into the hashmap
		@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
		public void onEntityDamage(EntityDamageEvent event) {
			
				if(!(event.getEntity() instanceof Player)) return;
				if(!(event instanceof EntityDamageByEntityEvent)){
					data.put((Player) event.getEntity(), null);	
					return;
				}					
		
		    EntityDamageByEntityEvent newevent = (EntityDamageByEntityEvent) event;
		    //here is checked which type of attack is used the player which has
		    // been found will be added to the hashmap
		    if(event.getCause().toString().equals("ENTITY_ATTACK")){
		    	if(newevent.getDamager() instanceof Player){
					Player pvper = (Player) newevent.getDamager();
					data.put((Player) event.getEntity(), pvper);}
		        else if(newevent.getDamager() instanceof Arrow){
		    	Player pvper = (Player) ((Arrow) newevent.getDamager()).getShooter();
		    	data.put((Player) event.getEntity(), pvper);
		    	}
				else if(newevent.getDamager() instanceof Monster){
				}
				else
					data.put((Player) event.getEntity(), null);
		        }
		   }
		
		//This listener definetly defines which player got killed and which one killed him/her
		@EventHandler(priority = EventPriority.NORMAL)
		public void onEntityDeath(EntityDeathEvent event) {				
	            //This disables the vanilla messages
				boolean disableVanilla = wkm.getConfig().getBoolean("disableVanillaMessages");

				if(event instanceof PlayerDeathEvent && disableVanilla == true)
                  {
                    PlayerDeathEvent e = (PlayerDeathEvent) event;
                    e.setDeathMessage(null);
                  }
				if(!(event.getEntity() instanceof Player)) return;
				if(event.getEntity() instanceof Monster) return;
				if(data.get((Player)event.getEntity()) == null) return;

			//defines the players, and overgives them to the messenger
			Player player = (Player)event.getEntity();
	        Player pvper = data.get(player);
	        boolean msgDisplay = true;
	        Messenger.sendMessage(player, pvper, msgDisplay);
		}		
}