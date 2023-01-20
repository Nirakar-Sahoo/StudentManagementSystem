package com.commons.nirakar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.nirakar.exception.StudentNotFoundException;
import com.commons.nirakar.model.Course;
import com.commons.nirakar.model.Student;
import com.commons.nirakar.repo.StudentRepository;
import com.commons.nirakar.service.ICourseService;
import com.commons.nirakar.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepository srepo;
	
	@Autowired
	private ICourseService cservice;
	
	@Override
	public String createStudent(Student std) {
		return srepo.save(std).getUniqueStudentCode();
	}
	
	@Override
	public void updateStudentAssignCourse(Integer sid, Integer cid) {
		//get student by id
		Student std=srepo.findById(sid).orElseThrow(()-> new StudentNotFoundException("Student not exist"));
		//get course by id
		Course course=cservice.getCourseById(cid);
		if(std!=null && course!=null && !std.getCourse().contains(course)) { 
			List<Course> courses=std.getCourse();
			courses.add(course);
			std.setCourse(courses);
			//update student
			srepo.save(std);
		}
	}
	
	@Override
	public List<Student> getStudentByName(String name) {
		List<Student> list=srepo.findBySname(name);
		if(!list.isEmpty()) {
			return list;
		}
		else {
			throw new StudentNotFoundException("Students with name "+name+" not exist");
		}
	}
	
	@Override
	public List<Student> getStudentsByCourse(String course) {
		List<Student> list=srepo.getStudentByCourse(course);
		if(!list.isEmpty()) {
			return list;
		}
		else {
			throw new StudentNotFoundException("Students with course name "+course+" not exist");
		}
	}
	
	@Override
	public Student getStudentByCode(String code) {
		return srepo.findByUniqueStudentCode(code).
				orElseThrow(()->new StudentNotFoundException("student not exist"));
	}
	
	@Override
	public String updateStudent(Student std) {
		return srepo.save(std).getUniqueStudentCode();
	}
	
	@Override
	public List<Course> getCourseOfStudent(String code) {
		Student std=srepo.findByUniqueStudentCode(code).
		      orElseThrow(()->new StudentNotFoundException("student not exist"));
		return std.getCourse();
	}
	
	@Override
	public void leaveCourseOfStudent(String code, Integer cid) {
		//get student by code
		Student std=srepo.findByUniqueStudentCode(code).
		       orElseThrow(()->new StudentNotFoundException("student not exist"));
		//get course deatils
		Course course=cservice.getCourseById(cid);
		if(std!=null && course!=null) {
			if(std.getCourse().contains(course)) {
				std.getCourse().remove(course);
				srepo.save(std);
			}
			
		}
	}
}
