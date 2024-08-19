package com.weppapp_be.teuta_qendresa.mapper;

import com.weppapp_be.teuta_qendresa.dto.CategoryDto;
import com.weppapp_be.teuta_qendresa.dto.request.CategoryRequest;
import com.weppapp_be.teuta_qendresa.entity.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper implements GenericMapper<Category, CategoryDto, CategoryRequest>{
    private final ModelMapper mapper;

    @Override
    public CategoryDto toDto(Category entity) {
        return mapper.map(entity, CategoryDto.class);
    }

    @Override
    public Category toEntity(CategoryRequest request) {
        return mapper.map(request, Category.class);
    }
}
