package com.example.testazizi.dto;

public class UserRegistrationDTO {
    private String email;
    private String password;
    private String firstname;
    private String surname;
    private String phone;
    private boolean isUserAdmin;

    public UserRegistrationDTO(String email, String password, String firstname, String surname, String phone, boolean isUserAdmin) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.phone = phone;
        this.isUserAdmin = isUserAdmin;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isUserAdmin() {
        return isUserAdmin;
    }

    public void setUserAdmin(boolean userAdmin) {
        isUserAdmin = userAdmin;
    }
}
