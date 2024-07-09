package org.example;

import another.world.Repo;
import another.world.RepoConfig;
import org.example.beans.CartService;
import org.example.beans.OrderService;
import org.example.beans.UserService;
import org.example.config.AppConfig;
import org.example.config.WebConfig;
import org.example.web.AuthController;
import org.example.web.HomeController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Application Started" );
        //we have to create object of application context
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class , RepoConfig.class, WebConfig.class);
        System.out.println(context);
        CartService cartService1 = context.getBean("cartService1", CartService.class);
        cartService1.createCart();


        UserService bean = context.getBean(UserService.class);
        bean.saveUser();

        OrderService bean1 = context.getBean(OrderService.class);
        bean1.createOrder();
        HomeController ho = context.getBean(HomeController.class);
        ho.homepage();

        AuthController au = context.getBean(AuthController.class);
         au.login();

        Repo bean2 = context.getBean(Repo.class);
        bean2.getUser();


        // ApplicationContext context = new ClassPathXmlApplicationContext();
    }
}
