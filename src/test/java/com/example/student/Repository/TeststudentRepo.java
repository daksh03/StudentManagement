package com.example.student.Repository;

import com.example.student.Model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class TeststudentRepo {

    @Autowired
    private StudentRepo studentRepo;

    @BeforeEach
    void setUp() {
        Student student = new Student("1", "Daksh", 24, "A+", "Choudhary Puram");
        studentRepo.save(student);
    }

    @AfterEach
    void tearDown() {
        studentRepo.deleteAll();
    }

    @Test
    void testFindById() {
        Optional<Student> studentOptional = studentRepo.findById("1");
        assertThat(studentOptional).isPresent();
        Student student = studentOptional.get();
        assertThat(student.getName()).isEqualTo("Daksh");
        assertThat(student.getAge()).isEqualTo(24);
    }

    @Test
    void testFindAll() {
        Page<Student> students = studentRepo.findAll(PageRequest.of(0, 10));
        assertThat(students.getTotalElements()).isEqualTo(1);
        assertThat(students.getContent().get(0).getName()).isEqualTo("Daksh");
    }
}
