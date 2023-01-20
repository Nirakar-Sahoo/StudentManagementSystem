package com.commons.nirakar.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commons.nirakar.exception.StudentNotFoundException;
import com.commons.nirakar.model.Course;
import com.commons.nirakar.model.Student;
import com.commons.nirakar.service.ICourseService;
import com.commons.nirakar.service.IStudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/admin")
@Api(description = "ADMIN REST APIS")
public class AdminRestController {
	
	@Autowired
	private IStudentService stdservice;
	
	@Autowired
	private ICourseService cservice;
	
	//Admit a student
	@PostMapping("/admit")
	@ApiOperation("ADMIT A STUDENT")
	public ResponseEntity<?> admitStudent(@RequestBody Student student){
		ResponseEntity<?> entity=null;
		try {
			String code=stdservice.createStudent(student);
			entity=new ResponseEntity<>("Student admitted with unique code "+code,HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return entity;
	}
	
	//Upload a Course
	@PostMapping("/addcourse")
	public ResponseEntity<?> uploadCourse(@RequestBody Course course){
		ResponseEntity<?> entity=null;
		try {
			Integer id=cservice.createCourse(course);
			entity=new ResponseEntity<>("Course uploaded with course id "+id,HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return entity;
	}
	
	//Assign courses to student
	@PutMapping("/assigncourse/{sid}/{cid}")
	public ResponseEntity<?> assignCoursesToStudent(@PathVariable Integer sid,@PathVariable Integer cid){
		ResponseEntity<?> entity=null;
		try {
			stdservice.updateStudentAssignCourse(sid,cid);
			entity=new ResponseEntity<>("Course Assign to student",HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return entity;
	}
	
	//search student by name
	@GetMapping("/search/{studentname}")
	public ResponseEntity<?> searchStudents(@PathVariable String studentname){
		ResponseEntity<?> entity=null;
		try {
			List<Student> list=stdservice.getStudentByName(studentname);
			entity=new ResponseEntity<>(list,HttpStatus.OK);
		}
		catch(StudentNotFoundException snfe) {
			snfe.printStackTrace();
			throw snfe;
		}
		return entity;
	}
	
	//get students assigned to particular course
	@GetMapping("/get/student/{coursename}")
	public ResponseEntity<?> getStudentsByCourse(@PathVariable String coursename){
		ResponseEntity<?> entity=null;
		try {
			entity=new ResponseEntity<>(stdservice.getStudentsByCourse(coursename),HttpStatus.OK);
		}
		catch(StudentNotFoundException snfe) {
			snfe.printStackTrace();
			throw snfe;
		}
		return entity;
	}
}
