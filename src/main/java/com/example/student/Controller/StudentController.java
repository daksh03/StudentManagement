package com.example.student.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.student.Model.Student;
import com.example.student.Service.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/allStudents")
    public Page<Student> getallStudents(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        return studentService.getAllStudents(page,size);
    }

    @PostMapping("/addStudent")
    public Student saveStudent(@Valid @RequestBody Student student) {
        try {
            return studentService.addStudent(student);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @GetMapping("/findbyID/{id}")
    public Student getStudentByID(@PathVariable String id) {
        return studentService.getStudentbyId(id);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable String id,@Valid @RequestBody Student student) {        
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable String id) {
         studentService.deleteStudent(id);
    }
    
}
