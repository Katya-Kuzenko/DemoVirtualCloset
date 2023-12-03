package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.dto.ClosingItemDto;
import com.example.DemoVirtualCloset.dto.SaveClosingItemDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClosingItemService {

    ClosingItemDto save(SaveClosingItemDto saveClosingItemDto);

    List<ClosingItemDto> findAllByUserUuid(UUID userUuid);

    Optional<ClosingItemDto> findById(UUID uuid);

    List<ClosingItemDto> findAllByIds(List<UUID> ids);
}