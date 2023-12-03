package com.example.DemoVirtualCloset.dto;

import java.util.UUID;

public class UserDto {
    private UUID uuid;
    private String name;

    public UserDto() {
    }

    public UserDto(UUID uuid, String name) {
        this.name = name;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}