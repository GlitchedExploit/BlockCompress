package com.Pixeler.BlockCompress;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EmeraldCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Griff... console has no emeralds...");
			return false;
		}
		
		Player p = (Player) sender;
		
		if (BlockCompress.getItemAmount(p, Material.EMERALD) < 64) {
			p.sendMessage(ChatColor.RED + "You must have at least 64 emeralds in your inventory.");
			return false;
		}
		
		BlockCompress.compressItems(p, Material.EMERALD, Material.EMERALD_BLOCK);

		return false;
	}
}
