package com.studentcrud.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.studentcrud.model.FacultyDetails;
import com.studentcrud.model.FacultyExperience;
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

		try {
			facultyDetails.setPhoto(facultyDetails.getImage().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// studentService.saveFaculty(facultyDetails, id);

		return "Success";
	}

	@PostMapping("/experience-submission")
	@ResponseBody
	public List<FacultyExperience> experienceSubmission(@RequestBody String jsonString, HttpSession httpSession)
			throws IOException {

		int id = (int) httpSession.getAttribute("studentNo");

		ObjectMapper mapper = new ObjectMapper();
		List<FacultyExperience> experience;
		try {
			experience = mapper.readValue(jsonString, new TypeReference<List<FacultyExperience>>() {
			});
			// studentService.saveFacultyExperience(experience, id);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		FacultyDetails details = studentService.getFacultyDetails(id);

		List<FacultyExperience> FacultyexperienceList = studentService.getFacultyExperience(id);
		// String json = gson.toJson(FacultyexperienceList);

		// HashMap<String, Object> map = new HashMap<>();

		// map.put("facultyPersonalDetails", details);
		// map.put("facultyExperience", json);

		// map.entrySet().forEach(entry -> {
		// System.out.println(entry.getKey() + " " + entry.getValue());
		// });
		// System.out.println("*******************************************************");
		return FacultyexperienceList;
	}
}
