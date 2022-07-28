package com.studentcrud;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.studentcrud.controller.StudentController;

@SpringBootApplication
public class StudentCrudApplication {

	public static void main(String[] args) {

		new File(StudentController.uploadDirectory).mkdir();

		SpringApplication.run(StudentCrudApplication.class, args);
	}

}
