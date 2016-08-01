package repo.binarydctr.nc.server;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import repo.binarydctr.nc.NetworkCore;
import repo.binarydctr.nc.database.Database;
import repo.binarydctr.nc.server.calls.ServerCall;
import repo.binarydctr.nc.server.enums.ServerType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class Servers extends Database implements Listener {

    public Connection connection;

    public NetworkCore networkCore;
    public ServerCall serverCall;

    public Servers(String user, String database, String password, String port, String hostname, NetworkCore networkCore) {
        super(user, database, password, port, hostname);
        this.networkCore = networkCore;
        connection = openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `Servers`(`id` varchar(36) NOT NULL, `type` varchar(36) NOT NULL, `players` int(8) NOT NULL)");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        serverCall = new ServerCall(this);
        serverCall.addServer(getServerID(), getServerType());

        networkCore.getServer().getPluginManager().registerEvents(this, networkCore);
    }

    public ServerType getServerType() {
        String stringType = networkCore.getConfig().getString("Server.type");
        for(ServerType type : ServerType.values()) {
            if(stringType.equalsIgnoreCase(type.toString())) {
                return type;
            }
        }
        return null;
    }

    public String getServerID() {
        return networkCore.getConfig().getString("Server.id");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        /*
        UPDATES DATABASE ON PLAYER COUNT
         */
        serverCall.addPlayers(getServerID());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        /*
        UPDATES DATABASE ON PLAYER COUNT
         */
        serverCall.removePlayers(getServerID());
    }

}
