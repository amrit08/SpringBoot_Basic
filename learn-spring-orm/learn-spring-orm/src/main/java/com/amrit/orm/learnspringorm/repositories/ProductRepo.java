package com.amrit.orm.learnspringorm.repositories;

import com.amrit.orm.learnspringorm.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,String> {

    // Custom Finder Methods

    Optional<Product> findByProductName(String productName);

    //Product findByPId(int pid);

    //Product findByProductNameIs(String productName);

   // Product findByProductNameEquals(String productName);

    Product findByProductNameIsNot(String productName);

    List<Product> findByProductNameIsNull();
    List<Product> findByProductNameContaining(String infix);

    List<Product> findByLiveTrue();
    List<Product> findByLiveFalse();

    List<Product> findByProductNameStartingWith(String prefix);

    List<Product> findByProductNameEndingWith(String suffix);

    String pattern = "Sam%";
    List<Product> findByProductNameLike(String pattern);

    List<Product> findByPriceLessThan(double price);

    List<Product> findByProductNameIn(Collection<String> names);

    List<Product> findByProductNameAndPrice(String name, double price);
    List<Product> findByProductNameOrPrice(String name, double price);

    Product findByProductNameOrderByProductNameAsc(String productName);


//1.Query Methods

    // select all products with query
    //JPQL
    @Query(value = "SELECT p FROM Product p")
    List<Product> getAllProductInThis();


    @Query( value = "SELECT p from Product p  where p.productName='VIVO  pkjfda d'")
    List<Product> getAllActiveProducts();

    //native query








}
