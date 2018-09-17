package me.jupdyke01.Main.Utils;

import me.jupdyke01.ReportGUI.Main;
import net.md_5.bungee.api.ChatColor;

public class LangManager {
	
	public static String PluginTag = ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("PluginTag"));
	public static String NoPerm = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("NoPerm"));
	public static String InvSyntax = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("InvSyntax"));
	public static String PlayerNull = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("PlayerNull"));
	public static String PNoReports = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("PNoReports"));
	public static String PReportsCleared = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("PReportsCleared"));
	public static String AllReportsCleared = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("AllReportsCleared"));
	public static String ClosedCleared = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("ClosedCleared"));
	public static String ReportClosed = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("ReportClosed"));
	public static String InvalidReport = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("InvalidReport"));
	public static String ReportReopened = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("ReportReopened"));
	public static String ReportRemoved = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("ReportRemoved"));
	public static String CancelReport = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("CancelReport"));
	public static String ReportSelf = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("ReportSelf"));
	public static String NoPKilled = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("NoPKilled"));
	public static String NoPKill = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("NoPKill"));
	public static String NoPHit = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("NoPHit"));
	public static String NoPHitBy = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("NoPHitBy"));
	public static String STFUOff = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("STFUOff"));
	public static String STFUOn = PluginTag + " " + ChatColor.translateAlternateColorCodes('&', Main.getPlugin(Main.class).getLang().getString("STFUOn"));

}
