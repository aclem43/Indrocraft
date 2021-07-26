package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Economy implements TabExecutor {

    private final Main main;
    public Economy(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        main.data.addcolumn("Bank", "INT(100)");
        main.data.addcolumn("Wallet", "INT(100)");

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
        } else {
            Player player = (Player) sender;
            //if no arguments are given:
            if (args.length == 0) {
                String ban = main.data.getString(player.getUniqueId(), "Bank");
                String wal = main.data.getString(player.getUniqueId(), "Wallet");
                player.sendMessage("Your wallet has: " + ChatColor.GOLD + "$" + wal);
                player.sendMessage("Your bank has: " + ChatColor.GOLD + "$" + ban);
                sender.sendMessage(ChatColor.RED + "For more commands use /economy help");
                return true;
            }
            //help information:
            if ("help".equalsIgnoreCase(args[0])) {
                player.sendMessage(ChatColor.GREEN + "Economy help menu\n" + ChatColor.YELLOW + ChatColor.STRIKETHROUGH + "-----------------------------");
                player.sendMessage(ChatColor.GOLD + "/economy bank" + ChatColor.GREEN + " - Shows you how much money you have in your bank");
                player.sendMessage(ChatColor.GOLD + "/economy withdraw" + ChatColor.RED + " [amount]" + ChatColor.GREEN + " - withdraws money to your wallet");
                player.sendMessage(ChatColor.GOLD + "/economy deposit" + ChatColor.RED + " [amount]" + ChatColor.GREEN + " - deposits money to your bank");
                player.sendMessage(ChatColor.GOLD + "/economy wallet" + ChatColor.GREEN + " - Shows you how much money you can access on this server");
                player.sendMessage(ChatColor.GOLD + "/economy pay" + ChatColor.RED + " [player] [amount]" + ChatColor.GREEN + " - sends money to another player");
                player.sendMessage("" + ChatColor.YELLOW + ChatColor.STRIKETHROUGH + "-----------------------------");
                return true;
            }
            //dev command
            if ("add".equalsIgnoreCase(args[0])) {
                main.data.setInt(player.getUniqueId(), 50, "Bank");
                main.data.setInt(player.getUniqueId(), 0, "Wallet");
            }
            //shows your current bank or wallet ballance:
            if ("bank".equalsIgnoreCase(args[0])) {
                String ban = main.data.getString(player.getUniqueId(), "bank");
                player.sendMessage("You current have: $" + ban + "  in your bank account!");
            }
            if ("wal".equalsIgnoreCase(args[0]) || "wallet".equalsIgnoreCase(args[0])) {
                String wal = main.data.getString(player.getUniqueId(), "wallet");
                player.sendMessage("You currently have: $ " + wal + " in your wallet!");
            }
            //withdraws money to your wallet from bank:
            if ("with".equalsIgnoreCase(args[0]) || "withdraw".equalsIgnoreCase(args[0])) {
                List<String> arg = new ArrayList<>();
                arg.add(args[1]);
                player.sendMessage(arg.toString());
                if (arg.toString() == "") {
                    player.sendMessage(ChatColor.RED + "You must put a number after 'with'");
                    return true;
                }
                if (isNum(args[1])) {
                    int ban = main.data.getInt(player.getUniqueId(), "bank");
                    int wal = main.data.getInt(player.getUniqueId(), "Wallet");
                    int amount = (Integer.parseInt(args[1]));
                    if (amount > ban) {
                        player.sendMessage(ChatColor.BLUE + "You dont have enough money in your bank to do that!");
                        return true;
                    }
                    main.data.setInt(player.getUniqueId(), wal + amount, "Wallet");
                    main.data.setInt(player.getUniqueId(), ban - amount, "bank");
                    player.sendMessage(ChatColor.BLUE + "Successfully withdrew: " + amount + " wallet now at: " + (wal + amount));
                } else {
                    player.sendMessage(ChatColor.RED + "You must have a number after /economy withdraw");
                    return true;
                }
                return true;
            }
            //deposits money to your wallet
            if ("dep".equalsIgnoreCase(args[0]) || "deposit".equalsIgnoreCase(args[0])) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "You must put a number after 'with'");
                    return true;
                }
                String num = args[1];
                player.sendMessage(player.getWorld().toString());
                if (isNum(args[1])) {
                    int ban = main.data.getInt(player.getUniqueId(), "bank");
                    int wal = main.data.getInt(player.getUniqueId(), "Wallet");
                    int amount = (Integer.parseInt(args[1]));
                    if (amount > wal) {
                        player.sendMessage(ChatColor.BLUE + "You do not have enough money in your wallet to do that!");
                        return true;
                    }
                    main.data.setInt(player.getUniqueId(), wal - amount, "Wallet");
                    main.data.setInt(player.getUniqueId(), ban + amount, "bank");
                    player.sendMessage(ChatColor.BLUE + "Successfully deposited: " + amount + " bank balance now at: " + (ban + amount));
                } else {
                    player.sendMessage(ChatColor.RED + "You must have a number after /economy deposit");
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
        if (args.length == 1){
            List<String> arg1 = new ArrayList<>();
            arg1.add("bank");
            arg1.add("deposit");
            arg1.add("pay");
            arg1.add("wallet");
            arg1.add("withdraw");
            return arg1;
        }
        return null;
    }

    public boolean isNum(String num) {
        try {
            Integer.parseInt(num);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}