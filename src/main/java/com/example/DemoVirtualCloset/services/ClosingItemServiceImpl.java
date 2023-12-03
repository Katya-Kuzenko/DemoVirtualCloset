package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.domain.Category;
import com.example.DemoVirtualCloset.domain.ClosingItem;
import com.example.DemoVirtualCloset.dto.CategoryDto;
import com.example.DemoVirtualCloset.dto.ClosingItemDto;
import com.example.DemoVirtualCloset.dto.SaveClosingItemDto;
import com.example.DemoVirtualCloset.exceptions.NotFoundException;
import com.example.DemoVirtualCloset.mappers.CategoryMapper;
import com.example.DemoVirtualCloset.mappers.ClosingItemMapper;
import com.example.DemoVirtualCloset.repositories.CategoryRepository;
import com.example.DemoVirtualCloset.repositories.ClosingItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClosingItemServiceImpl implements ClosingItemService {
    private final ClosingItemRepository closingItemRepository;

    private final ClosingItemMapper closingItemMapper;

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public ClosingItemServiceImpl(ClosingItemRepository closingItemRepository, ClosingItemMapper closingItemMapper, CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.closingItemRepository = closingItemRepository;
        this.closingItemMapper = closingItemMapper;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ClosingItemDto save(SaveClosingItemDto saveClosingItemDto) {
        ClosingItem closingItem = closingItemMapper.toClosingItem(saveClosingItemDto);
        Category category = getClosingItemCategory(closingItem);

        closingItemRepository.save(closingItem);
        return closingItemMapper.toDto(closingItem, categoryMapper.toDto(category));
    }

    @Override
    public List<ClosingItemDto> findAllByUserUuid(UUID userUuid) {
        List<ClosingItem> closingItems = closingItemRepository.findAllByUserUuid(userUuid);
        Map<UUID, CategoryDto> categories = closingItems.stream()
                .map(this::getClosingItemCategory)
                .collect(Collectors.toMap(Category::getUuid, categoryMapper::toDto, (el1, el2) -> el1));
        return closingItems.stream()
                .map(el -> closingItemMapper.toDto(el, categories.get(el.getCategoryUuid())))
                .toList();
    }

    private Category getClosingItemCategory(ClosingItem closingItem) {
        return categoryRepository.findById(closingItem.getCategoryUuid())
                .orElseThrow(() -> new NotFoundException("Category not found by uuid " + closingItem.getCategoryUuid()));
    }
}