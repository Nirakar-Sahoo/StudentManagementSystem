package com.commons.nirakar.service;


import com.commons.nirakar.model.Course;

public interface ICourseService {
	//create a course
	public Integer createCourse(Course course);
	
	//get course by id
	public Course getCourseById(Integer id);
}
