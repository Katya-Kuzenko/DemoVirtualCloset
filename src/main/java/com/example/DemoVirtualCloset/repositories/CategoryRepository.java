package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends FileRepository<UUID, Category> {
    List<Category> findAll();
}