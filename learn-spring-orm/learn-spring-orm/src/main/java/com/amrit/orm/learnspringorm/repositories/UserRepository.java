package com.amrit.orm.learnspringorm.repositories;

import com.amrit.orm.learnspringorm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {



}
