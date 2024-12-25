package com.example.student.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.Model.Student;
import com.example.student.Repository.StudentRepo;
import com.example.student.Service.StudentService;

@RestController
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    StudentService studentService;

    @GetMapping("/allStudents")
    public List<Student> getallStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/addStudent")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }
}
