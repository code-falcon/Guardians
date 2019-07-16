package com.falkon.guardians.entity;

import com.falkon.guardians.Guardians;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class GuardianEntity {

    private static IronGolem ironGolem;

    public static void spawn() {
        ironGolem= Bukkit.getWorld("arena").spawn(new Location(Bukkit.getWorld("arena"),3322.5, 6, 1313.5, -1753f, -1.5f),IronGolem.class);

        ironGolem.setCustomName("null");
        ironGolem.setCustomNameVisible(true);
        ironGolem.setMaxHealth(1000);
        ironGolem.setHealth(ironGolem.getMaxHealth());

        move(ironGolem,new Location(Bukkit.getWorld("arena"),3333, 6, 1195),1);
    }

    public static IronGolem getIronGolem() {
        return ironGolem;
    }

    public static void setIronGolem(IronGolem ironGolem) {
        GuardianEntity.ironGolem = ironGolem;
    }

    public static void move(LivingEntity entity, Location moveTo, float speed) {
        new BukkitRunnable() {

            public void run() {
                if (entity.getLocation().distance(moveTo) > 0.3) {
                    float yaw = getRotations(entity.getLocation(),moveTo)[0];

                    Vector direction = new Vector(-Math.sin(yaw * 3.1415927F / 180.0F) * (float) 1 * 0.5F, 0, Math.cos(yaw * 3.1415927F / 180.0F) * (float) 1 * 0.5F).multiply(0.25);

                    if(entity.getLocation().getY() - moveTo.getY() > 0 && entity.isOnGround()) {
                        direction.setY(Math.min(0.42, entity.getLocation().getY() - moveTo.getY()));
                    }
                    entity.setVelocity(direction);
                }
            }

        }.runTaskTimer(Guardians.getGuardians(),0L,1L);
    }

    public static float[] getRotations(Location one, Location two) {
        double diffX = two.getX() - one.getX();
        double diffZ = two.getZ() - one.getZ();
        double diffY = two.getY() + 2.0 - 0.4 - (one.getY() + 2.0);
        double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        float pitch = (float) (-Math.atan2(diffY, dist) * 180.0 / 3.141592653589793);
        return new float[]{yaw, pitch};
    }

}
