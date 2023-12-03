package com.example.DemoVirtualCloset.dto;

import java.util.UUID;

public class SaveClosingItemDto {
    private String name;
    private UUID categoryUuid;
    private UUID userUuid;
    private String image;

    public SaveClosingItemDto() {
    }

    public SaveClosingItemDto(String name, UUID categoryUuid, UUID userUuid, String image) {
        this.name = name;
        this.categoryUuid = categoryUuid;
        this.userUuid = userUuid;
        this.image = image;
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
        return "SaveClosingItemDto{" +
                "name='" + name + '\'' +
                ", categoryUuid=" + categoryUuid +
                ", userUuid=" + userUuid +
                '}';
    }
}