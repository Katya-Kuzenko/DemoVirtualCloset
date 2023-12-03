package com.example.DemoVirtualCloset.mappers;

import com.example.DemoVirtualCloset.domain.ClosingItem;
import com.example.DemoVirtualCloset.dto.CategoryDto;
import com.example.DemoVirtualCloset.dto.ClosingItemDto;
import com.example.DemoVirtualCloset.dto.SaveClosingItemDto;
import org.springframework.stereotype.Component;

@Component
public class ClosingItemMapper {

    public ClosingItem toClosingItem(SaveClosingItemDto saveClosingItemDto) {
        return new ClosingItem(saveClosingItemDto.getName(), saveClosingItemDto.getCategoryUuid(), saveClosingItemDto.getUserUuid(), saveClosingItemDto.getImage());
    }

    public ClosingItemDto toDto(ClosingItem closingItem, CategoryDto category) {
        return new ClosingItemDto(closingItem.getUuid(), closingItem.getName(), category, closingItem.getImage());
    }
}