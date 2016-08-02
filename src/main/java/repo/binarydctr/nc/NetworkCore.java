package repo.binarydctr.nc;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import repo.binarydctr.nc.player.PlayerInformation;
import repo.binarydctr.nc.server.Servers;
import repo.binarydctr.nc.util.HologramManager;
import repo.binarydctr.nc.vote.Votifier;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class NetworkCore extends JavaPlugin {

    /**
     *
     * GOOGLE SPREAD SHEET: https://docs.google.com/spreadsheets/d/1VxFA3yXv7EOLVWZBWGWTHPbHt0LbyDkMDCYtDkWMxeM/edit?usp=sharing
     *
     */

    public PlayerInformation playerInformation;
    public Servers servers;
    public Votifier votifier;
    public HologramManager hologramManager;

    @Override
    public void onEnable() {
        /*
        Don't judge my config work I don't enjoy working with configs.
         */
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        saveConfig();

        if(getConfig().get("Server.id") == null || getConfig().getString("Server.id").equalsIgnoreCase("null")) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "Please fill out the config for this server, do not use same id as any other server.");
            getServer().shutdown();
            return;
        }

        playerInformation = new PlayerInformation("root", "NetworkCore", "", "3306", "localhost", this);
        servers = new Servers("root", "NetworkCore", "", "3306", "localhost", this);
        votifier = new Votifier("root", "NetworkCore", "", "3306", "localhost", this);
        hologramManager = new HologramManager(this);
    }

    @Override
    public void onDisable() {}

}