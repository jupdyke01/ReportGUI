package me.jupdyke01.Main.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.jupdyke01.ReportGUI.Main;

public class OnKillEvent implements Listener{

	@EventHandler
	public void PlayerKill(PlayerDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			Player killer = (Player) e.getEntity().getKiller();
			Player killed = e.getEntity();
			
			if (Main.Killed.containsKey(killed) && Main.Kill.containsKey(killer)) {
				Main.Killed.remove(killed);
				Main.Killed.put(killed, killer);
				Main.Kill.remove(killer);
				Main.Kill.put(killer, killed);
				return;
			} else if (Main.Killed.containsKey(killed) && !(Main.Kill.containsKey(killer))) {
				Main.Killed.remove(killed);
				Main.Killed.put(killed, killer);
				Main.Kill.put(killer, killed);
				return;
			} else if (!(Main.Killed.containsKey(killed)) && (Main.Kill.containsKey(killer))) {
				Main.Killed.put(killed, killer);
				Main.Kill.remove(killer);
				Main.Kill.put(killer, killed);
				return;
			} else if (!(Main.Killed.containsKey(killed)) && !(Main.Kill.containsKey(killer))) {
				Main.Killed.put(killed, killer);
				Main.Kill.put(killer, killed);
				return;
			}
			
		}
		
		return;
	}
}
