package repo.binarydctr.nc.database;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public abstract class DatabaseCall<PluginType extends Database> {

    protected PluginType plugin;

    public DatabaseCall(PluginType plugin) {
        this.plugin = plugin;
    }

    public PluginType getPlugin() {
        return plugin;
    }
}
