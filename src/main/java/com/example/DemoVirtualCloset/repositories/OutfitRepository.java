package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.Outfit;

import java.util.List;
import java.util.UUID;

public interface OutfitRepository extends FileRepository<UUID, Outfit> {

    List<Outfit> findAllByUserUuid(UUID userUuid);
}