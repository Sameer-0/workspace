package com.studentcrud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studentcrud.model.Menu;
import com.studentcrud.model.Student;
import com.studentcrud.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("/register")
	public String register(Model model) {
		return "index";
	}

	@PostMapping("/student-details")
	public String StudentInfo(ModelMap model, Student student) {
		studentService.saveStudent(student);
		model.put("errorMsg", "Registered Successfully, you can now Login !");
		return "login";
	}

	@PostMapping("/login")
	public String StudentLogin(Model model, Student student) {

		String password = studentService.getStudentPassword(student.getId());
		String displayPassword = student.getPassword();

		if (password == null) {
			model.addAttribute("errorMsg", "User is not registered, please register");
			return "login";
		} else if (displayPassword.equals(password)) {
			Student students = studentService.getById(student.getId());
			model.addAttribute("id", students.getId());

			List<Menu> lists = studentService.getMenu();
			model.addAttribute("lists", lists);
			return "login-details";
		}
		model.addAttribute("errorMsg", "Password is incorrect");
		return "login";
	}

	@PostMapping("/student-update")
	public String updateStudent(Student student, Model model) {
		studentService.updateStudent(student);
		model.addAttribute("id", student.getId());
		List<Menu> lists = studentService.getMenu();
		model.addAttribute("lists", lists);
		return "login-details";
	}

	@RequestMapping(value = "/delete-student/{id}", method = RequestMethod.DELETE)
	public Object deleteStudent(@PathVariable String id, Model model) {
		studentService.deleteById(id);
		/*
		 * model.addAttribute("errorMsg", "Student with id " + id +
		 * " deleted successfully");
		 */ return null;
	}

	@GetMapping("/view-student/{id}")
	@ResponseBody
	public List<Student> viewStudent(@PathVariable String id, Model model) {

		List<Student> students = studentService.getListById(id);
		System.out.println(students);
		return students;
	}

	@GetMapping("/menu-return")
	@ResponseBody
	public List<Menu> menuList() {

		List<Menu> lists = studentService.getMenu();
		System.out.println(lists);
		return lists;
	}
}