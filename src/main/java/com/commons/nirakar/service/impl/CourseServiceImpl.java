package com.commons.nirakar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.nirakar.exception.CourseNotFoundException;
import com.commons.nirakar.model.Course;
import com.commons.nirakar.repo.CourseRepository;
import com.commons.nirakar.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private CourseRepository crepo;
	
	@Override
	public Integer createCourse(Course course) {
		return crepo.save(course).getCid();
	}

	@Override
	public Course getCourseById(Integer id) {
		return crepo.findById(id).orElseThrow(()->new CourseNotFoundException("Course not exist"));
	}
}
