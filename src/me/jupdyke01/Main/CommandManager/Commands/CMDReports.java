package me.jupdyke01.Main.CommandManager.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

public class CMDReports extends SubCommand implements Listener{
	private Main plugin = Main.getInstance();


	public void ReportsCommand(Player p) {
		ReportsInventory(p);
	}

	public Inventory ReportsInventory(Player player) {
		Inventory i = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Reports Main Menu");
		player.openInventory(i);

		ItemStack Opened = new ItemStack(Material.WOOL, 1, (byte) 5);
		ItemMeta OpenedMeta = Opened.getItemMeta();
		OpenedMeta.setDisplayName(ChatColor.GREEN + "OPENED REPORTS");
		Opened.setItemMeta(OpenedMeta);

		ItemStack Closed = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta ClosedMeta = Closed.getItemMeta();
		ClosedMeta.setDisplayName(ChatColor.RED + "CLOSED REPORTS");
		Closed.setItemMeta(ClosedMeta);

		ItemStack Filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta FillerMeta = Filler.getItemMeta();
		FillerMeta.setDisplayName(" ");
		Filler.setItemMeta(FillerMeta);

		i.setItem(0, Filler);
		i.setItem(1, Opened);
		i.setItem(2, Opened);
		i.setItem(3, Opened);
		i.setItem(4, Filler);
		i.setItem(5, Closed);
		i.setItem(6, Closed);
		i.setItem(7, Closed);
		i.setItem(8, Filler);
		i.setItem(9, Filler);
		i.setItem(10, Opened);
		i.setItem(11, Opened);
		i.setItem(12, Opened);
		i.setItem(13, Filler);
		i.setItem(14, Closed);
		i.setItem(15, Closed);
		i.setItem(16, Closed);
		i.setItem(17, Filler);
		i.setItem(22, Filler);
		i.setItem(19, Opened);
		i.setItem(20, Opened);
		i.setItem(21, Opened);
		i.setItem(18, Filler);
		i.setItem(23, Closed);
		i.setItem(24, Closed);
		i.setItem(25, Closed);
		i.setItem(26, Filler);
		return i;
	}

