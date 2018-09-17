package me.jupdyke01.Main.CommandManager.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;

public class CMDHelp extends SubCommand{
	private Main plugin = Main.getInstance();
	
	public void HelpCommand(Player p) {
		
		p.sendMessage(ChatColor.AQUA + "---===" + ChatColor.LIGHT_PURPLE + "ReportGUI by Jupdyke01" + ChatColor.AQUA + "===---");
		p.sendMessage(ChatColor.GRAY + "Optional Argument: []");
		p.sendMessage(ChatColor.GRAY + "Mandatory Argument: <>");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " help");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " info");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " reports");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " check/inspect <player>");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " report [player]");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " remove/delete <Player> <ReportName>");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " clear <player>");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " close <player> <ReportName>");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " open <player> <ReportName>");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " clearclosed");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " clearall");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " look <player>");
		p.sendMessage(ChatColor.GRAY + "ReportGUI" + ChatColor.GOLD + " STFU");
		p.sendMessage(ChatColor.AQUA + "---===========================---");
		
	}

	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.help"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		HelpCommand(p);
	}

	@Override
	public String name() {
		return plugin.commandManager.help;
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
