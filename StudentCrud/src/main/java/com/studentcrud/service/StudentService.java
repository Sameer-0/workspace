package com.studentcrud.service;

import java.util.List;

import com.studentcrud.model.Menu;
import com.studentcrud.model.Student;

public interface StudentService {

	Student saveStudent(Student student);

	Student updateStudent(Student student);

	Student getById(String id);

	String deleteById(String id);

	Student allStudents();

	public String getStudentPassword(String userId);

	public List<Menu> getMenu();
}
