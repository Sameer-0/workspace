package com.studentcrud.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String register() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/student-login")
	public String StudentInfo(ModelMap model, Student student) {
		studentService.saveStudent(student);
		model.put("errorMsg", "Registered Successfully, you can now Login !");
		return "login";
	}

	@PostMapping("/dashboard")
	public String StudentLogin(Model model, Student student, HttpSession httpSession) {

		String password = studentService.getStudentPassword(student.getId());
		String displayPassword = student.getPassword();

		if (password == null) {
			model.addAttribute("errorMsg", "User is not registered, please register");
			return "login";
		} else if (displayPassword.equals(password)) {

			httpSession.setAttribute("id", student.getId());
			return "redirect:/student-dashboard";
		}
		model.addAttribute("errorMsg", "Password is incorrect");
		return "login";
	}

	@GetMapping("/student-dashboard")
	public String studentDashboard(Model model, HttpSession httpSession) {

		if (httpSession.getAttribute("id") != null) {
			Student student = studentService.getById((String) httpSession.getAttribute("id"));
			model.addAttribute("id", student.getId());
			List<Menu> lists = studentService.getMenu();
			model.addAttribute("lists", lists);
			return "login-details";
		}
		return "redirect:/login";
	}

	@PostMapping("/student-update")
	public String updateStudent(Student student, Model model) {
		studentService.updateStudent(student);
		model.addAttribute("id", student.getId());
		List<Menu> lists = studentService.getMenu();
		model.addAttribute("lists", lists);
		return "login-details";
	}

	@PostMapping("/delete-student")
	public Object deleteStudent(@RequestParam String id, Model model) {
		studentService.deleteById(id);
		/*
		 * model.addAttribute("errorMsg", "Student with id " + id +
		 * " deleted successfully");
		 */ return "deleted";
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
	public String logout(Student students, Model model, HttpSession httpSession) {
		String sessionId = (String) httpSession.getAttribute("id");
		System.out.println(sessionId);
		httpSession.invalidate();
		return "login";
	}

	@PostMapping("/faculty-submission")
	public String facultySubmission(HttpSession httpSession) {

		System.out.println(httpSession.getAttribute("id"));
		return null;
	}
}
