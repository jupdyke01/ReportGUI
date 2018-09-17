package me.jupdyke01.Main.CommandManager.Commands;

import org.bukkit.entity.Player;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;

public class CMDClearAll extends SubCommand{
	private Main plugin = Main.getInstance();

	public void ClearAllCommand(Player p) {
		for (String Name : Main.cfgm.getReports().getKeys(false)) { 
			Main.cfgm.getReports().set(Name, null);
			Main.cfgm.saveReports();
		}
		p.sendMessage(LangManager.AllReportsCleared);
		return;
	}

	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.clearall"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		if (args.length == 1) {
			ClearAllCommand(p);
		} else {
			p.sendMessage(LangManager.InvSyntax);
			return;
		}
	}

	@Override
	public String name() {
		return plugin.commandManager.clearall;
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
