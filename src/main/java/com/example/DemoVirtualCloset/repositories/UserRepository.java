package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.User;

import java.util.UUID;

public interface UserRepository extends FileRepository<UUID, User> {
}