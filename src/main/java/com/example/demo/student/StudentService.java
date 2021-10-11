package com.example.demo.student;
// import java.time.LocalDate;
// import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Student service class that interacts with the DB
 */
@Service
public class StudentService {

	// dependency injection
	private final StudentRepository studentRepository;

	@Autowired
    public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	/**
	 * Gets the list of students
	 * @returns Student
	 */
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}


    public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}

		studentRepository.save(student);
    }

	/**
	 * Finds an student on the db and deletes it
	 * @param studentId
	 */
	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("student with id " + studentId + " does not exist");
		}

		studentRepository.deleteById(studentId);
	};

	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

		if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}

		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			
			student.setEmail(email);
		}				
	}
}


/* 
    The service decorator lets java know this is a service class
    that will eventually be referenced by a controller class
*/
