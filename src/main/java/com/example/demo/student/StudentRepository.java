package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //  equals SELECT * FROM student where email = parameter
    @Query("SELECT s FROM Student s WHERE s.email = ?1") // JPQL not SQL
    Optional<Student> findStudentByEmail(String email);
}


/*
    Repositories are the things connected to the database.
    This has to be an interface.
    @Repository annotation makes the interface access the db
    @Query annotation lets you write JPSQL to query the db
 */
