package com.example.DemoVirtualCloset.domain;

import java.util.List;
import java.util.UUID;

public class Outfit {
    private final UUID uuid;
    private final String name;
    private final List<UUID> closingItemUuids;

    public Outfit(String name, List<UUID> closingItemUuids) {
        this.name = name;
        this.closingItemUuids = closingItemUuids;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<UUID> getClosingItemUuids() {
        return closingItemUuids;
    }

    @Override
    public String toString() {
        return "Outfit{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", closingItemUuids=" + closingItemUuids +
                '}';
    }
}