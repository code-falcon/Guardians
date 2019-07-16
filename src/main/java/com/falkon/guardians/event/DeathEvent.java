package com.falkon.guardians.event;

import com.falkon.guardians.entity.GuardianEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathEvent implements Listener {

    @EventHandler
    public void death(EntityDeathEvent event) {
        if (GuardianEntity.getIronGolem() != null) {
            if (event.getEntity() == GuardianEntity.getIronGolem()) {

            }
        }
    }
}
