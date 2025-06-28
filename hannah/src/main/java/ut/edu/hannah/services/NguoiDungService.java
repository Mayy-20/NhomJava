package ut.edu.hannah.services;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.repository.NguoiDungRepository;
@Service
public class NguoiDungService {

    private final NguoiDungRepository nguoiDungRepository;

    public NguoiDungService(NguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    public NguoiDung login(String tenDangNhap, String matKhau) {
        Optional<NguoiDung> nguoiDungOpt = nguoiDungRepository.findByTenDangNhap(tenDangNhap);
        if (nguoiDungOpt.isPresent() && nguoiDungOpt.get().getMatKhau().equals(matKhau)) {
            return nguoiDungOpt.get();
        }
        return null;
    }

    public NguoiDung register(NguoiDung nguoiDung, String confirmPassword) {
        if (!nguoiDung.getMatKhau().equals(confirmPassword)) {
            return null; // Mật khẩu không khớp
        }
        if (nguoiDungRepository.findByTenDangNhap(nguoiDung.getTenDangNhap()).isPresent() ||
            nguoiDungRepository.findByEmail(nguoiDung.getEmail()).isPresent()) {
            return null; // Tên đăng nhập hoặc email đã tồn tại
        }

        nguoiDung.setMaVaiTro(3); // Học viên
        nguoiDung.setTrangThai(NguoiDung.TrangThai.Active);
        nguoiDung.setNgayTao(LocalDateTime.now());
        return nguoiDungRepository.save(nguoiDung);
    }
}