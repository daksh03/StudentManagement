package com.example.student.TestModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.student.Model.Student;

public class TestStudent {

    @Test
    void testStudent() {
        Student student = new Student();
        student.setId("1");
        student.setName("Daksh");
        student.setAge(24);
        student.setGrade("A+");
        student.setAddress("Choudhary Puram");

        Assertions.assertEquals("1",student.getId());
        Assertions.assertEquals("Daksh", student.getName());
        Assertions.assertEquals(24, student.getAge());
        Assertions.assertEquals("A+", student.getGrade());
        Assertions.assertEquals("Choudhary Puram", student.getAddress());

    }
}
