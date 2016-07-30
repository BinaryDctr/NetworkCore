package repo.binarydctr.nc.util;

import org.bukkit.Location;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class Hologram {

    public String message;
    public Location location;
    public Boolean perm;

    public Hologram(String message, Location location, Boolean perm) {
        this.message = message;
        this.location = location;
        this.perm = perm;
    }

    public String getMessage() {
        return message;
    }

    public Location getLocation() {
        return location;
    }

    public Boolean getPerm() {
        return perm;
    }
}
