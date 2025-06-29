package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.repository.NguoiDungRepository;
import java.time.LocalDateTime;

@Service
public class NguoiDungService implements INguoiDungService {

    private final NguoiDungRepository nguoiDungRepository;

    public NguoiDungService(NguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    @Override
    public NguoiDung findById(Integer maNguoiDung) {
        return nguoiDungRepository.findById(maNguoiDung).orElse(null);
    }

    @Override
    public NguoiDung findByTenDangNhap(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }

    @Override
    public NguoiDung login(String tenDangNhap, String matKhau) {
        if (tenDangNhap == null || matKhau == null) {
            return null;
        }
        NguoiDung nguoiDung = findByTenDangNhap(tenDangNhap);
        if (nguoiDung != null && kiemTraMatKhau(nguoiDung, matKhau)) {
            return nguoiDung;
        }
        return null;
    }

    @Override
    public NguoiDung register(NguoiDung nguoiDung, String confirmPassword) {
        if (nguoiDung == null || confirmPassword == null || nguoiDung.getMatKhau() == null) {
            return null;
        }
        if (!kiemTraMatKhau(nguoiDung, confirmPassword)) {
            return null;
        }
        if (nguoiDungRepository.findByTenDangNhap(nguoiDung.getTenDangNhap()) != null ||
            nguoiDungRepository.findByEmail(nguoiDung.getEmail()) != null) {
            return null;
        }
        nguoiDung.setMaVaiTro(3);
        nguoiDung.setTrangThai(NguoiDung.TrangThai.Active);
        nguoiDung.setNgayTao(LocalDateTime.now());
        return nguoiDungRepository.save(nguoiDung);
    }

    private boolean kiemTraMatKhau(NguoiDung nguoiDung, String matKhau) {
        if (nguoiDung == null || matKhau == null) {
            return false;
        }
        return nguoiDung.getMatKhau() != null && nguoiDung.getMatKhau().equals(matKhau);
    }
}