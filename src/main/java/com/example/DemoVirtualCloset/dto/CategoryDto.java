package com.example.DemoVirtualCloset.dto;

import java.util.UUID;

public class CategoryDto {
    private UUID uuid;

    private String name;

    public CategoryDto() {
    }

    public CategoryDto(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}