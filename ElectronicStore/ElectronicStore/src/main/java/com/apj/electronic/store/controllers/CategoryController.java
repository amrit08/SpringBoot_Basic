package com.apj.electronic.store.controllers;
import com.apj.electronic.store.dtos.*;
import com.apj.electronic.store.services.CategoryService;
import com.apj.electronic.store.services.FileService;
import com.apj.electronic.store.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileService fileService;

    @Value("${category.profile.image.path}")
    private String imageUploadPath;

    @Autowired
    private ProductService productService;


    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    //create
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid@RequestBody CategoryDto categoryDto){
      //call service to save object
        CategoryDto categoryDto1 = categoryService.create(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);

    }

    //update
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{categoryId}")
    public  ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto ,
                                                       @PathVariable String categoryId
                                                       ){
        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        return  new ResponseEntity<>(updatedCategory,HttpStatus.OK);

    }


    //delete
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public  ResponseEntity<ApiResponseMessage> deleteCategory(
            @PathVariable String categoryId){
        categoryService.delete(categoryId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message("Category is deleted successfully")
                .status(HttpStatus.OK)
                .success(true)
                .build();

    return  new ResponseEntity<>(responseMessage,HttpStatus.OK);

    }



    //get all
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAll(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){

        PageableResponse<CategoryDto> pageableResponse = categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }



    //get single
    @GetMapping("/{categoryId}")
    public  ResponseEntity<CategoryDto> getSingle(
            @PathVariable String categoryId
    ){

        CategoryDto categoryDto = categoryService.get(categoryId);

        return  new ResponseEntity<>(categoryDto,HttpStatus.OK);


    }

    //search category
    @GetMapping("/search/{keywords}")
    public  ResponseEntity<List<CategoryDto>> searchUser(@PathVariable String keywords){
        return  new ResponseEntity<>(categoryService.searchCategory(keywords),HttpStatus.OK);

    }

    //upload CategoryImage image
    @PostMapping("/image/{categoryId}")
    public ResponseEntity<ImageResponse> uploadCategoryImage(
            @RequestParam("categoryImage") MultipartFile image,
            @PathVariable String categoryId) throws IOException {
        String imageName = fileService.uploadFile(image,imageUploadPath);
        CategoryDto category = categoryService.get(categoryId);
        category.setCoverImage(imageName);
        CategoryDto updated = categoryService.update(category, categoryId);
        ImageResponse imageResponse = ImageResponse.builder()
                .imageName(imageName)
                .message("Image is successfully uploaded !!!!")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
        return  new ResponseEntity<>(imageResponse,HttpStatus.CREATED);

    }

    //serve category  image
    @GetMapping("/image/{categoryId}")
    public  void serveCategoryImage(@PathVariable String categoryId , HttpServletResponse response) throws IOException {
        CategoryDto category = categoryService.get(categoryId);
        logger.info("Category Image name :{}",category.getCoverImage());
        InputStream resource = fileService.getResource(imageUploadPath, category.getCoverImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }

    //create product with category

    @PostMapping("/{categoryId}/products")
    public  ResponseEntity<ProductDto> createProductWithCategory(
            @PathVariable String categoryId,
            @RequestBody ProductDto productDto

    ){
        ProductDto productWithCategory = productService.createWithCategory(productDto, categoryId);
        return  new ResponseEntity<>(productWithCategory,HttpStatus.CREATED);


    }
    //update category of product
    @PutMapping("/{categoryId}/products/{productId}")
    public  ResponseEntity<ProductDto> updateCategoryProduct(
            @PathVariable String categoryId,
            @PathVariable String productId

    ){
        ProductDto productDto = productService.updateCategoryOfProduct(productId, categoryId);
        return  new ResponseEntity<>(productDto,HttpStatus.OK);

    }

    //get products of categories
    @GetMapping("/{categoryId}/products")
    public ResponseEntity<PageableResponse<ProductDto>> getProductOfCategory(
            @PathVariable String categoryId,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir


    ){
        PageableResponse<ProductDto> response = productService.getAllOfCategory(categoryId,pageNumber,pageSize,sortBy,sortDir);
        return  new ResponseEntity<>(response,HttpStatus.OK);


    }



}
