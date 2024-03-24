package com.ashcollege.controllers;

import com.ashcollege.entities.Student;
import com.ashcollege.utils.Databases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @Autowired
    private Databases databases;
    @RequestMapping("/show_students_grades")
    public String showStudentsGrades (String id) {

        return id;
    }

    @RequestMapping("/show_course_students")
    public int showCourseStudents (int courseNumber) {
       return courseNumber;
    }

    @RequestMapping("/delete_student")
    public String deleteStudent (String id) {
        return id;
    }
    //add_student
    @RequestMapping("/add_student")
    public boolean addStudent (String id,String name,String phone) {
        Student student = new Student(name,id,phone);
        boolean isAdd =  databases.addStudent(student);
        return isAdd;
    }



}
