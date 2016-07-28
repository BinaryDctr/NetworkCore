package repo.binarydctr.nc.player.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import repo.binarydctr.nc.player.enums.Rank;


/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class RankChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Rank rank;

    public RankChangeEvent(Player player, Rank rank) {
        this.player = player;
        this.rank = rank;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public Rank getRank() {
        return rank;
    }
}
