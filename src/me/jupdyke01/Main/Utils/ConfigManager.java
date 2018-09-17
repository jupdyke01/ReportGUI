package me.jupdyke01.Main.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.jupdyke01.ReportGUI.Main;

public class ConfigManager {
	private Main plugin = Main.getPlugin(Main.class);

	String PluginTag = LangManager.PluginTag;

	public FileConfiguration configcfg;
	public File config;
	public FileConfiguration reportscfg;
	public File reports;
	public FileConfiguration closedcfg;
	public File closed;

	public void setup() {
		if (!(plugin.getDataFolder().exists())) {
			plugin.getDataFolder().mkdir();
		}

		config = new File(plugin.getDataFolder(), "config.yml");
		reports = new File(plugin.getDataFolder(), "reports.yml");
		closed = new File(plugin.getDataFolder(), "closed.yml");
		
		if(!(config.exists())) {
			try {
				config.createNewFile();
			} catch(IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(PluginTag + ChatColor.RED + " The config file for PWarps could not be loaded!");
			}
		}

		if(!(reports.exists())) {
			try {
				reports.createNewFile();
			} catch(IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(PluginTag + ChatColor.RED + " The reports file for PWarps could not be loaded!");
			}
		}
		
		if(!(closed.exists())) {
			try {
				closed.createNewFile();
			} catch(IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(PluginTag + ChatColor.RED + " The closed file for PWarps could not be loaded!");
			}
		}

		configcfg = YamlConfiguration.loadConfiguration(config);
		reportscfg = YamlConfiguration.loadConfiguration(reports);
		closedcfg = YamlConfiguration.loadConfiguration(closed);
	}

	// CONFIG
	public FileConfiguration getConfig() {
		return configcfg;
	}

	public void saveConfig() {
		try {
			configcfg.save(config);
		} catch(IOException e) {

		}
	}
	public void reloadConfig() {
		configcfg = YamlConfiguration.loadConfiguration(config);
	}

	// Reports
	public FileConfiguration getReports() {
		return reportscfg;
	}
	
	public void saveReports() {
		try {
			reportscfg.save(reports);
		} catch (IOException e) {

		}
	}
	public void reloadReports() {
		reportscfg = YamlConfiguration.loadConfiguration(closed);
	}
	
	// Closed
		public FileConfiguration getClosed() {
			return closedcfg;
		}
		
		public void saveClosed() {
			try {
				closedcfg.save(closed);
			} catch (IOException e) {

			}
		}
		public void reloadClosed() {
			closedcfg = YamlConfiguration.loadConfiguration(closed);
		}
}

