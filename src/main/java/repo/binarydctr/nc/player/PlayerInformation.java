package repo.binarydctr.nc.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import repo.binarydctr.nc.NetworkCore;
import repo.binarydctr.nc.database.Database;
import repo.binarydctr.nc.player.calls.PlayerCall;
import repo.binarydctr.nc.player.enums.Rank;
import repo.binarydctr.nc.util.ChatFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class PlayerInformation extends Database implements Listener {

    public Connection connection;
    public PlayerCall playerCall;

    public NetworkCore networkCore;

    public PlayerInformation(String user, String database, String password, String port, String hostname, NetworkCore networkCore) {
        super(user, database, password, port, hostname);
        this.networkCore = networkCore;
        connection = openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `PlayerInfo`(`uuid` varchar(36) NOT NULL, `name` varchar(36) NOT NULL, `tokens` int(8) NOT NULL, `rank` varchar(36) NOT NULL)");
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        playerCall = new PlayerCall(this);

        networkCore.getServer().getPluginManager().registerEvents(this, networkCore);
    }

    public Rank getRankFromString(String rank) {
        for(Rank ranks : Rank.values()) {
            if(ranks.getName().equalsIgnoreCase(rank)) {
                return ranks;
            }
        }
        return null;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        /**
         * ADDS PLAYER TO DATABASE AND CHECKS IF THEIR NAME IS UP TO DATE WITH THE DATABASE.
         */
        if(playerCall.checkExists(uuid) == false) {
            playerCall.createPlayerInfo(player);
        } else if(playerCall.checkExists(uuid) == true) {
            playerCall.updatePlayerInfo(player);
        }

        event.setJoinMessage(ChatFormat.format("Join", player.getName()));
    }

}
