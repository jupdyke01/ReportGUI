package me.jupdyke01.Main.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.jupdyke01.ReportGUI.Main;

public class OnHitEvent implements Listener{

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player))) {
			Player damager = (Player) e.getDamager();
			Player damaged = (Player) e.getEntity();
			
			if (Main.HitBy.containsKey(damaged) && Main.Hit.containsKey(damager)) {
				Main.HitBy.remove(damaged);
				Main.HitBy.put(damaged, damager);
				Main.Hit.remove(damager);
				Main.Hit.put(damager, damaged);
				return;
			} else if (Main.HitBy.containsKey(damaged) && !(Main.Hit.containsKey(damager))) {
				Main.HitBy.remove(damaged);
				Main.HitBy.put(damaged, damager);
				Main.Hit.put(damager, damaged);
				return;
			} else if (!(Main.HitBy.containsKey(damaged)) && (Main.Hit.containsKey(damager))) {
				Main.HitBy.put(damaged, damager);
				Main.Hit.remove(damager);
				Main.Hit.put(damager, damaged);
				return;
			} else if (!(Main.Killed.containsKey(damaged)) && !(Main.Hit.containsKey(damager))) {
				Main.HitBy.put(damaged, damager);
				Main.Hit.put(damager, damaged);
				return;
			}
			
		}
	}
}

