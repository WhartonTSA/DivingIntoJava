package org.apcs.mc.commands;

import org.apcs.mc.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SellCommand implements CommandExecutor {

    private Main plugin;

    public SellCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    // /sell <block> <amount>
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Material item;
        try {
            item = Material.valueOf(strings[0].toUpperCase());
        } catch (IllegalArgumentException ex) {
            commandSender.sendMessage("Please provide a valid item name");
            return true;
        }
        int amount = Integer.parseInt(strings[1]);
        int processed = 0;

        Player player = (Player) commandSender;
        ItemStack[] stacks = player.getInventory().getContents();
        for (int i = 0; i < stacks.length; i++) {
            if (processed >= amount) {
                break;
            }
            ItemStack itemStack = stacks[i];
            if (itemStack == null) {
                continue;
            }
            boolean match = itemStack.getType() == item;
            if (!match) {
                continue;
            }
            if (itemStack.getAmount() > amount) {
                itemStack.setAmount(itemStack.getAmount() - amount);
                processed = amount;
                break;
            } else if (itemStack.getAmount() > 0) {
                processed += itemStack.getAmount();
                stacks[i] = null;
            }
        }
        player.getInventory().setContents(stacks);
        int moneyGiven = 10 * amount;
        WalletCommand walletCommand = (WalletCommand) this.plugin.getCommand("wallet").getExecutor();
        WalletCommand.WalletManager.Wallet wallet = walletCommand.getWalletManager().getWallet(((Player) commandSender).getUniqueId());
        wallet.add((double) moneyGiven);
        commandSender.sendMessage("You got cash! (" + moneyGiven + ")");
        return true;
    }
}
