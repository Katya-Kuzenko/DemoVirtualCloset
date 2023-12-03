package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.domain.Category;
import com.example.DemoVirtualCloset.domain.ClosingItem;
import com.example.DemoVirtualCloset.dto.ClosingItemDto;
import com.example.DemoVirtualCloset.dto.SaveClosingItemDto;
import com.example.DemoVirtualCloset.mappers.CategoryMapper;
import com.example.DemoVirtualCloset.mappers.ClosingItemMapper;
import com.example.DemoVirtualCloset.repositories.CategoryRepository;
import com.example.DemoVirtualCloset.repositories.ClosingItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClosingItemServiceTest {

    @Mock
    private ClosingItemRepository closingItemRepository;
    @Mock
    private CategoryRepository categoryRepository;

    private ClosingItemService closingItemService;

    @Captor
    private ArgumentCaptor<ClosingItem> closingItemCaptor;

    @BeforeEach
    public void init() {
        closingItemService = new ClosingItemServiceImpl(closingItemRepository, new ClosingItemMapper(), categoryRepository, new CategoryMapper());
    }

    @Test
    public void saveTest() {
        SaveClosingItemDto saveClosingItemDto = new SaveClosingItemDto("name", UUID.randomUUID(), UUID.randomUUID(), "image");
        Category category = new Category(saveClosingItemDto.getCategoryUuid(), "categoryName");
        when(categoryRepository.findById(saveClosingItemDto.getCategoryUuid())).thenReturn(Optional.of(category));

        closingItemService.save(saveClosingItemDto);

        verify(closingItemRepository).save(closingItemCaptor.capture());
        ClosingItem closingItem = closingItemCaptor.getValue();
        assertEquals(saveClosingItemDto.getName(), closingItem.getName());
        assertEquals(saveClosingItemDto.getUserUuid(), closingItem.getUserUuid());
        assertEquals(saveClosingItemDto.getImage(), closingItem.getImage());
        assertEquals(saveClosingItemDto.getCategoryUuid(), closingItem.getCategoryUuid());
    }

    @Test
    public void findAllByUserUuidTest() {
        UUID userUuid = UUID.randomUUID();
        ClosingItem closingItem1 = new ClosingItem("name", UUID.randomUUID(), userUuid, "image");
        ClosingItem closingItem2 = new ClosingItem("name2", UUID.randomUUID(), userUuid, "image2");
        when(closingItemRepository.findAllByUserUuid(userUuid)).thenReturn(List.of(closingItem1, closingItem2));

        Category category1 = new Category(closingItem1.getCategoryUuid(), "categoryName1");
        Category category2 = new Category(closingItem2.getCategoryUuid(), "categoryName2");
        when(categoryRepository.findById(category1.getUuid())).thenReturn(Optional.of(category1));
        when(categoryRepository.findById(category2.getUuid())).thenReturn(Optional.of(category2));

        List<ClosingItemDto> closingItems = closingItemService.findAllByUserUuid(userUuid);
        assertEquals(2, closingItems.size());
        ClosingItemDto closingItemDto1 = closingItems.get(0);
        ClosingItemDto closingItemDto2 = closingItems.get(1);
        assertEqual(closingItem1, closingItemDto1, category1);
        assertEqual(closingItem2, closingItemDto2, category2);
    }

    private static void assertEqual(ClosingItem closingItem, ClosingItemDto closingItemDto, Category category) {
        assertEquals(closingItem.getUuid(), closingItemDto.getUuid());
        assertEquals(closingItem.getName(), closingItemDto.getName());
        assertEquals(closingItem.getImage(), closingItemDto.getImage());
        assertEquals(category.getUuid(), closingItemDto.getCategory().getUuid());
        assertEquals(category.getName(), closingItemDto.getCategory().getName());
    }


}