package me.jupdyke01.Main.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;
import net.md_5.bungee.api.ChatColor;

public class OnJoinEvent implements Listener {

	int x = 0;

	@SuppressWarnings("unused")
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {

		Player p = e.getPlayer();


		if (p.hasPermission("reportgui.staff")) {
			for (String ReportUsers : Main.cfgm.getReports().getKeys(false)) {
				for (String Reports : Main.cfgm.getReports().getConfigurationSection(ReportUsers).getKeys(false)) {
					x++;
				}
			}
			p.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " There are: " + ChatColor.AQUA + x + ChatColor.GRAY + " opened reports right now!");
			p.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Use " + ChatColor.AQUA + "/reportgui reports" + ChatColor.GRAY + " to review them!");

		}
	}
}
