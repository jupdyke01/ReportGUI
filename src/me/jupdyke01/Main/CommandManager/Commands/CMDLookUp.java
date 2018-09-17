package me.jupdyke01.Main.CommandManager.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;
import net.md_5.bungee.api.ChatColor;

public class CMDLookUp extends SubCommand{
	private Main plugin = Main.getInstance();

	@SuppressWarnings("unused")
	public int getOpenReports(OfflinePlayer p) {
		int x = 0;
		if (Main.cfgm.getReports().contains(p.getName())) {
			for (String Reports : Main.cfgm.getReports().getConfigurationSection(p.getName()).getKeys(false)) {
				x++;
			}
		}
		return x;
	}

	@SuppressWarnings("unused")
	public int getClosedReports(OfflinePlayer p) {
		int x = 0;
		if (Main.cfgm.getClosed().contains(p.getName())) {
			for (String Reports : Main.cfgm.getClosed().getConfigurationSection(p.getName()).getKeys(false)) {
				x++;
			}
		}
		return x;
	}

	public int getMadeClosedReports(OfflinePlayer p) {
		int x = 0;

		for (String ReportUsers : Main.cfgm.getClosed().getKeys(false)) {
			for (String Reports : Main.cfgm.getClosed().getConfigurationSection(ReportUsers).getKeys(false)) {
				if (Main.cfgm.getClosed().get(ReportUsers + "." + Reports + ".Reporter") == p.getName()) {
					x++;
				}
			}
		}

		return x;
	}
	
	public int getMadeOpenReports(OfflinePlayer p) {
		int x = 0;

		for (String ReportUsers : Main.cfgm.getReports().getKeys(false)) {
			for (String Reports : Main.cfgm.getReports().getConfigurationSection(ReportUsers).getKeys(false)) {
				if (Main.cfgm.getReports().get(ReportUsers + "." + Reports + ".Reporter") == p.getName()) {
					x++;
				}
			}
		}

		return x;
	}


	public void LookCommand(Player p, OfflinePlayer t) {

		p.sendMessage(ChatColor.GRAY + "Player: " + ChatColor.AQUA + t.getName());
		p.sendMessage(ChatColor.GRAY + "----" + ChatColor.AQUA + "Reports On This Player" + ChatColor.GRAY + "----");
		p.sendMessage(ChatColor.GRAY + "Open Reports: " + ChatColor.AQUA + getOpenReports(t));
		p.sendMessage(ChatColor.GRAY + "Closed Reports: " + ChatColor.AQUA + getClosedReports(t));
		p.sendMessage(ChatColor.GRAY + "---" + ChatColor.AQUA + "Reports This Player Made" + ChatColor.GRAY + "---");
		p.sendMessage(ChatColor.GRAY + "Open Reports: " + ChatColor.AQUA + getMadeOpenReports(t));
		p.sendMessage(ChatColor.GRAY + "Closed Reports: " + ChatColor.AQUA + getMadeClosedReports(t));
		p.sendMessage(ChatColor.GRAY + "---------------------------");

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.check"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		if (args.length == 2) {
			Player t = Bukkit.getPlayer(args[1]);
			if (t != null) {
				LookCommand(p, t);
				return;
			} else {
				OfflinePlayer to = Bukkit.getOfflinePlayer(args[1]);
				if (to == null || !to.hasPlayedBefore()) {
					p.sendMessage(LangManager.PlayerNull.replace("%player%", args[1]));
					return;
				} else {
					LookCommand(p, to);
					return;
				}
			}
		} else {
			p.sendMessage(LangManager.InvSyntax);
			return;
		}
	}

	@Override
	public String name() {

		return plugin.commandManager.lookup;
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
