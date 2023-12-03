package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.domain.Outfit;
import com.example.DemoVirtualCloset.dto.ClosingItemDto;
import com.example.DemoVirtualCloset.dto.OutfitDto;
import com.example.DemoVirtualCloset.dto.SaveOutfitDto;
import com.example.DemoVirtualCloset.mappers.OutfitMapper;
import com.example.DemoVirtualCloset.repositories.OutfitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OutfitServiceImpl implements OutfitService {

    private final OutfitRepository outfitRepository;
    private final OutfitMapper outfitMapper;
    private final ClosingItemService closingItemService;

    public OutfitServiceImpl(OutfitRepository outfitRepository, OutfitMapper outfitMapper, ClosingItemService closingItemService) {
        this.outfitRepository = outfitRepository;
        this.outfitMapper = outfitMapper;
        this.closingItemService = closingItemService;
    }

    @Override
    public OutfitDto save(SaveOutfitDto saveOutfitDto) {
        Outfit outfit = outfitMapper.toOutfit(saveOutfitDto);
        List<ClosingItemDto> closingItems = closingItemService.findAllByIds(saveOutfitDto.getClosingItemUuids());

        outfitRepository.save(outfit);
        return outfitMapper.toDto(outfit, closingItems);
    }

    @Override
    public List<OutfitDto> findAllByUserUuid(UUID userUuid) {
        return outfitRepository.findAllByUserUuid(userUuid).stream()
                .map(outfit -> {
                    List<ClosingItemDto> closingItems = closingItemService.findAllByIds(outfit.getClosingItemUuids());
                    return outfitMapper.toDto(outfit, closingItems);
                })
                .toList();
    }
}