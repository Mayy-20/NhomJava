package ut.nhomjava.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ut.nhomjava.model.NguoiDung;
import ut.nhomjava.repository.NguoiDungRepository;

@Service
public class StudentService {

    private final NguoiDungRepository nguoiDungRepository;

    public StudentService(NguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    public List<NguoiDung> getAllStudents() {
        // Giả sử tên vai trò cho sinh viên trong cơ sở dữ liệu là "student"
        return nguoiDungRepository.findByVaiTro_TenVaiTro("student");
    }

    // Các phương thức khác để quản lý sinh viên (thêm, sửa, xóa) có thể được thêm vào đây
}