package com.Abdelrahman.studentmanagementsystem.Dao;

import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
