package repo.binarydctr.nc.vote;

import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import repo.binarydctr.nc.NetworkCore;
import repo.binarydctr.nc.database.Database;
import repo.binarydctr.nc.vote.calls.VoteCall;

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
public class Votifier extends Database implements Listener {

    public Connection connection;

    public VoteCall voteCall;
    public NetworkCore networkCore;

    public Votifier(String user, String database, String password, String port, String hostname, NetworkCore networkCore) {
        super(user, database, password, port, hostname);
        this.networkCore = networkCore;
        connection = openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `Votifier`(`uuid` varchar(36) NOT NULL, `name` varchar(36) NOT NULL, `votes` int(8) NOT NULL)");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        voteCall = new VoteCall(this);
    }

    @EventHandler
    public void onVote(VotifierEvent event) {
        String name = event.getVote().getUsername();
        String id = networkCore.playerInformation.playerCall.getUUID(name);
        UUID uuid = UUID.fromString(id);
        Player player = Bukkit.getPlayer(uuid);
        networkCore.playerInformation.playerCall.addTokens(50, player);
        voteCall.setVotes(uuid, voteCall.getVotes(uuid) + 1);
    }
}
