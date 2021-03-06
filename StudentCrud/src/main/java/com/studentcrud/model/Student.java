package com.studentcrud.model;

public class Student {

	private String id;
	private String name;
	private String email;
	private String password;
	private int studentNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", studentNo="
				+ studentNo + "]";
	}

	public Student(String id, String name, String email, String password, int studentNo) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.studentNo = studentNo;
	}

	public Student() {
	}

}