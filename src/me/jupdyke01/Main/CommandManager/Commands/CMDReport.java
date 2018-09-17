package me.jupdyke01.Main.CommandManager.Commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jupdyke01.Main.CommandManager.SubCommand;
import me.jupdyke01.Main.Utils.LangManager;
import me.jupdyke01.ReportGUI.Main;
import me.jupdyke01.ReportGUI.Report;

public class CMDReport extends SubCommand implements Listener{
	private Main plugin = Main.getInstance();


	public static Inventory ReportInventory(Player player) {
		Inventory i = Bukkit.createInventory(null,27, ChatColor.BLUE + "Report");
		player.openInventory(i);

		ItemStack ReportKiller = new ItemStack(Material.WOOL, 1, (byte) 5);
		ItemMeta ReportKillerMeta = ReportKiller.getItemMeta();
		ReportKillerMeta.setDisplayName(ChatColor.GREEN + "Report Last Killer");
		ArrayList<String> lore = new ArrayList<String>();
		if (!(Main.Killed.containsKey(player))) {
			lore.add(ChatColor.GRAY + "Player: " + ChatColor.AQUA + "Null");
		} else {
			lore.add(ChatColor.GRAY + "Player: " + ChatColor.AQUA + Main.Killed.get(player).getName());
		}
		ReportKillerMeta.setLore(lore);
		ReportKiller.setItemMeta(ReportKillerMeta);

		ItemStack ReportKilled = new ItemStack(Material.WOOL, 1, (byte) 9);
		ItemMeta ReportKilledMeta = ReportKilled.getItemMeta();
		ReportKilledMeta.setDisplayName(ChatColor.BLUE + "Report Last Killed");
		ArrayList<String> lore2 = new ArrayList<String>();
		if (!(Main.Kill.containsKey(player))) {
			lore2.add(ChatColor.GRAY + "Player: " + ChatColor.AQUA + "Null");
		} else {
			lore2.add(ChatColor.GRAY + "Player: " + ChatColor.AQUA + Main.Kill.get(player).getName());
		}
		ReportKilledMeta.setLore(lore2);
		ReportKilled.setItemMeta(ReportKilledMeta);

		ItemStack ReportLastHit = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta ReportLastHitMeta = ReportLastHit.getItemMeta();
		ReportLastHitMeta.setDisplayName(ChatColor.RED + "Report Last Hit");
		ArrayList<String> lore3 = new ArrayList<String>();
		if (!(Main.Hit.containsKey(player))) {
			lore3.add(ChatColor.GRAY + "Player: " + ChatColor.AQUA + "Null");
		} else {
			lore3.add(ChatColor.GRAY + "Player: " + ChatColor.AQUA + Main.Hit.get(player).getName());
		}
		ReportLastHitMeta.setLore(lore3);
		ReportLastHit.setItemMeta(ReportLastHitMeta);


		ItemStack ReportLastHitBy = new ItemStack(Material.WOOL, 1, (byte) 2);
		ItemMeta ReportLastHitByMeta = ReportLastHitBy.getItemMeta();
		ReportLastHitByMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Report Last Hit By");
		ArrayList<String> lore4 = new ArrayList<String>();
		if (!(Main.HitBy.containsKey(player))) {
			lore4.add(ChatColor.GRAY + "Player: " + ChatColor.AQUA + "Null");
		} else {
			lore4.add(ChatColor.GRAY + "Player: " + ChatColor.AQUA + Main.HitBy.get(player).getName());
		}
		ReportLastHitByMeta.setLore(lore4);
		ReportLastHitBy.setItemMeta(ReportLastHitByMeta);

		ItemStack Filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta FillerMeta = Filler.getItemMeta();
		FillerMeta.setDisplayName(" ");
		Filler.setItemMeta(FillerMeta);

		i.setItem(0, Filler);
		i.setItem(1, ReportKiller);
		i.setItem(2, Filler);
		i.setItem(3, ReportKilled);
		i.setItem(4, Filler);
		i.setItem(5, ReportLastHit);
		i.setItem(6, Filler);
		i.setItem(7, ReportLastHitBy);
		i.setItem(8, Filler);
		i.setItem(9, Filler);
		i.setItem(10, ReportKiller);
		i.setItem(11, Filler);
		i.setItem(12, ReportKilled);
		i.setItem(13, Filler);
		i.setItem(14, ReportLastHit);
		i.setItem(15, Filler);
		i.setItem(16, ReportLastHitBy);
		i.setItem(17, Filler);
		i.setItem(22, Filler);
		i.setItem(19, ReportKiller);
		i.setItem(20, Filler);
		i.setItem(21, ReportKilled);
		i.setItem(18, Filler);
		i.setItem(23, ReportLastHit);
		i.setItem(24, Filler);
		i.setItem(25, ReportLastHitBy);
		i.setItem(26, Filler);
		return i;
	}

