package repo.binarydctr.nc.player.calls;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import repo.binarydctr.nc.database.DatabaseCall;
import repo.binarydctr.nc.database.Result;
import repo.binarydctr.nc.player.PlayerInformation;
import repo.binarydctr.nc.player.enums.Rank;
import repo.binarydctr.nc.player.events.RankChangeEvent;
import repo.binarydctr.nc.player.events.TokenChangeEvent;

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
     * Get player's name using their UUID.
     *
     */

    public String getName(UUID uuid) {
        plugin.checkConnection();
        if(checkExists(uuid) == true) {
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
        }
        return null;
    }

    /**
     *
     * @param name
     * @return
     *
     * Get player's uuid with name.
     *
     */

    public String getUUID(String name) {
        plugin.checkConnection();
            try {
                PreparedStatement preparedStatement = plugin.connection.prepareStatement("SELECT uuid FROM PlayerInfo WHERE name=?");
                preparedStatement.setString(1, name);
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
     * @param rank
     * @param player
     * @return
     *
     * Sets player's rank.
     *
     */

    public Result setRank(Rank rank, Player player) {
        plugin.checkConnection();
        UUID uuid = player.getUniqueId();
        if(checkExists(uuid) == true) {
            try {
                PreparedStatement preparedStatement = plugin.connection.prepareStatement("UPDATE `PlayerInfo` SET rank=? WHERE uuid=?");
                preparedStatement.setString(1, rank.getName());
                preparedStatement.setString(2, uuid.toString());
                preparedStatement.executeUpdate();
                Bukkit.getPluginManager().callEvent(new RankChangeEvent(player, rank));
                return Result.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.ERROR;
            }
        }
        return Result.FAILURE;
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
     * @param amount
     * @param player
     * @return
     *
     * Adds tokens to player's current amount.
     *
     */

    public Result addTokens(Integer amount, Player player) {
        plugin.checkConnection();
        UUID uuid = player.getUniqueId();
        if(checkExists(uuid) == true) {
            try {
                PreparedStatement preparedStatement = plugin.connection.prepareStatement("UPDATE `PlayerInfo` SET tokens=? WHERE uuid=?");
                preparedStatement.setInt(1, getTokens(uuid) + amount);
                preparedStatement.setString(2, uuid.toString());
                preparedStatement.executeUpdate();
                Bukkit.getPluginManager().callEvent(new TokenChangeEvent(player, getTokens(uuid) + amount));
                return Result.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.ERROR;
            }
        }
        return Result.FAILURE;
    }

    /**
     *
     * @param amount
     * @param player
     * @return
     *
     * Removes tokens to player's current amount.
     *
     */

    public Result removeTokens(Integer amount, Player player) {
        plugin.checkConnection();
        UUID uuid = player.getUniqueId();
        if(checkExists(uuid) == true) {
            try {
                PreparedStatement preparedStatement = plugin.connection.prepareStatement("UPDATE `PlayerInfo` SET tokens=? WHERE uuid=?");
                preparedStatement.setInt(1, getTokens(uuid) - amount);
                preparedStatement.setString(2, uuid.toString());
                preparedStatement.executeUpdate();
                Bukkit.getPluginManager().callEvent(new TokenChangeEvent(player, getTokens(uuid) - amount));
                return Result.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.ERROR;
            }
        }
        return Result.FAILURE;
    }

    /**
     *
     * @param amount
     * @param player
     * @return
     *
     * Sets player's token amount overriding there current token amount.
     *
     */

    public Result setTokens(Integer amount, Player player) {
        plugin.checkConnection();
        UUID uuid = player.getUniqueId();
        if(checkExists(uuid) == true) {
            try {
                PreparedStatement preparedStatement = plugin.connection.prepareStatement("UPDATE `PlayerInfo` SET tokens=? WHERE uuid=?");
                preparedStatement.setInt(1, amount);
                preparedStatement.setString(2, uuid.toString());
                preparedStatement.executeUpdate();
                Bukkit.getPluginManager().callEvent(new TokenChangeEvent(player, amount));
                return Result.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.ERROR;
            }
        }
        return Result.FAILURE;
    }

    /**
     *
     * @param player
     *
     * Adds player to database.
     *
     */

    public Result createPlayerInfo(Player player) {
        plugin.checkConnection();
        if(checkExists(player.getUniqueId()) == false) {
            try {
                PreparedStatement preparedStatement = plugin.connection.prepareStatement("INSERT INTO `PlayerInfo` VALUES(?,?,?,?)");
                preparedStatement.setString(1, player.getUniqueId().toString());
                preparedStatement.setString(2, player.getName());
                preparedStatement.setInt(3, 0);
                preparedStatement.setString(4, Rank.DEFAULT.getName());
                preparedStatement.executeUpdate();
                return Result.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.ERROR;
            }
        }
        return Result.FAILURE;
    }

    /**
     *
     * @param player
     * @return
     *
     * Updates player's name in the database.
     *
     */

    public Result updatePlayerInfo(Player player) {
        plugin.checkConnection();
        if(checkExists(player.getUniqueId()) == true) {
            if (!getName(player.getUniqueId()).equalsIgnoreCase(player.getName())) {
                try {
                    PreparedStatement preparedStatement = plugin.connection.prepareStatement("UPDATE `PlayerInfo` SET name=? WHERE uuid=?");
                    preparedStatement.setString(1, player.getName());
                    preparedStatement.setString(2, player.getUniqueId().toString());
                    preparedStatement.executeUpdate();
                    return Result.SUCCESS;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return Result.ERROR;
                }
            }
        }
        return Result.FAILURE;
    }
}

