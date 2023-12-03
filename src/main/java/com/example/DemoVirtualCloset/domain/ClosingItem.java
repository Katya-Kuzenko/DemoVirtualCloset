package com.example.DemoVirtualCloset.domain;

import java.util.UUID;

public class ClosingItem {
    private UUID uuid;
    private String name;
    private UUID categoryUuid;
    private UUID userUuid;
    private String image;

    public ClosingItem() {
    }

    public ClosingItem(String name, UUID categoryUuid, UUID userUuid, String image) {
        this.name = name;
        this.categoryUuid = categoryUuid;
        this.userUuid = userUuid;
        this.image = image;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getCategoryUuid() {
        return categoryUuid;
    }

    public String getImage() {
        return image;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    @Override
    public String toString() {
        return "ClosingItem{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", categoryUuid=" + categoryUuid +
                ", userUuid=" + userUuid +
                '}';
    }
}