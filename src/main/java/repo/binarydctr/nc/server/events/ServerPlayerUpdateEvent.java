package repo.binarydctr.nc.server.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ServerPlayerUpdateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private String id;
    private Integer amount;

    public ServerPlayerUpdateEvent(String id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public String getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }
}