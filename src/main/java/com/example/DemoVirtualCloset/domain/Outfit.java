package com.example.DemoVirtualCloset.domain;

import java.util.List;
import java.util.UUID;

public class Outfit {
    private UUID uuid;
    private String name;
    private UUID userUuid;
    private List<UUID> closingItemUuids;

    public Outfit() {
    }

    public Outfit(String name, UUID userUuid, List<UUID> closingItemUuids) {
        this.name = name;
        this.closingItemUuids = closingItemUuids;
        this.userUuid = userUuid;
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

    public UUID getUserUuid() {
        return userUuid;
    }

    @Override
    public String toString() {
        return "Outfit{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", userUuid=" + userUuid +
                ", closingItemUuids=" + closingItemUuids +
                '}';
    }
}