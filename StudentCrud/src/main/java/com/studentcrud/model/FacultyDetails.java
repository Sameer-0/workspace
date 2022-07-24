package com.studentcrud.model;

import org.springframework.web.multipart.MultipartFile;

public class FacultyDetails {
    private int id;
    private MultipartFile photo;
    private String name;
    private String email;
    private String contactNo;
    private String aadhar;
    private String pan;

    public FacultyDetails() {
    }

    public FacultyDetails(int id, MultipartFile photo, String name, String email, String contactNo, String aadhar,
            String pan) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.aadhar = aadhar;
        this.pan = pan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    @Override
    public String toString() {
        return "FacultyDetails [aadhar=" + aadhar + ", contactNo=" + contactNo + ", email=" + email + ", id=" + id
                + ", name=" + name + ", pan=" + pan + ", photo=" + photo + "]";
    }

}
