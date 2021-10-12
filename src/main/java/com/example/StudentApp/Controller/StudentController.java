package com.example.StudentApp.Controller;

import com.example.StudentApp.Repositories.StudentRepo;
import com.example.StudentApp.datamodel.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("student")
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
    @GetMapping(path="/dashboard")
    public ModelAndView getAllStudents(ModelAndView mv) {
        mv.addObject("stdList",studentRepo.findAll());
        mv.setViewName("dashboard.html");
        return mv;
    }
}d
