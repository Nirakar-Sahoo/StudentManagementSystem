package com.commons.nirakar.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.commons.nirakar.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	//SELECT * FROM STUDENT WHERE SNAME=?
	List<Student> findBySname(String sname);
	
	@Query("SELECT std FROM Student std INNER JOIN std.course as c WHERE c.cname=:course")
	List<Student> getStudentByCourse(String course);
	
	Optional<Student> findByUniqueStudentCode(String unique_student_code);
	
}
