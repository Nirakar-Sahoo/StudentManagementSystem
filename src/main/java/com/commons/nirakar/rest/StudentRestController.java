package com.commons.nirakar.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commons.nirakar.exception.StudentNotFoundException;
import com.commons.nirakar.model.Student;
import com.commons.nirakar.service.IStudentService;

@RestController
@RequestMapping("/rest/student")
public class StudentRestController {
	
	@Autowired
	private IStudentService stdservice;
	
	//get student details by unique code
	@GetMapping("/get/{uniquecode}")
	public ResponseEntity<?> getStudentByCode(@PathVariable String uniquecode){
		ResponseEntity<?> entity=null;
		try {
			entity=new ResponseEntity<>(stdservice.getStudentByCode(uniquecode),HttpStatus.OK);
		}
		catch(StudentNotFoundException snfe) {
			snfe.printStackTrace();
			throw snfe;
		}
		return entity;
	}
	
	//update student 
	@PutMapping("/update")
	public ResponseEntity<?> updateStudent(@RequestBody Student student){
		ResponseEntity<?> entity=null;
		try {
			String code=stdservice.updateStudent(student);
			entity=new ResponseEntity<>("student with unique code "+code+" updated",HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return entity;
	}
	
	//search for courses of a student
	@GetMapping("/search/{uniquecode}")
	public ResponseEntity<?> searchCourse(@PathVariable String uniquecode){
		ResponseEntity<?> entity=null;
		try {
			entity=new ResponseEntity<>(stdservice.getCourseOfStudent(uniquecode),HttpStatus.OK);
		}
		catch(StudentNotFoundException snfe) {
			snfe.printStackTrace();
			throw snfe;
		}
		return entity;
	}
	
	//update student leave a course
	@PutMapping("/leave/{uniquecode}/{cid}")
	public ResponseEntity<?> leaveCourse(@PathVariable String uniquecode,@PathVariable Integer cid){
		ResponseEntity<?> entity=null;
		try {
			entity=new ResponseEntity<>(stdservice.leaveCourseOfStudent(uniquecode, cid),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return entity;
	}
}
