package com.commons.nirakar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commons.nirakar.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
