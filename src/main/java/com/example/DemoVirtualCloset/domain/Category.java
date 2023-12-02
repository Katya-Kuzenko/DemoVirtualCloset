package com.example.DemoVirtualCloset.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "category_items")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nameCategory;

    private String nameCategoryItem;

    private String itemImage;

    @Override
    public String toString() {
        return String.format("Category{id=%d, nameCategory='%s', nameCategoryItem='%s', itemImage='%s'}", id, nameCategory, nameCategoryItem, itemImage);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setNameCategoryItem(String nameCategoryItem) {
        this.nameCategoryItem = nameCategoryItem;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

}
