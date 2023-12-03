package com.example.DemoVirtualCloset.dto;

import java.util.List;
import java.util.UUID;

public class SaveOutfitDto {
    private String name;
    private UUID userUuid;
    private List<UUID> closingItemUuids;

    public SaveOutfitDto() {
    }

    public SaveOutfitDto(String name, UUID userUuid, List<UUID> closingItemIds) {
        this.name = name;
        this.userUuid = userUuid;
        this.closingItemUuids = closingItemIds;
    }

    public String getName() {
        return name;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public List<UUID> getClosingItemUuids() {
        return closingItemUuids;
    }

    @Override
    public String toString() {
        return "SaveOutfitDto{" +
                "name='" + name + '\'' +
                ", userUuid=" + userUuid +
                ", closingItemUuids=" + closingItemUuids +
                '}';
    }
}