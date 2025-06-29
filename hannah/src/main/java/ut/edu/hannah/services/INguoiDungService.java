package ut.edu.hannah.services;

import ut.edu.hannah.model.NguoiDung;

// Interface định nghĩa các phương thức liên quan đến người dùng
public interface INguoiDungService {
    NguoiDung findById(Integer maNguoiDung);
    NguoiDung findByTenDangNhap(String tenDangNhap);
    NguoiDung login(String tenDangNhap, String matKhau);
    NguoiDung register(NguoiDung nguoiDung, String confirmPassword);

}