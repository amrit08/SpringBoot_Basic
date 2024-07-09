package com.apj.electronic.store.services.impl;

import com.apj.electronic.store.dtos.PageableResponse;
import com.apj.electronic.store.dtos.ProductDto;
import com.apj.electronic.store.entities.Category;
import com.apj.electronic.store.entities.Product;
import com.apj.electronic.store.exceptions.ResourceNotFoundException;
import com.apj.electronic.store.helper.Helper;
import com.apj.electronic.store.repositories.CategoryRepository;
import com.apj.electronic.store.repositories.ProductRepository;
import com.apj.electronic.store.services.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CategoryRepository categoryRepository;


    @Value("${product.image.path}")
    private  String imagePath;

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);



    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        //Auto generate product id
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);

        //Auto generate ADDED Date
        product.setAddedDate(new Date());
        Product saveProduct = productRepository.save(product);
        return mapper.map(saveProduct,ProductDto.class) ;
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        //fetch the product of given id
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product not found with given id !!! "));

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        product.setProductImageName(productDto.getProductImageName());

        // save the entity to database
        Product updatedProduct = productRepository.save(product);

        return mapper.map(updatedProduct,ProductDto.class);
    }

    @Override
    public void delete(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product not found with given id !!! "));

        //delete category  image
        String fullPath = imagePath + product.getProductImageName();

        try
        {
            Path path = Paths.get(fullPath);
            Files.delete(path);
        }
        catch (NoSuchFileException ex){
            logger.info("Category  Image Not Found : {}",ex);
            ex.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //delete product
        productRepository.delete(product);

    }

    @Override
    public ProductDto get(String productId)
    {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product not found with given id !!! "));
        return mapper.map(product,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir)
    {
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending()) ;
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findAll(pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending()) ;
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByLiveTrue(pageable);
        return Helper.getPageableResponse(page,ProductDto.class);

    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending()) ;
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByTitleContaining(subTitle , pageable);
        return Helper.getPageableResponse(page,ProductDto.class);

    }

    @Override
    public ProductDto createWithCategory(ProductDto productDto, String categoryId) {

        //fetch the category from db ;

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new
                ResourceNotFoundException("Category not found with found with given id !!!!"));

        Product product = mapper.map(productDto, Product.class);
        //Auto generate product id
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);

        //Auto generate ADDED Date
        product.setAddedDate(new Date());
        product.setCategory(category);
        Product saveProduct = productRepository.save(product);
        return mapper.map(saveProduct,ProductDto.class) ;

    }

    @Override
    public ProductDto updateCategoryOfProduct(String productId, String categoryId)
    {
        // product fetch
        Product product = productRepository.findById(productId).orElseThrow(() -> new
                ResourceNotFoundException("product with given id not found "));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new
                ResourceNotFoundException("category with given id not found"));
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllOfCategory(String categoryId,int pageNumber, int pageSize, String sortBy,String sortDir)
    {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new
                ResourceNotFoundException("category with given id not found"));
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending()) ;
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByCategory(category,pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }
}
