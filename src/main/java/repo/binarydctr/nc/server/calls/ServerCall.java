package repo.binarydctr.nc.server.calls;

import org.bukkit.Bukkit;
import repo.binarydctr.nc.database.DatabaseCall;
import repo.binarydctr.nc.database.Result;
import repo.binarydctr.nc.server.Servers;
import repo.binarydctr.nc.server.enums.ServerType;
import repo.binarydctr.nc.server.events.ServerPlayerUpdateEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class ServerCall extends DatabaseCall<Servers> {

    public ServerCall(Servers plugin) {
        super(plugin);
    }

    /**
     *
     * @param id
     * @return
     */

    public Result checkExists(String id) {
        plugin.checkConnection();
        try {
            PreparedStatement ps = plugin.connection.prepareStatement("SELECT id FROM Servers WHERE id=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return Result.TRUE;
            } else {
                return Result.FALSE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.ERROR;
        }
    }

    /**
     *
     * @param id
     * @param type
     * @return
     */

    public Result addServer(String id, ServerType type) {
        plugin.checkConnection();
        if(checkExists(id) == Result.FALSE) {
            try {
                PreparedStatement ps = plugin.connection.prepareStatement("INSERT INTO `Servers` VALUES (?,?,?)");
                ps.setString(1, id);
                ps.setString(2, type.toString());
                ps.setInt(3, 0);
                ps.executeUpdate();
                return Result.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.ERROR;
            }
        } else {
            System.out.println("A server with that id, already exists please change id in config.");
            return Result.ERROR;
        }
    }

    /**
     *
     * @param id
     * @return
     */

    public int getPlayers(String id) {
        plugin.checkConnection();
        if(checkExists(id) == Result.TRUE) {
            try{
                PreparedStatement ps = plugin.connection.prepareStatement("SELECT players FROM Servers WHERE id=?");
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    return rs.getInt("players");
                } else {
                    return -1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            System.out.println("Server " + id + " doesn't exists or is offline.");
            return 0;
        }
    }

    /**
     *
     * @param id
     * @return
     */

    public Result addPlayers(String id) {
        plugin.checkConnection();
        if(checkExists(id) == Result.TRUE) {
            try{
                PreparedStatement ps = plugin.connection.prepareStatement("UPDATE `Servers` SET players=? WHERE id=?");
                ps.setInt(1, getPlayers(id) + 1);
                ps.setString(2, id);
                ps.executeUpdate();
                Bukkit.getPluginManager().callEvent(new ServerPlayerUpdateEvent(id, getPlayers(id) + 1));
                return Result.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.ERROR;
            }
        } else {
            System.out.println("Server " + id + " doesn't exists or is offline.");
            return Result.ERROR;
        }
    }

    /**
     *
     * @param id
     * @return
     */

    public Result removePlayers(String id) {
        plugin.checkConnection();
        if(checkExists(id) == Result.TRUE) {
            try{
                PreparedStatement ps = plugin.connection.prepareStatement("UPDATE `Servers` SET players=? WHERE id=?");
                ps.setInt(1, getPlayers(id) - 1);
                ps.setString(2, id);
                ps.executeUpdate();
                Bukkit.getPluginManager().callEvent(new ServerPlayerUpdateEvent(id, getPlayers(id) - 1));
                return Result.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.ERROR;
            }
        } else {
            System.out.println("Server " + id + " doesn't exists or is offline.");
            return Result.ERROR;
        }
    }
}
