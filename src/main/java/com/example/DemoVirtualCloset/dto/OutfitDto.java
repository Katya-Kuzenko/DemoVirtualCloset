package com.example.DemoVirtualCloset.dto;

import java.util.List;
import java.util.UUID;

public class OutfitDto {
    private UUID uuid;
    private String name;
    private List<ClosingItemDto> closingItems;

    public OutfitDto() {
    }

    public OutfitDto(UUID uuid, String name, List<ClosingItemDto> closingItems) {
        this.uuid = uuid;
        this.name = name;
        this.closingItems = closingItems;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<ClosingItemDto> getClosingItems() {
        return closingItems;
    }

    @Override
    public String toString() {
        return "OutfitDto{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", closingItems=" + closingItems +
                '}';
    }
}