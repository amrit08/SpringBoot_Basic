package com.amrit.orm.learnspringorm.repositories;

import com.amrit.orm.learnspringorm.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {


}
