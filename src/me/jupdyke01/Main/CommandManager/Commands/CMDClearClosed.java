package me.jupdyke01.Main.CommandManager.Commands;

import org.bukkit.entity.Player;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;

public class CMDClearClosed extends SubCommand{
	private Main plugin = Main.getInstance();

	public void ClearClosedCommand(Player p) {
		for (String Name : Main.cfgm.getClosed().getKeys(false)) {
			Main.cfgm.getClosed().set(Name, null);
			Main.cfgm.saveClosed();
		}
		p.sendMessage(LangManager.ClosedCleared);
		return;
	}
	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.clearclosed"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		if (args.length == 1) {
			ClearClosedCommand(p);
		} else {
			p.sendMessage(LangManager.InvSyntax);
			return;
		}
	}
	@Override
	public String name() {

		return plugin.commandManager.clearclosed;
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
