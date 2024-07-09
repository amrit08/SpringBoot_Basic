package com.apj.electronic.store.services;

import com.apj.electronic.store.dtos.CategoryDto;
import com.apj.electronic.store.dtos.PageableResponse;
import com.apj.electronic.store.dtos.UserDto;

import java.util.List;
import java.util.Locale;

public interface CategoryService {
    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto , String categoryId);

    //delete

    void delete(String categoryId);

    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize ,String sortBy , String sortDir);

    //get single category detail
    CategoryDto get(String categoryId);

    //search
    List<CategoryDto> searchCategory(String keyword);



}
