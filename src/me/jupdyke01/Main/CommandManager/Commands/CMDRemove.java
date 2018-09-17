package me.jupdyke01.Main.CommandManager.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;

public class CMDRemove extends SubCommand{
	private Main plugin = Main.getInstance();
	
	public void RemoveCommand(Player p, OfflinePlayer t, String Name) {
		if (t == null) {
			return;
		}
		if (!(Main.cfgm.getReports().contains(t.getName()))) {
			p.sendMessage(LangManager.PNoReports.replace("%player%", t.getName()));
			return;
		} else {
			if (!(Main.cfgm.getReports().contains(t.getName() + "." + Name))) {
				p.sendMessage(LangManager.InvalidReport.replace("%player%", t.getName()));
				return;
			} else {
				Main.cfgm.getReports().set(t.getName() + "." + Name, null);
				Main.cfgm.saveReports();
				String ReportRemoved = LangManager.ReportRemoved.replace("%player%", t.getName());
				p.sendMessage(ReportRemoved.replace("%report_name%", Name));
				int i = 0;
				for (int x = 0; x < Main.cfgm.getReports().getConfigurationSection(t.getName()).getKeys(false).size(); x++, i++) {}
				if (i > 0) {
					return;
				} else {
					Main.cfgm.getReports().set(t.getName(), null);
					Main.cfgm.saveReports();
					return;
				}
			}
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.remove"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		if (args.length == 3) {
			Player t = Bukkit.getPlayer(args[1]);
			if (t != null) {
				RemoveCommand(p, t, args[2]);
				return;
			} else {
				OfflinePlayer to = Bukkit.getOfflinePlayer(args[1]);
				if (to == null || !to.hasPlayedBefore()) {
					p.sendMessage(LangManager.PlayerNull.replace("%player%", args[1]));
					return;
				} else {
					RemoveCommand(p, to, args[2]);
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
		return plugin.commandManager.remove;
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
