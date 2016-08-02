package repo.binarydctr.nc.vote.calls;

import org.bukkit.Bukkit;
import repo.binarydctr.nc.database.DatabaseCall;
import repo.binarydctr.nc.database.Result;
import repo.binarydctr.nc.vote.Votifier;
import repo.binarydctr.nc.vote.events.VoteUpdateEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class VoteCall extends DatabaseCall<Votifier> {

    /*
    KEEPS TRACK OF THE AMOUNT OF TIMES THE PLAYER VOTED FOR YOUR SERVER.
     */

    public VoteCall(Votifier plugin) {
        super(plugin);
    }

    /**
     *
     * @param uuid
     * @return
     */

    public Result checkExists(UUID uuid) {
        plugin.checkConnection();
        try {
            PreparedStatement ps = plugin.connection.prepareStatement("SELECT uuid FROM Votifier WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return Result.TRUE;
            } else {
                return Result.FALSE;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return Result.ERROR;
        }
    }

    /**
     *
     * @param uuid
     * @return
     */

    public String getName(UUID uuid) {
        plugin.checkConnection();
        if(checkExists(uuid) == Result.TRUE) {
            try {
                PreparedStatement ps = plugin.connection.prepareStatement("SELECT name FROM Votifier WHERE uuid=?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    return rs.getString("name");
                } else {
                    return null;
                }
            } catch(SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("This player isn't in the database.");
            return null;
        }
    }

    /**
     *
     * @param name
     * @return
     */

    public String getUUID(String name) {
        plugin.checkConnection();
        try {
            PreparedStatement ps = plugin.connection.prepareStatement("SELECT uuid FROM Votifier WHERE name=?");
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("uuid");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param uuid
     * @return
     */

    public int getVotes(UUID uuid) {
        plugin.checkConnection();
        if(checkExists(uuid) == Result.TRUE) {
            try {
                PreparedStatement ps = plugin.connection.prepareStatement("SELECT votes FROM Votifier WHERE uuid=?");
                ps.setString(1, uuid.toString());
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("votes");
                } else {
                    return -1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            System.out.println("This player isn't in the database.");
            return 0;
        }
    }

    /**
     *
     * @param uuid
     * @param votes
     * @return
     */

    public Result setVotes(UUID uuid, int votes) {
        plugin.checkConnection();
        if(checkExists(uuid) == Result.TRUE) {
            try {
                PreparedStatement ps = plugin.connection.prepareStatement("UPDATE `Votifier` SET votes=? WHERE uuid=?");
                ps.setInt(1, votes);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
                Bukkit.getPluginManager().callEvent(new VoteUpdateEvent(uuid, votes));
                return Result.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.ERROR;
            }
        } else {
            System.out.println("This player isn't in the database.");
            return Result.ERROR;
        }
    }
}
