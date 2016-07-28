package repo.binarydctr.nc.util;

import org.bukkit.ChatColor;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class ChatFormat {

    public static String format(String header, String message) {
        return ChatColor.LIGHT_PURPLE +""+ ChatColor.BOLD + header + ChatColor.RESET + " : "
                + ChatColor.GRAY + message;
    }

}
