package com.Abdelrahman.studentmanagementsystem.Dao;

import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@DataJpaTest
public class StudentRepoTest {

    @Autowired
    private StudentRepo underTest;

    @Autowired
    private TeacherRepo teacherRepo;

    @Test
    void itCanGetListOfStudentsByTeacher() {

        //given
        Teacher teacher = new Teacher("Omar", "12345678");
        teacherRepo.save(teacher);
        Student student1 = new Student("ahmed", "mohammed", "ahmedMohammed@gmail.com", 80, teacher);
        Student student2 = new Student("omar", "mohammed", "omarMohammed@gmail.com", 90, teacher);
        underTest.save(student1);
        underTest.save(student2);

        //when

        List<Student> expected = underTest.findByTeacher(teacher);


        //then

        assertThat(expected).hasSize(2).extracting(Student::getId).containsOnly(student1.getId(), student2.getId());

    }

    @Test
    void itReturnsEmptyListOfStudentsWhenThereAreNoStudents() {

        //given
        Teacher teacher = new Teacher("Omar", "12345678");
        teacherRepo.save(teacher);
        //when
        List<Student> expected = underTest.findByTeacher(teacher);


        //then

        assertThat(expected).hasSize(0);

    }



}
