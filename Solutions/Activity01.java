package org.apcs.mc.commands;

import org.apcs.mc.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WalletCommand implements CommandExecutor {

    /**
     * The wallet manager instance
     */
    private WalletManager walletManager;
    private Main plugin;

    {
        walletManager = new WalletManager();
    }

    public WalletCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        // If no arguments are provided, default to showing the help page. This prevents an out-of-bounds exception
        if (args.length == 0) {
            args = new String[] {"help"};
        }
        WalletManager.Wallet targetWallet, senderWallet;
        boolean success;
        Double transferAmount;
        Player player;
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Players only :(");
            return true;
        }
        player = (Player) commandSender;
        senderWallet = walletManager.getWallet(player.getUniqueId());
        switch (args[0]) {
            case "send":
                // Get the target player by username
                Player target = plugin.getServer().getPlayer(args[1]);
                try {
                    // Safely parse the transfer amount
                    transferAmount = Double.parseDouble(args[2]);
                } catch (NumberFormatException ex) {
                    commandSender.sendMessage(ChatColor.RED + "Please provide a real number.");
                    break;
                }
                if (target == null) {
                    commandSender.sendMessage(ChatColor.RED + "Please provide a real user.");
                    break;
                }
                UUID targetUUID = target.getUniqueId();
                targetWallet = walletManager.getWallet(targetUUID);
                // Transfer!
                success = senderWallet.transfer(targetWallet, transferAmount);
                if (!success) {
                    commandSender.sendMessage(ChatColor.RED + "Couldn't complete the transfer. Try a lower amount?");
                    break;
                }
                commandSender.sendMessage(ChatColor.AQUA + "Sent $" + transferAmount + " to " + target.getName());
                return true;
            case "donate":
                try {
                    // Safely parse the transfer amount
                    transferAmount = Double.parseDouble(args[1]);
                } catch (NumberFormatException ex) {
                    commandSender.sendMessage(ChatColor.RED + "Please provide a real number.");
                    break;
                }
                success = senderWallet.deduct(transferAmount);
                if (!success) {
                    commandSender.sendMessage(ChatColor.RED + "Couldn't complete your donation. Try a lower amount?");
                    break;
                }
                commandSender.sendMessage(ChatColor.AQUA + "Donated $" + transferAmount + " to the server.");
                return true;
            case "balance":
                commandSender.sendMessage(ChatColor.AQUA + "You have $" + senderWallet.get());
                return true;
            default:
                // \n denotes a new line without having to send multiple messages
                commandSender.sendMessage(ChatColor.AQUA + "/wallet send <player> <amount>\n/wallet donate <amount>\n/wallet balance\n/wallet help");
                return true;
        }
        return false;
    }

    /**
     * A self-contained wallet management class
     */
    private class WalletManager {
        /**
         * The wallets
         */
        private Map<UUID, Wallet> wallets;

        {
            wallets = new HashMap<>();
        }

        /**
         * Gets a wallet with the given UUID
         * @param uuid the uuid to search for
         * @return the wallet
         */
        public Wallet getWallet(UUID uuid) {
            if (wallets.containsKey(uuid)) {
                return wallets.get(uuid);
            }
            Wallet newWallet = new Wallet(100.0);
            wallets.put(uuid, newWallet);
            return newWallet;
        }

        /**
         * The Wallet implementation class
         */
        private class Wallet {
            /**
             * The raw balance
             */
            private Double balance;

            /**
             * Construct a Wallet with the given balance
             * @param initialValue the initial balance
             */
            public Wallet(Double initialValue) {
                balance = initialValue;
            }

            /**
             * Adds the given value to the balance (negative numbers are coerced to positive)
             * @param value the value to add
             * @return the new balance
             */
            public void add(Double value) {
                balance += Math.abs(value);
            }

            /**
             * Deducts the given value from the balance (negative numbers are coerced to positive)
             * @param value the value to deduct
             * @return the new balance
             */
            public boolean deduct(Double value) {
                if (balance < value) {
                    return false;
                }
                balance -= Math.abs(value);
                return true;
            }

            /**
             * Transfers the given amount to a destination wallet
             * @param destination the wallet to transfer to
             * @param amount the amount to transfer
             * @return whether it was successful
             */
            public boolean transfer(Wallet destination, Double amount) {
                amount = Math.abs(amount);
                if (balance < amount) {
                    return false;
                }
                destination.add(amount);
                deduct(amount);
                return true;
            }

            /**
             * Gets the current balance
             * @return the current balance
             */
            public Double get() {
                return balance;
            }

            /**
             * Overwrites the balance
             * @param balance the new balance
             */
            public void set(Double balance) {
                this.balance = balance;
            }
        }
    }
}
