package repo.binarydctr.nc.player;

import repo.binarydctr.nc.database.Database;
import repo.binarydctr.nc.player.calls.PlayerCall;
import repo.binarydctr.nc.player.enums.Rank;

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
public class PlayerInformation extends Database {

    public Connection connection;
    public PlayerCall playerCall;

    public PlayerInformation(String user, String database, String password, String port, String hostname) {
        super(user, database, password, port, hostname);
        connection = openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `PlayerInfo`(`uuid` varchar(36) NOT NULL, `name` varchar(36) NOT NULL, `tokens` int(8) NOT NULL, `rank` varchar(36) NOT NULL)");
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        playerCall = new PlayerCall(this);
    }

    public Rank getRankFromString(String rank) {
        for(Rank ranks : Rank.values()) {
            if(ranks.getName().equalsIgnoreCase(rank)) {
                return ranks;
            }
        }
        return null;
    }

}
