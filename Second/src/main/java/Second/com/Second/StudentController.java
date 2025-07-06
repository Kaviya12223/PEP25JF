package Second.com.Second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepo repo;

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // Get student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    // Create a new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return repo.save(student);
    }

    // Update an existing student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        return repo.save(student);
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        repo.deleteById(id);
    }
}
