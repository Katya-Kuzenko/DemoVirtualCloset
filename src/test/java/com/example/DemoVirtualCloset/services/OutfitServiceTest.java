package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.domain.Outfit;
import com.example.DemoVirtualCloset.dto.CategoryDto;
import com.example.DemoVirtualCloset.dto.ClosingItemDto;
import com.example.DemoVirtualCloset.dto.OutfitDto;
import com.example.DemoVirtualCloset.dto.SaveOutfitDto;
import com.example.DemoVirtualCloset.mappers.OutfitMapper;
import com.example.DemoVirtualCloset.repositories.OutfitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OutfitServiceTest {

    @Mock
    private OutfitRepository outfitRepository;
    @Mock
    private ClosingItemService closingItemService;

    @Captor
    private ArgumentCaptor<Outfit> outfitCaptor;

    private OutfitService outfitService;

    @BeforeEach
    public void init() {
        outfitService = new OutfitServiceImpl(outfitRepository, new OutfitMapper(), closingItemService);
    }

    @Test
    public void saveTest() {
        SaveOutfitDto saveOutfitDto = new SaveOutfitDto("name", UUID.randomUUID(), List.of(UUID.randomUUID(), UUID.randomUUID()));
        CategoryDto category1 = new CategoryDto(UUID.randomUUID(), "categoryName1");
        CategoryDto category2 = new CategoryDto(UUID.randomUUID(), "categoryName2");

        ClosingItemDto closingItem1 = new ClosingItemDto(UUID.randomUUID(), "name1", category1, "image");
        ClosingItemDto closingItem2 = new ClosingItemDto(UUID.randomUUID(), "name2", category2, "image2");

        when(closingItemService.findAllByIds(saveOutfitDto.getClosingItemUuids())).thenReturn(List.of(closingItem1, closingItem2));

        outfitService.save(saveOutfitDto);

        verify(outfitRepository).save(outfitCaptor.capture());
        Outfit outfit = outfitCaptor.getValue();
        assertEquals(saveOutfitDto.getName(), outfit.getName());
        assertEquals(saveOutfitDto.getUserUuid(), outfit.getUserUuid());
        assertIterableEquals(saveOutfitDto.getClosingItemUuids(), outfit.getClosingItemUuids());
    }

    @Test
    public void findAllByUserUuidTest() {
        UUID userUuid = UUID.randomUUID();
        Outfit outfit1 = new Outfit("name", userUuid, List.of(UUID.randomUUID()));
        Outfit outfit2 = new Outfit("name", userUuid, List.of(UUID.randomUUID()));

        when(outfitRepository.findAllByUserUuid(userUuid)).thenReturn(List.of(outfit1, outfit2));

        CategoryDto category1 = new CategoryDto(UUID.randomUUID(), "categoryName1");
        CategoryDto category2 = new CategoryDto(UUID.randomUUID(), "categoryName2");

        ClosingItemDto closingItem1 = new ClosingItemDto(UUID.randomUUID(), "name1", category1, "image");
        ClosingItemDto closingItem2 = new ClosingItemDto(UUID.randomUUID(), "name2", category2, "image2");

        when(closingItemService.findAllByIds(outfit1.getClosingItemUuids())).thenReturn(List.of(closingItem1));
        when(closingItemService.findAllByIds(outfit2.getClosingItemUuids())).thenReturn(List.of(closingItem2));

        List<OutfitDto> outfits = outfitService.findAllByUserUuid(userUuid);

        assertEquals(2, outfits.size());
        OutfitDto outfitDto1 = outfits.get(0);
        OutfitDto outfitDto2 = outfits.get(1);
        assertEqual(outfit1, outfitDto1, List.of(closingItem1));
        assertEqual(outfit2, outfitDto2, List.of(closingItem2));
    }

    private static void assertEqual(Outfit outfit, OutfitDto outfitDto, List<ClosingItemDto> closingItems) {
        assertEquals(outfit.getUuid(), outfitDto.getUuid());
        assertEquals(outfit.getName(), outfitDto.getName());
        assertIterableEquals(closingItems, outfitDto.getClosingItems());
    }
}