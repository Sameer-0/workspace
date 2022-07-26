package com.studentcrud.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.studentcrud.model.FacultyDetails;
import com.studentcrud.model.FacultyExperience;
import com.studentcrud.model.Menu;
import com.studentcrud.model.Student;
import com.studentcrud.service.StudentService;

@Repository
public class StudentDao implements StudentService {

	private static final String insertStudent = "INSERT INTO STUDENT_PORTAL_FIRST(id,name,email,password) values(?,?,?,?)";
	private static final String updateStudentById = "UPDATE STUDENT_PORTAL_FIRST SET ID=?,name=?,EMAIL=?,PASSWORD=? WHERE StudentNo=?";
	private static final String getStudentById = "SELECT * FROM STUDENT_PORTAL_FIRST WHERE ID=?";
	private static final String deleteStudentById = "DELETE FROM STUDENT_PORTAL_FIRST WHERE ID=?";
	private static final String getAllStudents = "SELECT * FROM STUDENT_PORTAL_FIRST";
	private static final String getStudentPassword = "SELECT PASSWORD FROM STUDENT_PORTAL_FIRST WHERE ID=?";
	private static final String getMenu = "SELECT M2.* FROM MENU M1,MENU M2 WHERE M1.ID=M2.PARENTID ORDER BY ID";
	private static final String insertFacultyDetails = "INSERT INTO faculty_details(photo,NAME,email,contactNo,aadhar,pan,parentId) VALUES(?,?,?,?,?,?,?);";
	// private static final String getFacultyDetails = "SELECT * FROM //
	// `faculty_details`";
	private static final String insertFacultyExperience = "INSERT INTO faculty_experience(university,subject,yearsOfExperience,startDate,endDate,parentID) VALUES(?,?,?,?,?,?)";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Student saveStudent(Student student) {
		jdbcTemplate.update(insertStudent, student.getId(), student.getName(), student.getEmail(),
				student.getPassword());
		return student;
	}

	@Override
	public Student updateStudent(Student student) {
		jdbcTemplate.update(updateStudentById, student.getId(), student.getName(), student.getEmail(),
				student.getPassword(), student.getStudentNo());
		return student;
	}

	@Override
	public Student getById(String id) {

		return jdbcTemplate.queryForObject(getStudentById, (rs, rownum) -> {
			return new Student(rs.getString("id"), rs.getString("name"), rs.getString("email"),
					rs.getString("password"), rs.getInt("studentNo"));
		}, id);
	}

	@Override
	public String deleteById(String id) {
		jdbcTemplate.update(deleteStudentById, id);
		return "Student got deleted with id " + id;
	}

	@Override
	public List<Student> allStudents() {

		return jdbcTemplate.query(getAllStudents, (rs, rownum) -> {
			return new Student(rs.getString("id"), rs.getString("name"), rs.getString("email"),
					rs.getString("password"), rs.getInt("studentNo"));
		});
	}

	public String getStudentPassword(String id) {

		List<String> password = jdbcTemplate.queryForList(getStudentPassword, String.class, id);

		if (password.isEmpty()) {
			return null;
		} else {
			return password.get(0);
		}
	}

	public List<Menu> getMenu() {
		return jdbcTemplate.query(getMenu, (rs, rownum) -> {
			return new Menu(rs.getInt("id"), rs.getString("name"), rs.getString("parentId"), rs.getString("link"));
		});
	}

	@Override
	public List<Student> getListById(String id) {
		return jdbcTemplate.query(getStudentById, (rs, rownum) -> {
			return new Student(rs.getString("id"), rs.getString("name"), rs.getString("email"),
					rs.getString("password"), rs.getInt("studentNo"));
		}, id);
	}

	@Override
	public FacultyDetails saveFaculty(FacultyDetails details, int studentNo) {

		jdbcTemplate.update(insertFacultyDetails, details.getPhoto(), details.getName(), details.getEmail(),
				details.getContactNo(), details.getAadhar(), details.getPan(), studentNo);
		return details;
	}

	@Override
	public void saveFacultyExperience(List<FacultyExperience> facultyExperience, int studentNo) {
		jdbcTemplate.batchUpdate(insertFacultyExperience, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				FacultyExperience experience = facultyExperience.get(i);
				ps.setString(1, experience.getUniversity());
				ps.setString(2, experience.getSubject());
				ps.setString(3, experience.getYearsOfExperience());
				ps.setString(4, experience.getStartDate());
				ps.setString(5, experience.getEndDate());
				ps.setInt(6, studentNo);

			}

			@Override
			public int getBatchSize() {

				return facultyExperience.size();
			}

		});
	}

	// @Override
	// public FacultyDetails getFaculty() {
	// return jdbcTemplate.queryForObject(getFacultyDetails, (rs, rownum) -> {
	// return new FacultyDetails(rownum, rs.getString("photo"), null, null, null,
	// null, null);
	// });
	// }

}
