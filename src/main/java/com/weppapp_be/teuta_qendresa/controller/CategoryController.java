package com.weppapp_be.teuta_qendresa.controller;

import com.weppapp_be.teuta_qendresa.dto.CategoryDto;
import com.weppapp_be.teuta_qendresa.dto.CategoryWithEvents;
import com.weppapp_be.teuta_qendresa.dto.request.CategoryRequest;
import com.weppapp_be.teuta_qendresa.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryRequest request) {
        return new ResponseEntity<>(categoryService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/with-number-of-events")
    public ResponseEntity<List<CategoryWithEvents>> getAllCategoriesWithEvents() {
        List<CategoryWithEvents> categoriesWithEvents = categoryService.getAllCategoriesWithEvents();
        return ResponseEntity.ok(categoriesWithEvents);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(categoryService.update(id, fields));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
