package com.example.dto;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Person;

public interface PersonReposity  extends JpaRepository<Person,Serializable>{
			public Person findById(String id);
}
