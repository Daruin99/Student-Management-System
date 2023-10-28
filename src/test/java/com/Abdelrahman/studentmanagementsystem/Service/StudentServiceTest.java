package com.Abdelrahman.studentmanagementsystem.Service;


import com.Abdelrahman.studentmanagementsystem.Dao.StudentRepo;
import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock private StudentRepo studentRepo;
    private StudentService underTest;
    private Teacher teacher;

    @BeforeEach
    void setup() {
        underTest = new StudentServiceImp(studentRepo);
        teacher = new Teacher();
    }

    @Test
    void canCreateStudent(){

        //given
        Student student = new Student();

        //when
        underTest.createStudent(student);

        // then

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepo).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);

    }

    @Test
    void canDeleteStudentById() {
        // given
        int id = 1;

        // when
        underTest.deleteStudentById(id);

        // then
        verify(studentRepo).deleteById(id);
    }

    @Test
    void canFindByTeacher() {
        // given
        Teacher teacher = new Teacher();
        List<Student> students = List.of(new Student(), new Student());
        when(studentRepo.findByTeacher(teacher)).thenReturn(students);

        // when
        List<Student> found = underTest.findByTeacher(teacher);

        // then

        ArgumentCaptor<Teacher> teacherArgumentCaptor = ArgumentCaptor.forClass(Teacher.class);

        verify(studentRepo).findByTeacher(teacherArgumentCaptor.capture());
        Teacher capturedTeacher = teacherArgumentCaptor.getValue();

        assertThat(capturedTeacher).isEqualTo(teacher);
        assertThat(found).isEqualTo(students);
    }

    @Test
    void canFindStudentById() {
        // given
        Student student = new Student();
        int id = 1;
        student.setId(id);
        when(studentRepo.findById(id)).thenReturn(Optional.of(student));

        // when
        Student found = underTest.findStudentById(id);

        // then
        assertThat(found).isEqualTo(student);
    }
}
