package com.studentcrud.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import com.studentcrud.model.FacultyDetails;
import com.studentcrud.model.Menu;
import com.studentcrud.model.Student;
import com.studentcrud.service.StudentService;

@Controller
public class StudentController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/imagedata";

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
		return "redirect:/login";
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
		return "redirect:/login";
	}

	@GetMapping("/student-dashboard")
	public String studentDashboard(Model model, HttpSession httpSession) {

		if (httpSession.getAttribute("id") != null) {
			Student student = studentService.getById((String) httpSession.getAttribute("id"));
			httpSession.setAttribute("studentNo", student.getStudentNo());

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
		return null;
	}

	@PostMapping("/view-student")
	@ResponseBody
	public String viewStudent(@RequestParam("id") String id, Model model) {

		List<Student> students = studentService.getListById(id);
		String json = gson.toJson(students);
		return json;
	}

	@GetMapping("/menu-return")
	@ResponseBody
	public List<Menu> menuList() {

		List<Menu> lists = studentService.getMenu();
		return lists;
	}

	@GetMapping("/logout-student")
	public String logout(Student students, Model model, HttpSession httpSession) {
		String sessionId = (String) httpSession.getAttribute("id");
		System.out.println(sessionId);
		httpSession.invalidate();
		return "redirect:/login";
	}

	@PostMapping("/faculty-submission")
	@ResponseBody
	public String facultySubmission(HttpSession httpSession, FacultyDetails facultyDetails) {

		int id = (int) httpSession.getAttribute("studentNo");

		String filename = id + facultyDetails.getImage().getOriginalFilename();

		Path fileNameAndPath = Paths.get(uploadDirectory, filename);

		try {
			Files.write(fileNameAndPath, facultyDetails.getImage().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		facultyDetails.setPhoto(filename);
		studentService.saveFaculty(facultyDetails, id);

		return "Success";
	}
}
