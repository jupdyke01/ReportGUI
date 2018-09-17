package me.jupdyke01.Main.CommandManager.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;

public class CMDCheck extends SubCommand implements Listener{
	private Main plugin = Main.getInstance();
	
	public void CheckCommand(Player p, OfflinePlayer t) {
		ReportInventory(p, t);
		return;
	}

	@SuppressWarnings("unchecked")
	public Inventory ReportInventory(Player p, OfflinePlayer t) {
		Inventory i = Bukkit.createInventory(null, 54, ChatColor.RED + t.getName() + ChatColor.GRAY + " Reports");
		p.openInventory(i);

		ItemStack GreenGlass = new ItemStack(Material.STAINED_GLASS, 1, (byte) 5);
		ItemMeta GreenGlassMeta = GreenGlass.getItemMeta();
		GreenGlassMeta.setDisplayName(ChatColor.GREEN + "OPEN");
		GreenGlass.setItemMeta(GreenGlassMeta);

		i.setItem(0, GreenGlass);
		i.setItem(1, GreenGlass);
		i.setItem(2, GreenGlass);
		i.setItem(3, GreenGlass);
		i.setItem(4, GreenGlass);
		i.setItem(5, GreenGlass);
		i.setItem(6, GreenGlass);
		i.setItem(7, GreenGlass);
		i.setItem(8, GreenGlass);

		ItemStack RedGlass = new ItemStack(Material.STAINED_GLASS, 1, (byte) 14);
		ItemMeta RedGlassMeta = RedGlass.getItemMeta();
		RedGlassMeta.setDisplayName(ChatColor.RED + "CLOSED");
		RedGlass.setItemMeta(RedGlassMeta);

		i.setItem(27, RedGlass);
		i.setItem(28, RedGlass);
		i.setItem(29, RedGlass);
		i.setItem(30, RedGlass);
		i.setItem(31, RedGlass);
		i.setItem(32, RedGlass);
		i.setItem(33, RedGlass);
		i.setItem(34, RedGlass);
		i.setItem(35, RedGlass);

		if (Main.cfgm.getReports().contains(t.getName())) {
			int x = 8;
			for (String Reports : Main.cfgm.getReports().getConfigurationSection(t.getName()).getKeys(false)) {
				x++;
				String ConfigPath = t.getName() + "." + Reports;
				String Reporter = Main.cfgm.getReports().getString(ConfigPath + "." + "Reporter");
				String Time = Main.cfgm.getReports().getString(ConfigPath + "." + "Time");
				String Reason = Main.cfgm.getReports().getString(ConfigPath + "." + "Reason");
				List<String> Proof = (List<String>) Main.cfgm.getReports().getList(ConfigPath + "." + "Proof");


				ItemStack Report = new ItemStack(Material.WOOL, 1);
				ItemMeta ReportMeta = Report.getItemMeta();
				ReportMeta.setDisplayName(ChatColor.BLUE + Reports);

				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GRAY + "Reporter: " + ChatColor.AQUA + Reporter);
				lore.add(ChatColor.GRAY + "Time: " + ChatColor.AQUA + Time);
				lore.add(ChatColor.GRAY + "Reason: " + ChatColor.AQUA + Reason);

				if (Proof.isEmpty()) {
					lore.add(ChatColor.GRAY + "Proof: " + ChatColor.AQUA + "No Proof");
				} else {
					lore.add(ChatColor.GRAY + "Proof: ");
					for (int y = 1; y <= Proof.size(); y++) {
						lore.add(ChatColor.GREEN + "  -" + Proof.get(y-1));
					}
				}

				ReportMeta.setLore(lore);
				Report.setItemMeta(ReportMeta);

				i.setItem(x, Report);

			}
		}

