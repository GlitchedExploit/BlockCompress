package com.Pixeler.BlockCompress;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockCompress extends JavaPlugin {
	
	public void onEnable() {

		getCommand("emerald").setExecutor(new EmeraldCommand());
	}
	
	public static int getItemAmount(Player p, Material m) {
		int found = 0;
		for (ItemStack i : p.getInventory().getContents()) {
			if (i == null || i.getType() == Material.AIR || i.getType() == null) continue;
			if (i.getType().equals(m)) found += i.getAmount();
		}
		return found;
	}
	
	
	private static void removeItems(Inventory inventory, Material type, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }
	
	public static int compressItems(Player p, Material item, Material block) {
		int amount = (int) Math.floor((double)getItemAmount(p, item)/64);
		removeItems(p.getInventory(), item, amount*64);
		ItemStack b = new ItemStack(block, amount);
		p.getInventory().addItem(b);
		return amount;
		
	}
}
