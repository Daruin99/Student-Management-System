package com.Abdelrahman.studentmanagementsystem.Dao;

import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class TeacherRepoTest {
    @Autowired
    private TeacherRepo underTest;

    @Test
    void itCanGetTeacherByUserName() {
        //given

        Teacher teacher = new Teacher("Omar", "12345678");
        underTest.save(teacher);

        //when
        Teacher expected = underTest.findByUserName(teacher.getUserName());

        //then
        assertThat(expected.getUserName()).isEqualTo(teacher.getUserName());
    }

    @Test
    void itReturnsNullWhenGivenNonExistentTeacher() {
        //given

        Teacher teacher = new Teacher("Omar", "12345678");
        underTest.save(teacher);

        //when
        Teacher expected = underTest.findByUserName("Mike");

        //then
        assertThat(expected).isNull();
    }
}
