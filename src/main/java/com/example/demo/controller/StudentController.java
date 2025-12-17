package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Create Student
    @PostMapping("/postStudent")
    public Student postStudent(@RequestBody Student st) {
        return studentService.insertStudent(st);
    }

    // Get All Students
    @GetMapping("/getAll")
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    // Get Student by ID
    @GetMapping("/get/{id}")
    public Optional<Student> getStudent(@PathVariable Long id) {
        return studentService.getOneStudent(id);
    }

    // Update Student
    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody Student newStudent) {
        Optional<Student> student = studentService.getOneStudent(id);
        if (student.isPresent()) {
            newStudent.setId(id);
            studentService.insertStudent(newStudent);
            return "Updated Success";
        }
        return "Id not found";
    }

    // Delete Student
    @DeleteMapping("/del/{id}")
    public String deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.getOneStudent(id);
        if (student.isPresent()) {
            studentService.deleteStudent(id);
            return "Deleted Success";
        }
        return "Id Not Found";
    }
}
