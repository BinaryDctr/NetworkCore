package repo.binarydctr.nc.vote;

import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import repo.binarydctr.nc.NetworkCore;

import java.util.UUID;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class Votifier implements Listener {

    public NetworkCore networkCore;

    public Votifier(NetworkCore networkCore) {
        this.networkCore = networkCore;
    }

    @EventHandler
    public void onVote(VotifierEvent event) {
        String name = event.getVote().getUsername();
        try {
            String id = networkCore.playerInformation.playerCall.getUUID(name);
            UUID uuid = UUID.fromString(id);
            Player player = Bukkit.getPlayer(uuid);
            if(networkCore.playerInformation.playerCall.checkExists(uuid) == true) {
                networkCore.playerInformation.playerCall.addTokens(50, player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
