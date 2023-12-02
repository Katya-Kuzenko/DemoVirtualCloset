package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
