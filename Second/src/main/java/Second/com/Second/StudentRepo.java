package Second.com.Second;
import Second.com.Second.Student;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepo extends JpaRepository<Student, Integer> {
}
