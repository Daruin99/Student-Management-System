package com.Abdelrahman.studentmanagementsystem.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import com.Abdelrahman.studentmanagementsystem.Dao.TeacherRepo;

import com.Abdelrahman.studentmanagementsystem.Exception.InvalidPasswordException;
import com.Abdelrahman.studentmanagementsystem.Exception.UserNameExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
    @Mock
    TeacherRepo teacherRepo;
    PasswordEncoder passwordEncoder;
    private TeacherService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TeacherServiceImp(teacherRepo, passwordEncoder);
    }

    @Test
    void canCreateTeacher() throws Exception {

        Teacher teacher = new Teacher();
        teacher.setPassword("StrongPassword123!");
        teacher.setUserName("UniqueUserName");

        when(passwordEncoder.encode(any())).thenReturn("EncodedPassword");
        when(teacherRepo.findByUserName(any())).thenReturn(null);

        //when
        underTest.createTeacher(teacher);

        // then
        ArgumentCaptor<Teacher> teacherArgumentCaptor = ArgumentCaptor.forClass(Teacher.class);
        verify(teacherRepo).save(teacherArgumentCaptor.capture());

        Teacher capturedTeacher = teacherArgumentCaptor.getValue();

        assertThat(capturedTeacher).isEqualTo(teacher);
        assertThat(capturedTeacher.getPassword()).isEqualTo("EncodedPassword");

    }

    @Test
    void willThrowWhenPasswordIsWeak() {
        // given
        Teacher teacher = new Teacher();
        teacher.setPassword("weakpassword");
        teacher.setUserName("UniqueUserName");

        // then
        assertThrows(InvalidPasswordException.class, () -> {
            // when
            underTest.createTeacher(teacher);
        });
    }

    @Test
    void willThrowWhenUserNameExists() {
        // given
        Teacher existingTeacher = new Teacher();
        existingTeacher.setUserName("ExistingUserName");

        when(teacherRepo.findByUserName(existingTeacher.getUserName())).thenReturn(existingTeacher);

        Teacher newTeacher = new Teacher();
        newTeacher.setUserName("ExistingUserName");

        // then
        assertThrows(UserNameExistsException.class, () -> {
            // when
            underTest.createTeacher(newTeacher);
        });
    }


    @Test
    void canFindTeacherByUserName() {
        // given
        Teacher teacher = new Teacher();
        teacher.setUserName("Omar");
        when(teacherRepo.findByUserName(teacher.getUserName())).thenReturn(teacher);

        // when

        Teacher found = underTest.findTeacherByUserName(teacher.getUserName());

        // then

        ArgumentCaptor<String> userNameArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(teacherRepo).findByUserName(userNameArgumentCaptor.capture());
        String capturedUserName = userNameArgumentCaptor.getValue();

        assertThat(capturedUserName).isEqualTo(teacher.getUserName());
        assertThat(found).isEqualTo(teacher);
    }


}

