package com.apj.electronic.store.repositories;

import com.apj.electronic.store.entities.Category;
import com.apj.electronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,String> {
    List<Category> findByTitleContaining(String keywords);

}
