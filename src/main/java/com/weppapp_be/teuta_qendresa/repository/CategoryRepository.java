package com.weppapp_be.teuta_qendresa.repository;


import com.weppapp_be.teuta_qendresa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.id = ?1 AND c.deletedAt IS NULL")
    Optional<Category> findById(Long id);

    @Query("SELECT c FROM Category c WHERE c.deletedAt IS NULL")
    List<Category> findAll();
}
