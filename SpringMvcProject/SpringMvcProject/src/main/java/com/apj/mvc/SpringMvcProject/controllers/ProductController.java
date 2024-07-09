package com.apj.mvc.SpringMvcProject.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

   Logger logger = LoggerFactory.getLogger(ProductController.class);
    @GetMapping("/getProduct")
    public  String getProduct(@RequestParam("productName") String name ,
                              @RequestParam("productId") int id ,
                              @RequestParam(value = "productRating" , defaultValue = "0",required = false) int pr){
        System.out.println("Product Name :"+name);
        System.out.println("ProductId :"+id);
        System.out.println("productRating"+pr);

        return "This is testing product url";

    }

    @GetMapping("/checkProduct/{productId}/{productName}/{productRating}")
    public String checkProduct(
            @PathVariable("productId") int id,
            @PathVariable String productName,
            @PathVariable int productRating
    ){
//        System.out.println("ProductName"+productName);
//        System.out.println("Product id"+id);
//        System.out.println("Product Rating"+productRating);

        logger.error("ProductName {} {}" ,productName,"Testing Argument");
        logger.warn("Product Id {}",id);
        logger.info("Product Rating{}",productRating);
        logger.debug("This is testing for debig");



        return "this is checking the concept of path variable";

    }
}
