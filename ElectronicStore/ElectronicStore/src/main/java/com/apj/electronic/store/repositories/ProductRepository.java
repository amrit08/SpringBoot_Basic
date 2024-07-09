package com.apj.electronic.store.repositories;

import com.apj.electronic.store.entities.Category;
import com.apj.electronic.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    //search

    Page<Product> findByTitleContaining(String subTitle, Pageable pageable);

    //find all live products whose value is True

    Page<Product> findByLiveTrue(Pageable pageable);

    Page<Product> findByCategory(Category category , Pageable pageable);





}
