package me.jupdyke01.Main.CommandManager.Commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;

public class CMDClose extends SubCommand{
	private Main plugin = Main.getInstance();

	@SuppressWarnings("unchecked")
	public void CloseCommand(Player p, OfflinePlayer t, String Name) {
		
		if (Main.cfgm.getReports().contains(t.getName() + "." + Name)) {
			
			String Reporter = Main.cfgm.getReports().getString(t.getName() + "." + Name + ".Reporter");
			String Time = Main.cfgm.getReports().getString(t.getName() + "." + Name + ".Time");
			String Reason = Main.cfgm.getReports().getString(t.getName() + "." + Name + ".Reason");
			List<String> Proof = (List<String>) Main.cfgm.getReports().getList(t.getName() + "." + Name + ".Proof");
			
			Main.cfgm.getReports().set(t.getName() + "." + Name, null);
			Main.cfgm.saveReports();
			
			int i = 0;
			for (int x = 0; x < Main.cfgm.getReports().getConfigurationSection(t.getName()).getKeys(false).size(); x++, i++) {}
			if (i > 0) {
				
			} else {
				Main.cfgm.getReports().set(t.getName(), null);
				Main.cfgm.saveReports();
			}
			
			long millis = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");    
			Date resultdate = new Date(millis);
			
			Main.cfgm.getClosed().set(t.getName() + "." + Name + ".Reporter", Reporter);
			Main.cfgm.getClosed().set(t.getName() + "." + Name + ".TimeClosed", sdf.format(resultdate));
			Main.cfgm.getClosed().set(t.getName() + "." + Name + ".Time", Time);
			Main.cfgm.getClosed().set(t.getName() + "." + Name + ".Reason", Reason);
			Main.cfgm.getClosed().set(t.getName() + "." + Name + ".Proof", Proof);
			Main.cfgm.saveClosed();
			
			String ReportClosed = LangManager.ReportClosed.replace("%player%", t.getName());
			p.sendMessage(ReportClosed.replace("%report_name%", Name));
			
		} else {
			String InvalidReport = LangManager.InvalidReport.replace("%player%", t.getName());
			p.sendMessage(InvalidReport.replace("%report_name%", Name));
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.close"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		if (args.length == 3) {
			Player t = Bukkit.getPlayer(args[1]);
			if (t != null) {
				CloseCommand(p, t, args[2]);
				return;
			} else {
				OfflinePlayer to = Bukkit.getOfflinePlayer(args[1]);
				if (to == null || !to.hasPlayedBefore()) {
					p.sendMessage(LangManager.PlayerNull.replace("%player%", args[1]));
					return;
				} else {
					CloseCommand(p, to, args[2]);
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
		return plugin.commandManager.close;
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
