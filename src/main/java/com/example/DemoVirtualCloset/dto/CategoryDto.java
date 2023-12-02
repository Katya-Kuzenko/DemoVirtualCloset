package com.example.DemoVirtualCloset.dto;

import java.io.Serializable;
import java.util.UUID;

public class CategoryDto implements Serializable {

    public UUID uuid;

    public String nameCategory;

    public String nameCategoryItem;

    public String itemImage;

    @Override
    public String toString() {
        return String.format("CategoryDto{uuid=%b, nameCategory='%s', nameCategoryItem='%s', itemImage='%s'}", uuid, nameCategory, nameCategoryItem, itemImage);
    }

    public UUID getCategoryId() {
        return uuid;
    }

    public void setCategoryId(UUID categoryId) {
        this.uuid = categoryId;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameCategoryItem() {
        return nameCategoryItem;
    }

    public void setNameCategoryItem(String nameCategoryItemDto) {
        this.nameCategoryItem = nameCategoryItemDto;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImageDto) {
        this.itemImage = itemImageDto;
    }
}
