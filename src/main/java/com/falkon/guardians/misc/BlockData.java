package com.falkon.guardians.misc;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BlockData {

    private Material material;
    private byte data;

    private ItemStack itemStack;

    public BlockData(Material material, byte data) {
        this.material=material;
        this.data=data;

        this.itemStack=new ItemStack(material,1,data);
    }

    public Material getMaterial() {
        return material;
    }

    public byte getData() {
        return data;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
