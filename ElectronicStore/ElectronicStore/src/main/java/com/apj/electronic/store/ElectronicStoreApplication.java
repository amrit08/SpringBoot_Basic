package com.apj.electronic.store;

import com.apj.electronic.store.entities.Role;
import com.apj.electronic.store.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
@EnableWebMvc
public class ElectronicStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicStoreApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository repository;

	@Value("${admin.role.id}")
	private String role_admin_id;

	@Value("${normal.role.id}")
	private String role_normal_id;

	@Override
	public void run(String... args) throws Exception {
		//System.out.println(passwordEncoder.encode("6789"));

		try {
//			String role_admin_id = "8379jdsahkahakjFVB";
//			String role_normal_id = "345429sdakjnckajfalj";
			Role role_admin = Role.builder().roleId(role_admin_id)
					.roleName("ROLE_ADMIN").build();
			Role role_normal = Role.builder().roleId(role_normal_id)
					.roleName("ROLE_NORMAL").build();
			 repository.save(role_admin);
			 repository.save(role_normal);


		}catch (Exception e){
			e.printStackTrace();


		}


		}
}
