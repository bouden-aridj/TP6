package com.example.tp6;

public class Patient {
    public String lastName, firstName, email, phone, sex;
    public int age;

    public Patient(String lastName, String firstName, String email,
                   String phone, int age, String sex) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " (" + age + ", " + sex + ")\n"
                + email + " - " + phone;
    }
}
