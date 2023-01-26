package com.commons.nirakar.service;

import java.util.List;

import com.commons.nirakar.model.Course;
import com.commons.nirakar.model.Student;

public interface IStudentService {
	//create a student
	public String createStudent(Student std);
	
	//update student assign course
	public String updateStudentAssignCourse(Integer sid,Integer cid);
	
	//search a student by name
	public List<Student> getStudentByName(String name);
	
	//get student based on course
	public List<Student> getStudentsByCourse(String course);
	
	//get student by code
	public Student getStudentByCode(String code);
	
	//update student
	public String updateStudent(Student std);
	
	//search for courses of a student
	public List<Course> getCourseOfStudent(String code);
	
	//leave a course of a student
	public String leaveCourseOfStudent(String code,Integer cid);
}
