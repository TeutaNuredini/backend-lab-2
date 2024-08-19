package com.weppapp_be.teuta_qendresa.repository;


import com.weppapp_be.teuta_qendresa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
