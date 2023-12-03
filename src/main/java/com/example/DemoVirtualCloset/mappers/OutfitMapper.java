package com.example.DemoVirtualCloset.mappers;

import com.example.DemoVirtualCloset.domain.Outfit;
import com.example.DemoVirtualCloset.dto.ClosingItemDto;
import com.example.DemoVirtualCloset.dto.OutfitDto;
import com.example.DemoVirtualCloset.dto.SaveOutfitDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OutfitMapper {

    public Outfit toOutfit(SaveOutfitDto saveOutfitDto) {
        return new Outfit(saveOutfitDto.getName(), saveOutfitDto.getUserUuid(), saveOutfitDto.getClosingItemUuids());
    }

    public OutfitDto toDto(Outfit outfit, List<ClosingItemDto> closingItems) {
        return new OutfitDto(outfit.getUuid(), outfit.getName(), closingItems);
    }
}