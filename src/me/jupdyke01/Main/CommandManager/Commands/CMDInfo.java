package me.jupdyke01.Main.CommandManager.Commands;

import org.bukkit.entity.Player;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;
import net.md_5.bungee.api.ChatColor;

public class CMDInfo extends SubCommand{
	private Main plugin = Main.getInstance();

	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.info"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		p.sendMessage(ChatColor.AQUA + "-=-=-=-=-=" + ChatColor.LIGHT_PURPLE + LangManager.PluginTag + ChatColor.AQUA + "=-=-=-=-=-");
		p.sendMessage(ChatColor.GRAY + "Version: " + ChatColor.GOLD + Main.pl.getDescription().getVersion());
		p.sendMessage(ChatColor.GRAY + "Author: " + ChatColor.GOLD + "Jupdyke01");
		p.sendMessage(ChatColor.GRAY + "PluginName: " + ChatColor.GOLD + Main.pl.getDescription().getName());
	}

	@Override
	public String name() {
		
		return plugin.commandManager.info;
	}

	@Override
	public String info() {

		return "";
	}

	@Override
	public String[] aliases() {
		return new String[0];
	}
	
}
