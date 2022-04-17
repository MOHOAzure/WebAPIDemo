package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail((student.getEmail()));
        if(studentOptional.isPresent()){
            throw  new IllegalStateException("Email taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudents(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalStateException("No student id:"+id);
        }

        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        System.out.println("Name: "+name+", Mail: "+email);
        Student student = studentRepository.findById(studentId)
                .orElseThrow( ()-> new IllegalStateException(
                        "No such student, id: "+studentId
                ));

        if(name != null &&
                !student.getName().equals(name) ){
            student.setName(name);
        }
        if(email != null &&
                !student.getEmail().equals(email) ){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent())
                throw  new IllegalStateException("Email taken");
            student.setEmail(email);
        }
    }
}
