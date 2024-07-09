package com.amrit.orm.learnspringorm;

import com.amrit.orm.learnspringorm.entities.*;
import com.amrit.orm.learnspringorm.repositories.CategoryRepo;
import com.amrit.orm.learnspringorm.repositories.ProductRepo;
import com.amrit.orm.learnspringorm.repositories.StudentRepository;
import com.amrit.orm.learnspringorm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class LearnSpringOrmApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ProductRepo productRepo;


	private Logger logger = LoggerFactory.getLogger(LearnSpringOrmApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(LearnSpringOrmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setName("DaltonPandit");
//		user.setCity("Daltonganj");
//		user.setAge(28);
//
//
//		User savedUser = userService.saveUser(user);
//		System.out.println(savedUser);

		//get all user
//		List<User> allUser = userService.getAllUser();
//		logger.info("user size is {} ",allUser.size());
//		logger.info("Users:{}",allUser);

		//get single  user
//		User user = userService.getUser(20);
//		logger.info("User is :{}",user);

//		User user = new User();
//		user.setName("DaltonPanditDaku");
//		user.setCity("RanchiJhat");
//		user.setAge(22);
//
//		User updateUser = userService.updateUser(user, 1);
//		logger.info("updated user details :{}",updateUser);

		//	userService.deleteUser(4);
//       ONE TO ONE MAPPING
//		Student student = new Student();
//		student.setStudentName("Ankit  Prasad Jaiswal");
//		student.setAbout("I am software Engineer");
//		student.setStudentId(125);
//
//
//
//		Laptop laptop = new Laptop();
//		laptop.setModelNumber("8871461");
//		laptop.setBrand("HP");
//		laptop.setLaptopId(12356);
//
//		//important to do this
//		laptop.setStudent(student);
//
//		student.setLaptop(laptop);
//
//
//		Student save = studentRepository.save(student);
//		logger.info("saved student :{}",save.getStudentName());

//		Student student = studentRepository.findById(125).get();
//		logger.info("Name is {}",student.getStudentName());
//
//		Laptop laptop = student.getLaptop();
//		logger.info("Laptop {},{}",laptop.getBrand(),laptop.getModelNumber());

//           ONE TO MANY

//		Student student = new Student();
//		student.setStudentName("Ravi Prasasd Jaiswal");
//		student.setAbout("I am software Engineer");
//		student.setStudentId(1);
//
//		Address a1 = new Address();
//		a1.setAddressId(122);
//		a1.setStreet("123/dajfkads");
//		a1.setCity("RAMGARH");
//		a1.setCountry("PAKISTSAN");
//		a1.setStudent(student);
//
//		Address a2 = new Address();
//		a2.setAddressId(123);
//		a2.setStreet("1245325/dajfkads");
//		a2.setCity("RAMGkdaa");
//		a2.setCountry("PAKISTSAN");
//		a2.setStudent(student);
//
//		List<Address> addressList = new ArrayList<>();
//		addressList.add(a1);
//		addressList.add(a2);
//
//		student.setAddressList(addressList);
//		Student save = studentRepository.save(student);
//		logger.info("Student created with address");
//
		// Many to Many

//		Product product1 = new Product();
//		product1.setpId("pid1");
//		product1.setProductName("Samasung pkjfda d");
//
//		Product product2 = new Product();
//		product2.setpId("pid2");
//		product2.setProductName("One Plus pkjfda d");
//
//		Product product3 = new Product();
//		product3.setpId("pid3");
//		product3.setProductName("VIVO  pkjfda d");
//
//		Category category1= new Category();
//		category1.setcId("cid1");
//		category1.setTitle("Electronics");
//
//		Category category2= new Category();
//		category2.setcId("cid2");
//		category2.setTitle("MonilePhones");
//
//		List<Product> category1Products = category1.getProducts();
//		category1Products.add(product1);
//		category1Products.add(product2);
//		category1Products.add(product3);
//
//		List<Product> category2Products = category2.getProducts();
//		category2Products.add(product1);
//		category2Products.add(product2);
//
//		categoryRepo.save(category1);
//		categoryRepo.save(category2);

//		Category category = categoryRepo.findById("cid1").get();
//		System.out.println(category.getProducts().size());
//
//		Category category2= categoryRepo.findById("cid2").get();
//		System.out.println(category2.getProducts().size());
//
//		Product product = productRepo.findById("pid1").get();
//		System.out.println(product.getCategories().size());
//		Optional<Product> byProductName = productRepo.findByProductName("Samasung pkjfda d");
//
//		System.out.println(byProductName.isPresent());
//		System.out.println(byProductName.get().getProductName());
//
//		System.out.println("++++++++++++++++++++++");
//
//		List<Product> d = productRepo.findByProductNameEndingWith("d");
//		d.forEach(a->{
//			System.out.println(a.getProductName());
//
//		});
//
//		System.out.println("+++++++++++++++++++++++++++++++");
//		List<Product> plus = productRepo.findByProductNameContaining("Plus");
//		plus.forEach(a-> System.out.println(a.getProductName()));
//		System.out.println("+++++++++++++++++++++++++");
//
//
//		List<Product> all = productRepo.findByProductNameIn(Arrays.asList("One Plus pkjfda d", "VIVO  pkjfda d"));
//		all.forEach(a-> System.out.println(a.getProductName()));

		List<Product> allProductInThis = productRepo.getAllProductInThis();
		allProductInThis.forEach(a-> System.out.println(a.getProductName()));

		productRepo.getAllActiveProducts().forEach(a-> System.out.println(a.getProductName()));


	}
}
