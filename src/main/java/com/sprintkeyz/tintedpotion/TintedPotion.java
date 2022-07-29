package com.sprintkeyz.tintedpotion;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class TintedPotion extends JavaPlugin implements Listener {

    public static ItemStack tintedpotion;

    @Override
    public void onEnable() {
        getLogger().info("Thanks for using Tinted Potions!");
        getServer().getPluginManager().registerEvents(this, this);

        addRecipe();
    }

    @EventHandler
    public void onPlayerCraftItem(CraftItemEvent e) {
        if (e.getRecipe().getResult().equals(tintedpotion)) {
            // is crafting tinted potion, set new result.
            for (ItemStack item : e.getInventory().getContents()) {
                if (item.getType() == Material.POTION && item.getItemMeta() != null && !item.getItemMeta().getDisplayName().contains("Tinted Potion")) {
                    e.getInventory().setResult(morphPotion(item));
                    break;
                }
            }
        }
    }

    private void addRecipe() {
        ItemStack item = new ItemStack(Material.POTION, 1);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        assert meta != null;
        meta.setColor(Color.GRAY);
        meta.setDisplayName(ChatColor.WHITE + "Tinted Potion");
        meta.setLore(Collections.singletonList(ChatColor.YELLOW + "Effects Hidden"));
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);
        tintedpotion = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("tinted_potion"), item);
        sr.shape("   ",
                "GPG",
                " G ");
        sr.setIngredient('G', Material.TINTED_GLASS);
        sr.setIngredient('P', Material.POTION);
        getServer().addRecipe(sr);
    }

    private ItemStack morphPotion(ItemStack potion) {
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        assert meta != null;

        meta.setColor(Color.GRAY);
        meta.setDisplayName(ChatColor.WHITE + "Tinted Potion");
        meta.setLore(Collections.singletonList(ChatColor.YELLOW + "Effects Hidden"));
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        potion.setItemMeta(meta);

        return potion;
    }
}
