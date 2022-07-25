package com.studentcrud.model;

public class FacultyDetails {
    private int id;
    private String image;
    private String name;
    private String email;
    private String contactNo;
    private String aadhar;
    private String pan;

    public FacultyDetails() {
    }

    public FacultyDetails(int id, String image, String name, String email, String contactNo, String aadhar,
            String pan) {
        this.id = id;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
                + ", name=" + name + ", pan=" + pan + ", image=" + image + "]";
    }

}
