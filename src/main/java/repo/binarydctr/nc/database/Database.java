package repo.binarydctr.nc.database;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class Database {

    private Connection connection;

    private final String user;
    private final String database;
    private final String password;
    private final String port;
    private final String hostname;

    public Database(String user, String database, String password, String port, String hostname) {
        this.user = user;
        this.database = database;
        this.password = password;
        this.port = port;
        this.hostname = hostname;
    }

    public String getUser() {
        return this.user;
    }

    public String getDatabase() {
        return this.database;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPort() {
        return this.port;
    }

    public String getHost() {
        return this.hostname;
    }

    public Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDatabase(), getUser(), getPassword());
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Could not connect to MySQL server! because: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Bukkit.getConsoleSender().sendMessage("JDBC Driver not found!");
        }
        return connection;
    }

    public void checkConnection() {
        if (connection == null) {
            this.connection = openConnection();
        }
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
