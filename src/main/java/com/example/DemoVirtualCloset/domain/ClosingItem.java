package com.example.DemoVirtualCloset.domain;

import java.util.UUID;

public class ClosingItem {
    private final UUID uuid;
    private final String name;
    private final UUID categoryUuid;
    private final String imageName;

    public ClosingItem(String name, UUID categoryUuid, String imageName) {
        this.name = name;
        this.categoryUuid = categoryUuid;
        this.imageName = imageName;
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

    public String getImageName() {
        return imageName;
    }

    @Override
    public String toString() {
        return "ClosingItem{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", categoryUuid=" + categoryUuid +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}