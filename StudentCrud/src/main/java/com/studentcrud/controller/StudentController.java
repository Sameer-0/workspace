package com.studentcrud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.studentcrud.model.Menu;
import com.studentcrud.model.Student;
import com.studentcrud.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	Gson gson;

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

	@PostMapping("/dashboard")
	public String StudentLogin(Model model, Student student, HttpSession httpSession, HttpServletRequest request) {

		String password = studentService.getStudentPassword(student.getId());
		String displayPassword = student.getPassword();

		if (password == null) {
			model.addAttribute("errorMsg", "User is not registered, please register");
			return "login";
		} else if (displayPassword.equals(password)) {

			Student students = studentService.getById(student.getId());
			model.addAttribute("name", student.getName());
			model.addAttribute("id", students.getId());

			List<Menu> lists = studentService.getMenu();
			httpSession.getAttribute("lists");
			String jsonMenu = gson.toJson(lists);
			request.getSession().setAttribute("lists", jsonMenu);
			return "login-details";
		}
		model.addAttribute("errorMsg", "Password is incorrect");
		return "login";
	}

	@PutMapping("/student-update")
	public String updateStudent(Student student, Model model) {
		studentService.updateStudent(student);
		model.addAttribute("id", student.getId());
		return "login-details";
	}

	@PostMapping("/delete-student")
	public Object deleteStudent(@RequestParam String id, Model model) {
		studentService.deleteById(id);
		/*
		 * model.addAttribute("errorMsg", "Student with id " + id +
		 * " deleted successfully");
		 */ return null;
	}

	@PostMapping("/view-student")
	@ResponseBody
	public String viewStudent(@RequestParam("id") String id, Model model) {

		List<Student> students = studentService.getListById(id);
		String json = gson.toJson(students);
		System.out.println(json);
		return json;
	}

	@GetMapping("/menu-return")
	@ResponseBody
	public List<Menu> menuList() {

		List<Menu> lists = studentService.getMenu();
		System.out.println(lists);
		return lists;
	}

	@GetMapping("/logout-student")
	@ResponseBody
	public String logout(HttpServletRequest request) {

		request.getSession().invalidate();
		return "logout";
	}
}
