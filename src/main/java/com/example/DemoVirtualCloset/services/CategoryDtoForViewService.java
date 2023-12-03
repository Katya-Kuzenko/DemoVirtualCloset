package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.dto.CategoryDtoForView;
import com.example.DemoVirtualCloset.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryDtoForViewService {

    public Iterable<CategoryDtoForView> getAll() {
        List<CategoryDtoForView> categoryDtoForViewList = new ArrayList<>();

        //має повертати список усіх CategoryDtoForView

        return categoryDtoForViewList;
    }

    public void create(CategoryDtoForView categoryDtoForView) {

        //має сетити значення з categoryDtoForView в відповідні об'єкти доменної області

    }

    public void delete(UUID uuid) {

        //має видаляти об'єкт CategoryDtoForView за переданим uuid

    }

}
