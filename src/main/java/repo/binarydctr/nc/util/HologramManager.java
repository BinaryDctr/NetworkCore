package repo.binarydctr.nc.util;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class HologramManager {

    public JavaPlugin plugin;

    public HologramManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public HashMap<String, Hologram> holograms = new HashMap<>();

    public List<String> hologramNames = new ArrayList<>();

    public void create(String name, Hologram hologram) {
        ArmorStand armorStand = (ArmorStand) hologram.getLocation().getWorld().spawnEntity(hologram.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setCustomName(hologram.getMessage());
        armorStand.setCustomNameVisible(true);

        hologramNames.add(name);
        holograms.put(name, hologram);
    }

}