	public Inventory OpenReports(Player player) {
		Inventory i = Bukkit.createInventory(null, 54, ChatColor.GREEN + "OPENED REPORTS");
		player.openInventory(i);

		ItemStack Opened = new ItemStack(Material.WOOL, 1, (byte) 5);
		ItemMeta OpenedMeta = Opened.getItemMeta();
		OpenedMeta.setDisplayName(ChatColor.GREEN + "OPENED REPORTS");
		Opened.setItemMeta(OpenedMeta);

		ItemStack Closed = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta ClosedMeta = Closed.getItemMeta();
		ClosedMeta.setDisplayName(ChatColor.RED + "CLOSED REPORTS");
		Closed.setItemMeta(ClosedMeta);

		ItemStack Filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta FillerMeta = Filler.getItemMeta();
		FillerMeta.setDisplayName(" ");
		Filler.setItemMeta(FillerMeta);
		
		ItemStack Back = new ItemStack(Material.BARRIER, 1);
		ItemMeta BackMeta = Back.getItemMeta();
		BackMeta.setDisplayName(ChatColor.RED + "Back");
		ArrayList<String> lore10 = new ArrayList<String>();
		lore10.add(ChatColor.GREEN +"Go Back");
		BackMeta.setLore(lore10);
		Back.setItemMeta(BackMeta);

		int x = -1;
		for (String ReportUsers : Main.cfgm.getReports().getKeys(false)) {
			for (String Reports : Main.cfgm.getReports().getConfigurationSection(ReportUsers).getKeys(false)) {
				if (x >= 53) {
					break;
				}
				x++;
				String ConfigPath = ReportUsers + "." + Reports;
				String Reported = ReportUsers;
				String Reporter = Main.cfgm.getReports().getString(ConfigPath + "." + "Reporter");
				String Time = Main.cfgm.getReports().getString(ConfigPath + "." + "Time");
				String Reason = Main.cfgm.getReports().getString(ConfigPath + "." + "Reason");
				@SuppressWarnings("unchecked")
				List<String> Proof = (List<String>) Main.cfgm.getReports().getList(ConfigPath + "." + "Proof");


				ItemStack Report = new ItemStack(Material.WOOL, 1);
				ItemMeta ReportMeta = Report.getItemMeta();
				ReportMeta.setDisplayName(ChatColor.BLUE + Reports);

				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GRAY + "Reported: " + ChatColor.AQUA + Reported);
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
		i.setItem(53, Back);
		return i;
	}

	public Inventory CloseReports(Player player) {
		Inventory i = Bukkit.createInventory(null, 54, ChatColor.RED + "CLOSED REPORTS");
		player.openInventory(i);

		ItemStack Opened = new ItemStack(Material.WOOL, 1, (byte) 5);
		ItemMeta OpenedMeta = Opened.getItemMeta();
		OpenedMeta.setDisplayName(ChatColor.GREEN + "OPENED REPORTS");
		Opened.setItemMeta(OpenedMeta);

		ItemStack Closed = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta ClosedMeta = Closed.getItemMeta();
		ClosedMeta.setDisplayName(ChatColor.RED + "CLOSED REPORTS");
		Closed.setItemMeta(ClosedMeta);

		ItemStack Filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta FillerMeta = Filler.getItemMeta();
		FillerMeta.setDisplayName(" ");
		Filler.setItemMeta(FillerMeta);

		ItemStack Back = new ItemStack(Material.BARRIER, 1);
		ItemMeta BackMeta = Back.getItemMeta();
		BackMeta.setDisplayName(ChatColor.RED + "Back");
		ArrayList<String> lore10 = new ArrayList<String>();
		lore10.add(ChatColor.GREEN +"Go Back");
		BackMeta.setLore(lore10);
		Back.setItemMeta(BackMeta);
		
		int x = -1;
		for (String ReportUsers : Main.cfgm.getClosed().getKeys(false)) {
			for (String Reports : Main.cfgm.getClosed().getConfigurationSection(ReportUsers).getKeys(false)) {
				if (x >= 53) {
					break;
				}
				x++;
				String ConfigPath = ReportUsers + "." + Reports;
				String Reported = ReportUsers;
				String Reporter = Main.cfgm.getClosed().getString(ConfigPath + "." + "Reporter");
				String TimeClosed = Main.cfgm.getClosed().getString(ConfigPath + "." + "TimeClosed");
				String Time = Main.cfgm.getClosed().getString(ConfigPath + "." + "Time");
				String Reason = Main.cfgm.getClosed().getString(ConfigPath + "." + "Reason");
				@SuppressWarnings("unchecked")
				List<String> Proof = (List<String>) Main.cfgm.getClosed().getList(ConfigPath + "." + "Proof");


				ItemStack Report = new ItemStack(Material.WOOL, 1);
				ItemMeta ReportMeta = Report.getItemMeta();
				ReportMeta.setDisplayName(ChatColor.BLUE + Reports);

				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GRAY + "Reported: " + ChatColor.AQUA + Reported);
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
		i.setItem(53, Back);
		return i;
	}
	
	@EventHandler
	public void  ReportsList(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (event.getInventory() != null) {
			if (event.getCurrentItem() != null) {
				ItemStack item = event.getCurrentItem();
				Inventory open = event.getInventory();

				if (open == null) {
					return;
				}
				if (open.getTitle().contains("Reports Main Menu")) {

					event.setCancelled(true);

					if (item == null || !item.hasItemMeta()) {
						return;
					}
					if (item.getItemMeta().hasDisplayName()) {
						if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "OPENED REPORTS")) {
							player.closeInventory();
							player.openInventory(OpenReports(player));
						} else if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "CLOSED REPORTS")){
							player.closeInventory();
							player.openInventory(CloseReports(player));
						} 
					} 
				} else if (open.getTitle().contains("OPENED REPORTS") || open.getTitle().contains("CLOSED REPORTS")) {
					event.setCancelled(true);

					if (item == null || !item.hasItemMeta()) {
						return;
					}
					if (item.getItemMeta().hasDisplayName()) {
						if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Back")) {
							player.closeInventory();
							player.openInventory(ReportsInventory(player));
						}
					} 
				} 
			}
		}
	}
	
	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.reports"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		if (args.length == 1) {
			ReportsCommand(p);
		} else {
			p.sendMessage(LangManager.InvSyntax);
			return;
		}
	}

	@Override
	public String name() {

		return plugin.commandManager.reports;
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
