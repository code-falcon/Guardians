package com.falkon.guardians.event;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public class ArrowEvent implements Listener {

    @EventHandler
    public void launch(ProjectileLaunchEvent event) {
        if (event.getEntity().getType() == EntityType.ARROW) {
            ArmorStand armorStand = event.getEntity().getWorld().spawn(event.getEntity().getLocation().add(0,5,0),ArmorStand.class);
            Arrow arrow = (Arrow) event.getEntity();

            armorStand.setVisible(false);
            armorStand.setSmall(true);
            armorStand.setHelmet(new ItemStack(Material.TNT));
            arrow.setPassenger(armorStand);
        }
    }

    @EventHandler
    public void projectile(ProjectileHitEvent event) {
        if (event.getEntity().getType() == EntityType.ARROW) {
            Arrow arrow = (Arrow) event.getEntity();
            if (arrow.getPassenger() != null) {
                arrow.getPassenger().remove();
            }
            arrow.getWorld().playEffect(arrow.getLocation(), Effect.EXPLOSION,1);
            arrow.remove();

            for (Entity entity : arrow.getWorld().getNearbyEntities(arrow.getLocation(),5,5,5)) {
                if (entity.getType() == EntityType.IRON_GOLEM) {
                    IronGolem golem = (IronGolem) entity;
                    golem.damage(20);
                }
            }
        }
    }
}
