package me.jupdyke01.Main.CommandManager.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;

public class CMDOpen extends SubCommand{
	private Main plugin = Main.getInstance();
	
	@SuppressWarnings("unchecked")
	public void OpenCommand(Player p, OfflinePlayer t, String Name) {
		
		if (Main.cfgm.getClosed().contains(t.getName() + "." + Name)) {
			
			
			String Reporter = Main.cfgm.getClosed().getString(t.getName() + "." + Name + ".Reporter");
			String Time = Main.cfgm.getClosed().getString(t.getName() + "." + Name + ".Time");
			String Reason = Main.cfgm.getClosed().getString(t.getName() + "." + Name + ".Reason");
			List<String> Proof = (List<String>) Main.cfgm.getClosed().getList(t.getName() + "." + Name + ".Proof");
			
			Main.cfgm.getClosed().set(t.getName() + "." + Name, null);
			Main.cfgm.saveClosed();
			
			int i = 0;
			for (int x = 0; x < Main.cfgm.getClosed().getConfigurationSection(t.getName()).getKeys(false).size(); x++, i++) {}
			if (i > 0) {
				
			} else {
				Main.cfgm.getClosed().set(t.getName(), null);
				Main.cfgm.saveClosed();
			}
			
			Main.cfgm.getReports().set(t.getName() + "." + Name + ".Reporter", Reporter);
			Main.cfgm.getReports().set(t.getName() + "." + Name + ".Time", Time);
			Main.cfgm.getReports().set(t.getName() + "." + Name + ".Reason", Reason);
			Main.cfgm.getReports().set(t.getName() + "." + Name + ".Proof", Proof);
			Main.cfgm.saveReports();
			
			String ReportReopened = LangManager.ReportReopened.replace("%player%", t.getName());
			p.sendMessage(ReportReopened.replace("%report_name%", Name));
			
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.open"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		if (args.length == 3) {
			Player t = Bukkit.getPlayer(args[1]);
			if (t != null) {
				OpenCommand(p, t, args[2]);
				return;
			} else {
				OfflinePlayer to = Bukkit.getOfflinePlayer(args[1]);
				if (to == null || !to.hasPlayedBefore()) {
					p.sendMessage(LangManager.PlayerNull.replace("%player%", args[1]));
					return;
				} else {
					OpenCommand(p, to, args[2]);
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
		return plugin.commandManager.open;
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
