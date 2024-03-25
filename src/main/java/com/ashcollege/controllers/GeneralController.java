package com.ashcollege.controllers;

import com.ashcollege.entities.Student;
import com.ashcollege.utils.Databases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeneralController {

    @Autowired
    private Databases databases;
    @RequestMapping("/show_students_grades")
    public List<Integer> showStudentsGrades (String id) {
    List<Integer> grades = databases.showStudentGrade(id);
        return grades;
    }

    @RequestMapping("/show_students_in_courses")
    public  List<Integer> showStudentsInCourses (int courseNumber) {
        List<Integer> studentIds=  databases.showCourseStudents(courseNumber);
       return studentIds;
    }
//
@RequestMapping("/update_grade")
public boolean updateGrade (String id ,int grade) {
        databases.updateGrade(id,grade);
    return true;
}
    @RequestMapping("/delete_student")
    public boolean deleteStudent (String id) {
        boolean isDelete = databases.deleteStudent(id);
        return isDelete;
    }
    //show_average
    @RequestMapping("/show_average")
    public double showAverage (String id) {
        double grade = databases.getAverage(id);
        return grade;
    }

    @RequestMapping("/add_student")
    public boolean addStudent (String id,String name,String phone) {
        Student student = new Student(name,id,phone);
        boolean isAdd =  databases.addStudent(student);
        return isAdd;
    }
    //updateId:"",
    //      updateName:"",
    //      updatePhone:""
    @RequestMapping("/update_student_details")
    public boolean updateStudentDetails (String updateId ,String updateName,String updatePhone) {
        databases.updateStudentDetails(updateId,updateName,updatePhone);
        return true;
    }


}
