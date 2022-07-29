package com.studentcrud.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.studentcrud.model.FacultyDetails;
import com.studentcrud.model.FacultyExperience;
import com.studentcrud.service.StudentService;

@RestController
public class StudentRestController {

    @Autowired
    StudentService studentService;

    @Autowired
    Gson gson;

    @PostMapping("/experience-submission")
    @ResponseBody
    public JSONObject experienceSubmission(@RequestBody String jsonString, HttpSession httpSession,
            Model model)
            throws IOException {

        int id = (int) httpSession.getAttribute("studentNo");

        ObjectMapper mapper = new ObjectMapper();
        List<FacultyExperience> experience;
        try {
            experience = mapper.readValue(jsonString, new TypeReference<List<FacultyExperience>>() {
            });
            studentService.saveFacultyExperience(experience, id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<FacultyExperience> facultyExperienceList = studentService.getFacultyExperience(id);
        FacultyDetails details = studentService.getFacultyDetails(id);

        JSONObject object = new JSONObject();
        object.put("facultyExperienceList", facultyExperienceList);
        object.put("facultyDetailsJson", details);
        return object;
    }
}
