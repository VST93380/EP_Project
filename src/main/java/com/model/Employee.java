package com.model;

import java.io.InputStream;

public class Employee {
    private int id;
    private String fullname;
    private String email;
    private int backlogs;
    private String aboutYourself;
    private byte[] resume;

    public Employee() {
    }

    public Employee(int id, String fullname, String email, int backlogs, String aboutYourself, byte[] resume) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.backlogs = backlogs;
        this.aboutYourself = aboutYourself;
        this.resume = resume;
    }

    // Getters and setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(int backlogs) {
        this.backlogs = backlogs;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] image) {
        this.resume = image; // Use "this.resume" instead of "resume"
    }

}
