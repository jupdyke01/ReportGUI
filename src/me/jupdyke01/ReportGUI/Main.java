package me.jupdyke01.ReportGUI;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.jupdyke01.Main.CommandManager.CommandManager;
import me.jupdyke01.Main.CommandManager.Commands.CMDCheck;
import me.jupdyke01.Main.CommandManager.Commands.CMDReports;
import me.jupdyke01.Main.Events.OnHitEvent;
import me.jupdyke01.Main.Events.OnJoinEvent;
import me.jupdyke01.Main.Events.OnKillEvent;
import me.jupdyke01.Main.Utils.ConfigManager;

public class Main extends JavaPlugin {

	public static ConfigManager cfgm;
	private File langFile = null;
	public YamlConfiguration lang = new YamlConfiguration();

	public static Plugin pl;

	public static HashMap<Player, Player> Killed = new HashMap<Player, Player>();
	public static HashMap<Player, Player> Kill = new HashMap<Player, Player>();
	public static HashMap<Player, Player> Hit = new HashMap<Player, Player>();
	public static HashMap<Player, Player> HitBy = new HashMap<Player, Player>();
	public static ArrayList<Player> STFU = new ArrayList<Player>();

	private static Main instance;
	public CommandManager commandManager;

	public void onEnable() {
		setInstance(this);
		langFile = new File(getDataFolder(), "lang.yml");
		pl = this;

		mkdir();
		loadYamls();
		loadConfigManager();
		RegisterCommands();
		RegisterEvents();
	}


	public static Main getInstance() {
		return instance;
	}

	public CommandManager getCommandManager() {
		return this.commandManager;
	}
	
	private static void setInstance(Main instance) {
		Main.instance = instance;
	}


	private void RegisterCommands() {
		commandManager = new CommandManager();
		commandManager.setup();
	}

	private void RegisterEvents() {
		getServer().getPluginManager().registerEvents(new OnKillEvent(), this);
		getServer().getPluginManager().registerEvents(new OnHitEvent(), this);
		getServer().getPluginManager().registerEvents(new CMDCheck(), this);
		getServer().getPluginManager().registerEvents(new CMDReports(), this);
		getServer().getPluginManager().registerEvents(new OnJoinEvent(), this);
		getServer().getPluginManager().registerEvents(commandManager.getCMDReport(), this);
	}

	private void mkdir() {
		if(!langFile.exists()) {
			saveResource("lang.yml" ,false);
		}
	}

	private void loadYamls() {
		try {
			lang.load(langFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public YamlConfiguration getLang() {
		return lang;
	}

	public static void loadConfigManager() {
		cfgm = new ConfigManager();
		cfgm.setup();
	}

	public void saveLang() {
		try {
			lang.save(langFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
