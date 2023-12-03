package com.example.DemoVirtualCloset.dto;

public class RegisterUserDto {
    private String name;
    private String password;

    public RegisterUserDto() {
    }

    public RegisterUserDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "SaveUserDto{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}