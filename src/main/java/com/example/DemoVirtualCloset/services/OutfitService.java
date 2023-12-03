package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.dto.OutfitDto;
import com.example.DemoVirtualCloset.dto.SaveOutfitDto;

import java.util.List;
import java.util.UUID;

public interface OutfitService {

    OutfitDto save(SaveOutfitDto saveOutfitDto);

    List<OutfitDto> findAllByUserUuid(UUID userUuid);

    OutfitDto generateOutfit(UUID userUuid);
}