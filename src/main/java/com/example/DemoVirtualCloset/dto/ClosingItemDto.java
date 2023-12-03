package com.example.DemoVirtualCloset.dto;

import java.util.UUID;

public class ClosingItemDto {
    private UUID uuid;
    private String name;
    private CategoryDto category;
    private String image;

    public ClosingItemDto() {
    }

    public ClosingItemDto(UUID uuid, String name, CategoryDto category, String image) {
        this.uuid = uuid;
        this.name = name;
        this.category = category;
        this.image = image;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "ClosingItemDto{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", image='" + image + '\'' +
                '}';
    }
}