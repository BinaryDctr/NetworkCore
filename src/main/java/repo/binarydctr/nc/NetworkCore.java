package repo.binarydctr.nc;

import org.bukkit.plugin.java.JavaPlugin;
import repo.binarydctr.nc.player.PlayerInformation;

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

    @Override
    public void onEnable() {
        new PlayerInformation("root", "NetworkCore", "", "3306", "localhost");
    }

    @Override
    public void onDisable() {

    }
}