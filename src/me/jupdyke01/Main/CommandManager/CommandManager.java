package me.jupdyke01.Main.CommandManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jupdyke01.Main.CommandManager.Commands.CMDCheck;
import me.jupdyke01.Main.CommandManager.Commands.CMDClear;
import me.jupdyke01.Main.CommandManager.Commands.CMDClearAll;
import me.jupdyke01.Main.CommandManager.Commands.CMDClearClosed;
import me.jupdyke01.Main.CommandManager.Commands.CMDClose;
import me.jupdyke01.Main.CommandManager.Commands.CMDHelp;
import me.jupdyke01.Main.CommandManager.Commands.CMDInfo;
import me.jupdyke01.Main.CommandManager.Commands.CMDLookUp;
import me.jupdyke01.Main.CommandManager.Commands.CMDOpen;
import me.jupdyke01.Main.CommandManager.Commands.CMDRemove;
import me.jupdyke01.Main.CommandManager.Commands.CMDReport;
import me.jupdyke01.Main.CommandManager.Commands.CMDReports;
import me.jupdyke01.Main.CommandManager.Commands.CMDSTFU;
import me.jupdyke01.ReportGUI.Main;
import net.md_5.bungee.api.ChatColor;

public class CommandManager implements CommandExecutor{

    private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
    private Main plugin = Main.getInstance();

    public CommandManager() {
    }

    //Sub Commands
    public String main = "reportgui";
    public String info = "info";
    public String report = "report";
    public String reports = "reports";
    public String help = "help";
    public String open = "open";
    public String close = "close";
    public String clearclosed = "clearclosed";
    public String clearall = "clearall";
    public String clear = "clear";
    public String check = "check";
    public String remove = "remove";
    public String stfu = "stfu";
    public String lookup = "lookup";

    public void setup() {
        plugin.getCommand(main).setExecutor(this);

        this.commands.add(new CMDInfo());
        this.commands.add(new CMDReport());
        this.commands.add(new CMDReports());
        this.commands.add(new CMDHelp());
        this.commands.add(new CMDOpen());
        this.commands.add(new CMDOpen());
        this.commands.add(new CMDClose());
        this.commands.add(new CMDClearClosed());
        this.commands.add(new CMDClearAll());
        this.commands.add(new CMDClear());
        this.commands.add(new CMDCheck());
        this.commands.add(new CMDRemove());
        this.commands.add(new CMDSTFU());
        this.commands.add(new CMDLookUp());
    }

    public CMDReport getCMDReport() {
    	return (CMDReport) this.commands.get(1);
    }
    
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use commands for this plugin.");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase(main)) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Please add arguments to your command. Type /reportgui help for info");
                return true;
            }

            SubCommand target = this.get(args[0]);

            if (target == null) {
                player.sendMessage(ChatColor.RED + "Invalid subcommand");
                return true;
            }

            ArrayList<String> arrayList = new ArrayList<String>();

            arrayList.addAll(Arrays.asList(args));
            arrayList.remove(0);

            try{
                target.onCommand(player,args);
            }catch (Exception e){
                player.sendMessage(ChatColor.RED + "An error has occurred.");

                e.printStackTrace();
            }
        }

        return true;
    }

    private SubCommand get(String name) {
        Iterator<SubCommand> subcommands = this.commands.iterator();

        while (subcommands.hasNext()) {
            SubCommand sc = (SubCommand) subcommands.next();

            if (sc.name().equalsIgnoreCase(name)) {
                return sc;
            }

            String[] aliases;
            int length = (aliases = sc.aliases()).length;

            for (int var5 = 0; var5 < length; ++var5) {
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)) {
                    return sc;
                }

            }
        }
        return null;
    }
}