package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.model.VaiTro;
import ut.edu.hannah.repository.NguoiDungRepository;
import ut.edu.hannah.repository.VaiTroRepository;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Service để quản lý người dùng.
 */
@Service
public class NguoiDungService {
    private final NguoiDungRepository nguoiDungRepository;
    private final VaiTroRepository vaiTroRepository;

    public NguoiDungService(NguoiDungRepository nguoiDungRepository, VaiTroRepository vaiTroRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
        this.vaiTroRepository = vaiTroRepository;
    }

    /**
     * Đăng ký người dùng mới.
     * @param tenDangNhap Tên đăng nhập.
     * @param email Email.
     * @param matKhau Mật khẩu.
     * @param confirmPassword Xác nhận mật khẩu.
     * @param hoTen Họ tên.
     * @param maVaiTro Mã vai trò.
     */
    public void register(String tenDangNhap, String email, String matKhau, String confirmPassword, String hoTen, Integer maVaiTro,String gioiThieu) {
        if (tenDangNhap == null || tenDangNhap.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống");
        }
        if (email == null || !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        if (matKhau == null || matKhau.length() < 6) {
            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 6 ký tự");
        }
        if (!matKhau.equals(confirmPassword)) {
            throw new IllegalArgumentException("Mật khẩu và xác nhận mật khẩu không khớp");
        }
        if (hoTen == null || hoTen.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để trống");
        }
        if (maVaiTro == null) {
            throw new IllegalArgumentException("Mã vai trò không được để trống");
        }
        VaiTro vaiTro = vaiTroRepository.findById(maVaiTro)
                .orElseThrow(() -> new IllegalArgumentException("Vai trò không tồn tại"));


        if (nguoiDungRepository.findByTenDangNhap(tenDangNhap).isPresent()) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại");
        }
        if (nguoiDungRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setTenDangNhap(tenDangNhap);
        nguoiDung.setEmail(email);
        nguoiDung.setMatKhau(matKhau);
        nguoiDung.setHoTen(hoTen);
        nguoiDung.setVaiTro(vaiTro);
        nguoiDung.setGioiThieu(gioiThieu);
        nguoiDungRepository.save(nguoiDung);
    }

    /**
     * Đăng nhập người dùng.
     * @param tenDangNhap Tên đăng nhập.
     * @param matKhau Mật khẩu.
     * @return Người dùng (nếu đăng nhập thành công).
     */
    public Optional<NguoiDung> login(String tenDangNhap, String matKhau) {
        if (tenDangNhap == null || matKhau == null) {
            return Optional.empty();
        }
        Optional<NguoiDung> nguoiDung = nguoiDungRepository.findByTenDangNhap(tenDangNhap);
        if (nguoiDung.isPresent() && nguoiDung.get().getMatKhau().equals(matKhau)) {
            return nguoiDung;
        }
        return Optional.empty();
    }

    /**
     * Tìm người dùng theo ID.
     * @param id Mã người dùng.
     * @return Người dùng (nếu có).
     */
    public Optional<NguoiDung> findById(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return nguoiDungRepository.findById(id);
    }

    /**
     * Tìm người dùng theo email.
     * @param email Email.
     * @return Người dùng (nếu có).
     */
    public Optional<NguoiDung> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        return nguoiDungRepository.findByEmail(email);
    }

    /**
     * Tìm người dùng theo tên đăng nhập.
     * @param tenDangNhap Tên đăng nhập.
     * @return Người dùng (nếu có).
     */
    public Optional<NguoiDung> findByTenDangNhap(String tenDangNhap) {
        if (tenDangNhap == null || tenDangNhap.trim().isEmpty()) {
            return Optional.empty();
        }
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }

    public void save(NguoiDung nguoiDung) {
        nguoiDungRepository.save(nguoiDung);
    }
}