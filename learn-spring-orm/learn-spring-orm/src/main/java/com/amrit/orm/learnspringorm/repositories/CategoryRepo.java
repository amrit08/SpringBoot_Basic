package com.amrit.orm.learnspringorm.repositories;

import com.amrit.orm.learnspringorm.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,String> {
}
