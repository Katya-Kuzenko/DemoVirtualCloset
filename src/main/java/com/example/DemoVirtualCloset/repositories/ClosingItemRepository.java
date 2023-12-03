package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.ClosingItem;

import java.util.List;
import java.util.UUID;

public interface ClosingItemRepository extends FileRepository<UUID, ClosingItem> {

    List<ClosingItem> findAllByUserUuid(UUID userUuid);
}