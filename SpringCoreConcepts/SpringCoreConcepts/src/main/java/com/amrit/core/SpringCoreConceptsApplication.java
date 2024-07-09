package com.amrit.core;

import com.amrit.core.couple.*;
import com.amrit.core.scope.Pepsi;
import com.amrit.core.scope.Soda;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import test.Test;

@SpringBootApplication
@ComponentScan(basePackages = {"com.amrit.core","test"})
public class SpringCoreConceptsApplication {

	public static void main(String[] args) {

		// about the beans

		//about the dependencies

		// where to scan for beans
		//com.amrit.core.couple

//		Animal animal = new Cat();
//		Person p = new Person(animal);
//		p.playWithAnimal();

		ApplicationContext context = SpringApplication.run(SpringCoreConceptsApplication.class, args);
		Person personBean = context.getBean(Person.class);
		personBean.playWithAnimal();
		System.out.println(personBean);
//
//		Test testBean = context.getBean(Test.class);
//		testBean.testing();

//		Animal dog = context.getBean("dog", Animal.class);
//		dog.play();

		//bean scope
		//first request for Pepsi bean
//		Pepsi bean = context.getBean(Pepsi.class);
//		System.out.println(bean);
		//	bean.drink();

		//second request for Pepsi bean
//		Pepsi bean1 = context.getBean(Pepsi.class);
//		System.out.println(bean1);
		//bean.drink();

//		Pepsi pepsi = context.getBean(Pepsi.class);
//		System.out.println(pepsi);
//		Soda soda = pepsi.getSoda();
//		System.out.println(soda);
//
//		Pepsi pepsi1 = context.getBean(Pepsi.class);
//		System.out.println(pepsi1);
//		Soda soda1 = pepsi1.getSoda();
//		System.out.println(soda1);

//		Pepsi pepsi2 = context.getBean(Pepsi.class);
//		System.out.println(pepsi2);
//		Soda soda2 = pepsi2.getSoda();
//		System.out.println(soda2);





	}

	//declaring bean using @Bean
//	@Bean(name = "samosa1")
//	public Samosa getSamosa1(){
//
//		return new Samosa("Tandoori");
//	}
//	@Bean(name = "samosa2")
//	public Samosa getSamosa2(){
//
//		return new Samosa("momo");
//}



}
