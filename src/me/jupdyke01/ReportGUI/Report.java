package me.jupdyke01.ReportGUI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.jupdyke01.Main.Utils.LangManager;
import net.md_5.bungee.api.ChatColor;

public class Report {

	@SuppressWarnings("unused")
	private Main main;
	private Player accuser;
	private OfflinePlayer accused;
	private String name;
	private String reason;
	private List<String> proof = new ArrayList<>();

	public Report(Main main, Player accuser, OfflinePlayer accused) {
		this.main = main;
		this.accused = accused;
		this.accuser  = accuser;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public void addProof(String str) {
		proof.add(str);
	}
	
	public OfflinePlayer getAccused() {
		return accused;
	}
	
	public Player getAccuser() {
		return accuser;
	}
	
	public String getName() {
		return name;
	}
	
	public String getReason() {
		return reason;
	}
	
	public List<String> getProof() {
		return proof;
	}
	
	public void Submit() {
		long millis = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
		Date resultdate = new Date(millis);
		if (Main.cfgm.getReports().contains(accused.getName() + "." + name)) {
			accuser.sendMessage(LangManager.PluginTag + ChatColor.RED + " There is already a report by the name of: " + ChatColor.GOLD + name + ChatColor.RED + " on player: " + ChatColor.GOLD + accused);
			return;
		}
		ConfigurationSection section =Main.cfgm.getReports().createSection(accused.getName() + "." + name);
		section.set("Reporter", accuser.getName());
		section.set("Time", sdf.format(resultdate));
		section.set("Reason", reason);
		if (!(proof.isEmpty())) {
			section.set("Proof" , proof);
		} else {
			proof.add("No Proof");
			section.set("Proof" , proof);
		}
		
		Main.cfgm.saveReports();
		Main.getInstance().getCommandManager().getCMDReport().reports.remove(accuser.getUniqueId());
		
		return;
	}
}
