package com.example.student.TestController;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import com.example.student.Controller.StudentController;
import com.example.student.Model.Student;
import com.example.student.Service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(StudentController.class)

public class StudentControllertest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StudentService studentService;

    @Test
void testgetAll() throws Exception {
    List<Student> students = Arrays.asList(new Student("1","Daksh",24,"A+","Bijnor"));

    Pageable pageable = PageRequest.of(0, 10);
    Page<Student> studentPage = new PageImpl<>(students, pageable, students.size());

    when(studentService.getAllStudents(0,10)).thenReturn(studentPage);

    mockMvc.perform(get("/allStudents?page=0&size=10")
            .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.content[0].id").value("1"))
            .andExpect(jsonPath("$.content[0].name").value("Daksh"))
            .andExpect(jsonPath("$.content[0].age").value(24))
            .andExpect(jsonPath("$.content[0].grade").value("A+"))
            .andExpect(jsonPath("$.content[0].address").value("Bijnor"));
}

    @Test
     void testAddStudent() throws Exception {
        Student student = new Student("1", "Daksh", 24, "A+", "Bijnor");
         when(studentService.addStudent(student)).thenReturn(student);
         mockMvc.perform(post("/addStudent")
         .contentType("application/json")
         .content(objectMapper.writeValueAsString(student)))
         .andExpect(status().isOk());
        }

    @Test
     void getStudentByID() throws Exception {
        Student student = new Student("1", "Daksh",24 ,"A+" , "Bijnor");
        when(studentService.getStudentbyId("1")).thenReturn(student);
        mockMvc.perform(get("/findbyID/1")
         .contentType("application/json"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.id").value("1"))
         .andExpect(jsonPath("$.name").value("Daksh"))
         .andExpect(jsonPath("$.age").value(24))
         .andExpect(jsonPath("$.grade").value("A+"))
         .andExpect(jsonPath("$.address").value("Bijnor"));
        }

        @Test
         void testDelete() throws Exception {
              doNothing().when(studentService).deleteStudent("1");
              mockMvc.perform(delete("/delete/1"))
              .andExpect(status().isOk());
              verify(studentService).deleteStudent("1"); }


    }
