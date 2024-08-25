package com.weppapp_be.teuta_qendresa.service;

import com.weppapp_be.teuta_qendresa.dto.CategoryDto;
import com.weppapp_be.teuta_qendresa.dto.VenueDto;
import com.weppapp_be.teuta_qendresa.dto.request.CategoryRequest;
import com.weppapp_be.teuta_qendresa.entity.Category;
import com.weppapp_be.teuta_qendresa.exception.ResourceNotFoundException;
import com.weppapp_be.teuta_qendresa.mapper.CategoryMapper;
import com.weppapp_be.teuta_qendresa.repository.CategoryRepository;
import com.weppapp_be.teuta_qendresa.util.ReflectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserService userService;

    public CategoryDto create(CategoryRequest request){
        Category category = categoryMapper.toEntity(request);
        category.setCreatedBy(userService.getCurrentUser().getId());
        category.setCreatedAt(LocalDateTime.now());
        Category categoryInDb =  categoryRepository.save(category);
        return categoryMapper.toDto(categoryInDb);
    }

    public CategoryDto getById(Long id){
        Category categoryInDb = categoryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format("Category with id %s not found", id)));
        return categoryMapper.toDto(categoryInDb);
    }

    public List<CategoryDto> getAll(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDto).collect(java.util.stream.Collectors.toList());
    }

    public CategoryDto update(Long id, Map<String, Object> fields){
        Category categoryInDb = categoryRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(String.format("Category with id %s not found", id)));
        fields.forEach((key, value) ->{
            ReflectionUtil.setFieldValue(categoryInDb, key, value);
        });
        return categoryMapper.toDto(categoryRepository.save(categoryInDb));
    }

    public void delete (Long id){
        Category categoryInDb = categoryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format("Category with id %s not found", id)));
        categoryInDb.setDeletedAt(LocalDateTime.now());
        categoryRepository.save(categoryInDb);
    }
}
