package com.studentcrud.model;

public class FacultyExperience {

    private String university;
    private String subject;
    private int yearsOfExperience;
    private String startDate;
    private String endDate;

    public FacultyExperience() {
    }

    public FacultyExperience(String university, String subject, int yearsOfExperience, String startDate,
            String endDate) {
        this.university = university;
        this.subject = subject;
        this.yearsOfExperience = yearsOfExperience;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "FacultyExperience [endDate=" + endDate + ", startDate=" + startDate + ", subject=" + subject
                + ", university=" + university + ", yearsOfExperience=" + yearsOfExperience + "]";
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

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
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

}
