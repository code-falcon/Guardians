package com.falkon.guardians.misc;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CItem {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    private String name;
    private byte data;
    private Material material;
    private List<String> lore;

    public CItem(String name, Material material) {
        this.name=name;
        this.material=material;
        lore = new ArrayList<>();
        this.itemStack=new ItemStack(material);
        this.itemMeta=itemStack.getItemMeta();
        this.itemMeta.setDisplayName(name);
    }

    public CItem(String name, Material material, byte data) {
        this.name=name;
        this.material=material;
        lore = new ArrayList<>();
        this.itemStack=new ItemStack(material,1,data);
        this.itemMeta=itemStack.getItemMeta();
    }

    public void addLore(String... s) {
        for (String l : s) {
            lore.add(l);
        }
    }

    public ItemStack getItemStack() {
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
