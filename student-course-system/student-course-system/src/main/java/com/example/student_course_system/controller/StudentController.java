import jakarta.validation.Valid;
import com.example.student_course_system.dto.StudentRequestDTO;

@PostMapping
public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentRequestDTO dto) {

    Student student = new Student();
    student.setFirstName(dto.getFirstName());
    student.setLastName(dto.getLastName());
    student.setEmail(dto.getEmail());
    student.setAge(dto.getAge());

    Student savedStudent = studentService.createStudent(student);

    return ResponseEntity.status(201).body(savedStudent);
}