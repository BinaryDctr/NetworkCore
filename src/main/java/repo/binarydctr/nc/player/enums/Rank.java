package repo.binarydctr.nc.player.enums;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public enum Rank {

    OWNER("Owner", "[O]"),
    CO_OWNER("Co-Owner", "[C.O]"),
    ADMIN("Admin", "[A]"),
    SUB_ADMIN("Sub-Admin", "[S.A]"),
    MOD("Mod", "[M]"),
    JR_MOD("Jr.Mod", "[J.M]"),
    HELPER("Helper", "[H]"),
    DEFAULT("Default", "[D]");

    public String name;
    public String tag;

    Rank(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }
}