	public HashMap<UUID, Report> reports = new HashMap<UUID, Report>();

	public void ReportCommand(Player reporter) {
		ReportInventory(reporter);
	}

	public void ReportCommandOther(Player reporter, OfflinePlayer reported) {
		if (reports.containsKey(reporter.getUniqueId())) {
			reporter.sendMessage(LangManager.PluginTag + ChatColor.RED + " You are already filling out an active report!");
			reporter.sendMessage(LangManager.PluginTag + ChatColor.RED + " Type CANCEL to stop!");
			return;
		}
		Report r = new Report(plugin, reporter, reported);
		reports.put(reporter.getUniqueId(), r);
		reporter.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Please respond to the questions in chat.");
		reporter.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Type " + ChatColor.RED + "CANCEL" + ChatColor.GRAY + " to stop at any point in time.");
		reporter.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Name of the report:");
	}

	@EventHandler
	public void ReportChat(AsyncPlayerChatEvent e) {

		Player p = e.getPlayer();

		if (!(reports.containsKey(p.getUniqueId()))) {
			return;
		}

		Report r = reports.get(p.getUniqueId());
		if (r.getName() == null) {
			if (!(e.getMessage().equalsIgnoreCase("cancel"))) {
				r.setName(e.getMessage());
				e.setCancelled(true);
				p.sendMessage(ChatColor.GRAY + "-------------------------------------------------");
				p.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Reason for the report: ");
				p.sendMessage(ChatColor.GRAY + "-------------------------------------------------");
				return;
			} else {
				p.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " You have cancelled this report.");
				reports.remove(p.getUniqueId());
				e.setCancelled(true);
				return;
			}
		} if (r.getReason() == null) {
			if (!(e.getMessage().equalsIgnoreCase("cancel"))) {
				r.setReason(e.getMessage());
				e.setCancelled(true);
				p.sendMessage(ChatColor.GRAY + "-------------------------------------------------");
				p.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Type 'Done' when you are done adding lines of proof.");
				p.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " You can send multiple messages of proof");
				p.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Proof for the report: ");
				p.sendMessage(ChatColor.GRAY + "-------------------------------------------------");
				return;
			} else {
				p.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " You have cancelled this report.");
				reports.remove(p.getUniqueId());
				e.setCancelled(true);
				return;
			}
		}

		if (!(e.getMessage().equalsIgnoreCase("done"))) {
			if (!(e.getMessage().equalsIgnoreCase("cancel"))) {
				e.setCancelled(true);
				r.addProof(e.getMessage());
				p.sendMessage(e.getMessage());
				p.sendMessage(ChatColor.GRAY + "-------------------------------------------------");
			} else {
				p.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " You have cancelled this report.");
				reports.remove(p.getUniqueId());
				e.setCancelled(true);
				return;
			}
		} else {
			long millis = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
			Date resultdate = new Date(millis);

			p.sendMessage(ChatColor.GRAY + "-------------------------------------------------");
			p.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " You have completed the report.");
			p.sendMessage(ChatColor.GRAY + "Reporter: " + ChatColor.AQUA + p.getName());
			p.sendMessage(ChatColor.GRAY + "Time: " + ChatColor.AQUA + sdf.format(resultdate));
			p.sendMessage(ChatColor.GRAY + "User: " + ChatColor.AQUA + r.getAccused().getName());
			p.sendMessage(ChatColor.GRAY + "Name: " + ChatColor.AQUA + r.getName());
			p.sendMessage(ChatColor.GRAY + "Reason: " + ChatColor.AQUA + r.getReason());
			p.sendMessage(ChatColor.GRAY + "Proof: " + ChatColor.AQUA + r.getProof().toString());
			p.sendMessage(ChatColor.GRAY + "-------------------------------------------------");

			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player != p) {
					if (player.hasPermission("reportgui.staff")) {
						if (!(Main.STFU.contains(player))) {
							player.sendMessage(ChatColor.GRAY + "--------" + ChatColor.AQUA + "New Report" + ChatColor.GRAY + "--------");
							player.sendMessage(ChatColor.GRAY + "User Reported: " + ChatColor.AQUA + r.getAccused().getName());
							player.sendMessage(ChatColor.GRAY + "Reporter: " + ChatColor.AQUA + p.getName());
							player.sendMessage(ChatColor.GRAY + "Reason: " + ChatColor.AQUA + r.getReason());     
							player.sendMessage(ChatColor.GRAY + "--------------------------");
						}
					}
				}
			}

			e.setCancelled(true);
			r.Submit();
			return;
		}
		return;
	}

	@EventHandler
	public void  ReportList(InventoryClickEvent event) {
		OfflinePlayer User;
		Player player = (Player) event.getWhoClicked();
		if (event.getInventory() != null) {
			if (event.getCurrentItem() != null) {
				ItemStack item = event.getCurrentItem();
				Inventory open = event.getInventory();

				if (open == null) {
					return;
				}
				if (open.getTitle().contains("Report")) {

					event.setCancelled(true);

					if (item == null || !item.hasItemMeta()) {
						return;
					}
					if (item.getItemMeta().hasDisplayName()) {
						if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Report Last Killer")) {
							player.closeInventory();

							if (!(Main.Killed.containsKey(player))) {
								player.sendMessage(LangManager.NoPKilled);
								return;
							} else {
								User = Main.Killed.get(player);
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Please respond to the questions in chat.");
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Type " + ChatColor.RED + "CANCEL" + ChatColor.GRAY + " to stop at any point in time.");
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Name of the report:");
								if (!(reports.containsKey(player.getUniqueId()))) {
									reports.put(player.getUniqueId(), new Report(plugin, player, User));
								}
							}
						} else if (item.getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Report Last Killed")) {
							player.closeInventory();
							if (!(Main.Kill.containsKey(player))) {
								player.sendMessage(LangManager.NoPKill);
								return;
							} else {
								User = Main.Kill.get(player);
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Please respond to the questions in chat.");
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Type " + ChatColor.RED + "CANCEL" + ChatColor.GRAY + " to stop at any point in time.");
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Name of the report:");
								reports.put(player.getUniqueId(), new Report(plugin, player, User));
							}
						} else if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Report Last Hit")) {
							player.closeInventory();
							if (!(Main.Hit.containsKey(player))) {
								player.sendMessage(LangManager.NoPHit);
								return;
							} else {
								User = Main.Hit.get(player);
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Please respond to the questions in chat.");
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Type " + ChatColor.RED + "CANCEL" + ChatColor.GRAY + " to stop at any point in time.");
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Name of the report:");
								reports.put(player.getUniqueId(), new Report(plugin, player, User));
							}
						} else if (item.getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Report Last Hit By")) {
							player.closeInventory();
							if (!(Main.HitBy.containsKey(player))) {
								player.sendMessage(LangManager.NoPHitBy);
								return;
							} else {
								User = Main.HitBy.get(player);
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Please respond to the questions in chat.");
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Type " + ChatColor.RED + "CANCEL" + ChatColor.GRAY + " to stop at any point in time.");
								player.sendMessage(LangManager.PluginTag + ChatColor.GRAY + " Name of the report:");
								reports.put(player.getUniqueId(), new Report(plugin, player, User));

							}
						}
					} 
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(Player p, String[] args) {
		if (!(p.hasPermission("reportgui.report"))) {
			p.sendMessage(LangManager.NoPerm);
			return;
		}
		if (args.length == 1) {
			ReportCommand(p);
			return;
		} else if (args.length == 2) {
			Player t = Bukkit.getPlayer(args[1]);
			if (t != null) {
				if (t.equals(p)) {
					p.sendMessage(LangManager.ReportSelf);
					return;
				}
				ReportCommandOther(p, t);
				return;
			} else {
				OfflinePlayer to = Bukkit.getOfflinePlayer(args[1]);
				if (to == null || !to.hasPlayedBefore()) {
					p.sendMessage(LangManager.PlayerNull.replace("%player%", args[1]));
					return;
				} else {
					ReportCommandOther(p, to);
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
		return plugin.commandManager.report;
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