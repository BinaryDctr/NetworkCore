package repo.binarydctr.nc.vote.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class VoteUpdateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private UUID uuid;
    private Integer votes;

    public VoteUpdateEvent(UUID uuid, int votes) {
        this.uuid = uuid;
        this.votes = votes;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Integer getVotes() {
        return votes;
    }
}