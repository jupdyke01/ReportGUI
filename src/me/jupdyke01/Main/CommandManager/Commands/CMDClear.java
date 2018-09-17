package me.jupdyke01.Main.CommandManager.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;

public class CMDClear extends SubCommand{
	private Main plugin = Main.getInstance();

	public void ClearCommand(Player p, OfflinePlayer t) {
		if (Main.cfgm.getReports().contains(t.getName())) {
			Main.cfgm.getReports().set(t.getName(), null);
			p.sendMessage(LangManager.PReportsCleared.replace("%player%", t.getName()));
			Main.cfgm.saveReports();
			return;
		} else {
			p.sendMessage(LangManager.PNoReports.replace("%player%", t.getName()));
			return;
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.clear"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		if (args.length == 2) {
			Player t = Bukkit.getPlayer(args[1]);
			if (t != null) {
				ClearCommand(p, t);
				return;
			} else {
				OfflinePlayer to = Bukkit.getOfflinePlayer(args[1]);
				if (to == null || !to.hasPlayedBefore()) {
					p.sendMessage(LangManager.PlayerNull.replace("%player%", args[1]));
					return;
				} else {
					ClearCommand(p, to);
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
		return plugin.commandManager.clear;
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
