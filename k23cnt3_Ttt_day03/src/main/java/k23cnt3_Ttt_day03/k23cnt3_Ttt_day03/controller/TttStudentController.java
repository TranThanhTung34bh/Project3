package k23cnt3_Ttt_day03.k23cnt3_Ttt_day03.controller;

import k23cnt3_Ttt_day03.k23cnt3_Ttt_day03.entity.TttStudent;
import k23cnt3_Ttt_day03.k23cnt3_Ttt_day03.service.TttServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TttStudentController {
    @Autowired
    private TttServiceStudent studentService;
    @GetMapping("/student-list")
    public List<TttStudent> getAllStudents() {
        return studentService.getStudents();
    }
    @GetMapping("/student/{id}")
    public TttStudent getAllStudents(@PathVariable String id)
    {
        Long param = Long.parseLong(id);
        return studentService.getStudent(param);
    }
    @PostMapping("/student-add")
    public TttStudent addStudent(@RequestBody TttStudent student)
    {
        return studentService.addStudent(student);
    }
    @PutMapping("/student/{id}")
    public TttStudent updateStudent(@PathVariable String id,
                                 @RequestBody TttStudent student) {
        Long param = Long.parseLong(id);
        return studentService.updateStudent(param,
                student);
    }
    @DeleteMapping("/student/{id}")
    public boolean deleteStudent(@PathVariable String id) {
        Long param = Long.parseLong(id);
        return studentService.deleteStudent(param);
    }
}

