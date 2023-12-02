package com.example.DemoVirtualCloset.domain;

import java.util.UUID;

public class User {
    private final UUID uuid;
    private final String name;
    private final String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}