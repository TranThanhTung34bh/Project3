package k23cnt3_Ttt_day03.k23cnt3_Ttt_day03.service;


import k23cnt3_Ttt_day03.k23cnt3_Ttt_day03.entity.TttStudent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TttServiceStudent {
    List<TttStudent> students
            = new ArrayList<TttStudent>();
    public TttServiceStudent() {
        students.addAll(Arrays.asList(
                new TttStudent(1L,"Bùi Lê Quốc Hùng 1",22,"male","HN","0327047118","quochungb3@gmail.com"),
        new TttStudent(2L,"Devmaster 2",25,"Non","Số 25VNP","0978611889","contact@devmaster.edu.vn"),
        new TttStudent(3L,"Devmaster 3",22,"Non","Số 25VNP","0978611889","chungtrinhj@gmail.com")
        ));
    }
    // Lấy toàn bộ danh sách sinh viên
    public List<TttStudent> getStudents() {
        return students;
    }
    // Lấy sinh viên theo id
    public TttStudent getStudent(Long id) {
        return students.stream()
                .filter(student -> student
                        .getId().equals(id))
                .findFirst().orElse(null);
    }
    // Thêm mới một sinh viên
    public TttStudent addStudent(TttStudent student) {
        students.add(student);
        return student;
    }
    // Cập nhật thông tin sinh viên
    public TttStudent updateStudent(Long id, TttStudent student)
    {
        TttStudent check = getStudent(id);
        if (check == null) {
            return null;
        }
        students.forEach(item -> {
            if (item.getId()==id) {
                item.setName(student.getName());
                item.setAddress(student.getAddress());
                item.setEmail(student.getEmail());
                item.setPhone(student.getPhone());
                item.setAge(student.getAge());
                item.setGender(student.getGender());
            }
        });
        return student;
    }
    // Xóa thông tin sinh viên
    public boolean deleteStudent(Long id){
        TttStudent check = getStudent(id);
        return students.remove(check);
    }
}

