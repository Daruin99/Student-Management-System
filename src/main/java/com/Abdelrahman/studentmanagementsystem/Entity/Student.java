package com.Abdelrahman.studentmanagementsystem.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    @NotNull(message = "This field is required")
    @Size(min = 1, message = "This field is required")
    private String firstName;

    @Column(name = "lastName")
    @NotNull(message = "This field is required")
    @Size(min = 1, message = "This field is required")

    private String lastName;

    @Column(name = "email")
    @NotNull(message = "This field is required")
    @Size(min = 1, message = "This field is required")
    @Email(message = "Enter a valid email format")
    private String email;

    @Column(name = "grade")
    @Min(value = 0, message = "Enter a value greater than 0")
    @Max(value = 100 , message = "Enter a value less than 100")
    private Integer grade;


    @ManyToOne
    @JoinColumn(name = "teacher_user_name")
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
