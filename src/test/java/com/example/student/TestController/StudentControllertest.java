package com.example.student.TestController;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.student.Controller.StudentController;
import com.example.student.Model.Student;
import com.example.student.Service.StudentService;

@WebMvcTest(StudentController.class)

public class StudentControllertest {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    void testgetAll() throws Exception {
        List<Student> students = Arrays.asList(new Student("Daksh",24,"A+","Bijnor"));
        when(studentService.getAllStudents()).thenReturn(students);
        mockMvc.perform(get("/allStudents"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$[0].name").value("Daksh")) 
            .andExpect(jsonPath("$[0].age").value(24)) 
            .andExpect(jsonPath("$[0].grade").value("A+")) 
            .andExpect(jsonPath("$[0].address").value("Bijnor")); 
            
        }
}
