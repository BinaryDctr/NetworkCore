package repo.binarydctr.nc.player.calls;

import org.bukkit.entity.Player;
import repo.binarydctr.nc.database.Database;
import repo.binarydctr.nc.database.DatabaseCall;
import repo.binarydctr.nc.player.PlayerInformation;
import repo.binarydctr.nc.player.enums.Rank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class PlayerCall extends DatabaseCall<PlayerInformation> {

    public PlayerCall(PlayerInformation plugin) {
        super(plugin);
    }

    /**
     *
     * @param uuid
     * @return
     *
     * Get player's name using their UUID.
     *
     */

    public String getName(UUID uuid) {
        plugin.checkConnection();
        try {
            PreparedStatement preparedStatement = plugin.connection.prepareStatement("SELECT name FROM PlayerInfo WHERE uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param uuid
     * @return
     *
     * Check if player is in the database.
     *
     */

    public boolean checkExists(UUID uuid) {
        plugin.checkConnection();
        try {
            PreparedStatement preparedStatement = plugin.connection.prepareStatement("SELECT uuid FROM PlayerInfo WHERE uuid=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param uuid
     * @return
     *
     * Get player's rank using their UUID.
     *
     */

    public Rank getRank(UUID uuid) {
        plugin.checkConnection();
        if(checkExists(uuid) == true) {
            try {
                PreparedStatement preparedStatement = plugin.connection.prepareStatement("SELECT rank FROM PlayerInfo WHERE uuid=?");
                preparedStatement.setString(1, uuid.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return plugin.getRankFromString(resultSet.getString("rank"));
                } else {
                    return Rank.DEFAULT;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Rank.DEFAULT;
        }
        return Rank.DEFAULT;
    }

    /**
     *
     * @param uuid
     * @return
     *
     * Get the amount of tokens a player has using their UUID.
     *
     */

    public Integer getTokens(UUID uuid) {
        plugin.checkConnection();
        if(checkExists(uuid) == true) {
            try {
                PreparedStatement preparedStatement = plugin.connection.prepareStatement("SELECT tokens FROM PlayerInfo WHERE uuid=?");
                preparedStatement.setString(1, uuid.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("uuid");
                } else {
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }
        return 0;
    }

    /**
     *
     * @param player
     *
     * Adds player to database.
     *
     */

    public void createPlayerInfo(Player player) {
        plugin.checkConnection();
        //TODO: make rest during next ep.
    }
}

