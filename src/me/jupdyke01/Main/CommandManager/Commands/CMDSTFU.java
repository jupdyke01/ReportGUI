package me.jupdyke01.Main.CommandManager.Commands;

import org.bukkit.entity.Player;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;

public class CMDSTFU extends SubCommand{
	private Main plugin = Main.getInstance();

	public static void STFUCommand(Player p) {

		if (Main.STFU.contains(p)) {
			p.sendMessage(LangManager.STFUOff);
			Main.STFU.remove(p);
		} else {
			p.sendMessage(LangManager.STFUOn);
			Main.STFU.add(p);
		}

	}

	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.staff"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		if (args.length == 1) {
			STFUCommand(p);
		} else {
			p.sendMessage(LangManager.InvSyntax);
			return;
		}
		return;
	}

	@Override
	public String name() {
		return plugin.commandManager.stfu;
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
