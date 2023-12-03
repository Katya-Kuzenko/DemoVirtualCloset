package com.example.DemoVirtualCloset.domain;

import java.util.UUID;

public class Category {
    private UUID uuid;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this(UUID.randomUUID(), name);
    }

    public Category(UUID uuid, String name) {
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
        return "Category{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}