		if (Main.cfgm.getClosed().contains(t.getName())) {
			int x = 35;
			for (String Reports : Main.cfgm.getClosed().getConfigurationSection(t.getName()).getKeys(false)) {
				x++;
				String ConfigPath = t.getName() + "." + Reports;
				String Reporter = Main.cfgm.getClosed().getString(ConfigPath + "." + "Reporter");
				String TimeClosed = Main.cfgm.getClosed().getString(ConfigPath + "." + "TimeClosed");
				String Time = Main.cfgm.getClosed().getString(ConfigPath + "." + "Time");
				String Reason = Main.cfgm.getClosed().getString(ConfigPath + "." + "Reason");
				List<String> Proof = (List<String>) Main.cfgm.getClosed().getList(ConfigPath + "." + "Proof");


				ItemStack Report = new ItemStack(Material.WOOL, 1);
				ItemMeta ReportMeta = Report.getItemMeta();
				ReportMeta.setDisplayName(ChatColor.BLUE + Reports);

				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GRAY + "Reporter: " + ChatColor.AQUA + Reporter);
				lore.add(ChatColor.GRAY + "TimeClosed: " + ChatColor.AQUA + TimeClosed);
				lore.add(ChatColor.GRAY + "Time: " + ChatColor.AQUA + Time);
				lore.add(ChatColor.GRAY + "Reason: " + ChatColor.AQUA + Reason);

				if (Proof.isEmpty()) {
					lore.add(ChatColor.GRAY + "Proof: " + ChatColor.AQUA + "No Proof");
				} else {
					lore.add(ChatColor.GRAY + "Proof: ");
					for (int y = 1; y <= Proof.size(); y++) {
						lore.add(ChatColor.GREEN + "  -" + Proof.get(y-1));
					}
				}

				ReportMeta.setLore(lore);
				Report.setItemMeta(ReportMeta);

				i.setItem(x, Report);

			}
		}

		return i;
	}

	@EventHandler
	public void  CheckList(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (event.getInventory() != null) {
			if (event.getCurrentItem() != null) {
				ItemStack item = event.getCurrentItem();
				Inventory open = event.getInventory();

				if (open == null) {
					return;
				}
				if (open.getTitle().contains(ChatColor.GRAY + " Reports")) {

					event.setCancelled(true);

					if (item == null || !item.hasItemMeta()) {
						return;
					}
					if (item.getItemMeta().hasDisplayName()) {
						if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "OPEN") || item.getItemMeta().getDisplayName().equals(ChatColor.RED + "CLOSED")) {
							return;
						}
						if (event.getSlot() < 28) {
							String User = ChatColor.stripColor(open.getTitle().split(" ")[0]);
							String Name = ChatColor.stripColor(item.getItemMeta().getDisplayName());

							String ConfigPath = User + "." + Name;
							String Reporter = Main.cfgm.getReports().getString(ConfigPath + "." + "Reporter");
							String Time = Main.cfgm.getReports().getString(ConfigPath + "." + "Time");
							String Reason = Main.cfgm.getReports().getString(ConfigPath + "." + "Reason");
							@SuppressWarnings("unchecked")
							List<String> Proof = (List<String>) Main.cfgm.getReports().getList(ConfigPath + "." + "Proof");

							p.sendMessage(ChatColor.BLUE + "------------------------------");
							p.sendMessage(ChatColor.GRAY + "Report Name: " + ChatColor.GREEN + Name);
							p.sendMessage(ChatColor.GRAY + "Reported: " + ChatColor.GREEN + User);
							p.sendMessage(ChatColor.GRAY + "Reporter: " + ChatColor.GREEN + Reporter);
							p.sendMessage(ChatColor.GRAY + "Time: " + ChatColor.AQUA + Time);
							p.sendMessage(ChatColor.GRAY + "Reason: " + ChatColor.GREEN + Reason);
							if (Proof == null) {
								p.sendMessage(ChatColor.GRAY + "Proof: No Proof");
								p.sendMessage(ChatColor.BLUE + "------------------------------");
								return;
							}
							p.sendMessage(ChatColor.GRAY + "Proof: ");
							for (int i = 1; i <= Proof.size(); i++) {
								p.sendMessage(ChatColor.GREEN + "  -" + Proof.get(i-1));
							}
							p.sendMessage(ChatColor.BLUE + "------------------------------");

						} else {
							String User = ChatColor.stripColor(open.getTitle().split(" ")[0]);
							String Name = ChatColor.stripColor(item.getItemMeta().getDisplayName());

							String ConfigPath = User + "." + Name;
							String Reporter = Main.cfgm.getClosed().getString(ConfigPath + "." + "Reporter");
							String TimeClosed = Main.cfgm.getClosed().getString(ConfigPath + "." + "TimeClosed");
							String Time = Main.cfgm.getClosed().getString(ConfigPath + "." + "Time");
							String Reason = Main.cfgm.getClosed().getString(ConfigPath + "." + "Reason");
							@SuppressWarnings("unchecked")
							List<String> Proof = (List<String>) Main.cfgm.getClosed().getList(ConfigPath + "." + "Proof");

							p.sendMessage(ChatColor.BLUE + "------------------------------");
							p.sendMessage(ChatColor.GRAY + "Report Name: " + ChatColor.GREEN + Name);
							p.sendMessage(ChatColor.GRAY + "Reported: " + ChatColor.GREEN + User);
							p.sendMessage(ChatColor.GRAY + "Reporter: " + ChatColor.GREEN + Reporter);
							p.sendMessage(ChatColor.GRAY + "TimeClosed: " + ChatColor.AQUA + TimeClosed);
							p.sendMessage(ChatColor.GRAY + "Time: " + ChatColor.AQUA + Time);
							p.sendMessage(ChatColor.GRAY + "Reason: " + ChatColor.GREEN + Reason);
							if (Proof == null) {
								p.sendMessage(ChatColor.GRAY + "Proof: No Proof");
								p.sendMessage(ChatColor.BLUE + "------------------------------");
								return;
							}
							p.sendMessage(ChatColor.GRAY + "Proof: ");
							for (int i = 1; i <= Proof.size(); i++) {
								p.sendMessage(ChatColor.GREEN + "  -" + Proof.get(i-1));
							}
							p.sendMessage(ChatColor.BLUE + "------------------------------");
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.check"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		if (args.length == 2) {
			Player t = Bukkit.getPlayer(args[1]);
			if (t != null) {
				CheckCommand(p, t);
				return;
			} else {
				OfflinePlayer to = Bukkit.getOfflinePlayer(args[1]);
				if (to == null || !to.hasPlayedBefore()) {
					p.sendMessage(LangManager.PlayerNull.replace("%player%", args[1]));
					return;
				} else {
					CheckCommand(p, to);
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
		return plugin.commandManager.check;
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
