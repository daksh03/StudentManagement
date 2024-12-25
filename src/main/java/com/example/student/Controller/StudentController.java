package com.example.student.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.Model.Student;
import com.example.student.Service.StudentService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class StudentController {

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

    @GetMapping("/findbyID/{id}")
    public Student getStudentByID(@PathVariable String id) {
        return studentService.getStudentbyId(id);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody Student student) {        
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable String id) {
         studentService.deleteStudent(id);
    }
    
}
