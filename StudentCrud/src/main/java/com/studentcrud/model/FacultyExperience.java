package com.studentcrud.model;

public class FacultyExperience {

    private int id;
    private String university;
    private String subject;
    private String yearsOfExperience;
    private String startDate;
    private String endDate;

    public FacultyExperience() {
    }

    public FacultyExperience(int id, String university, String subject, String yearsOfExperience, String startDate,
            String endDate) {
        this.id = id;
        this.university = university;
        this.subject = subject;
        this.yearsOfExperience = yearsOfExperience;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "FacultyExperience [endDate=" + endDate + ", id=" + id + ", startDate=" + startDate + ", subject="
                + subject + ", university=" + university + ", yearsOfExperience=" + yearsOfExperience + "]";
    }

}
