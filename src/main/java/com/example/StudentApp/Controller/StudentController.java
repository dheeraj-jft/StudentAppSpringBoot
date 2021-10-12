package com.example.StudentApp.Controller;

import com.example.StudentApp.Repositories.StudentRepo;
import com.example.StudentApp.datamodel.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    StudentRepo studentRepo;

    @PostMapping("/add")
    public @ResponseBody String addStudent(@RequestParam String name, @RequestParam String address, @RequestParam String phone){
        Student st= new Student();
        st.setName(name);
        st.setAddress(address);
        st.setPhone(Integer.parseInt(phone));
        studentRepo.save(st);
        return "saved";
    }
    @GetMapping(path="/getStudents")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return studentRepo.findAll();
    }
}
