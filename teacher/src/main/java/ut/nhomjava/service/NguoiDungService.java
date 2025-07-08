package ut.nhomjava.service;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder; // <-- QUAN TRỌNG: Đảm bảo đã import PasswordEncoder
import org.springframework.stereotype.Service;

import ut.nhomjava.model.NguoiDung;
import ut.nhomjava.model.VaiTro;
import ut.nhomjava.repository.NguoiDungRepository;
import ut.nhomjava.repository.VaiTroRepository;

/**
 * Service để quản lý người dùng.
 */
@Service
public class NguoiDungService {
    private final NguoiDungRepository nguoiDungRepository;
    private final VaiTroRepository vaiTroRepository;
    private final PasswordEncoder passwordEncoder; // <-- KHAI BÁO BIẾN PasswordEncoder

    // Constructor cần được cập nhật để nhận PasswordEncoder
    public NguoiDungService(NguoiDungRepository nguoiDungRepository,
                            VaiTroRepository vaiTroRepository,
                            PasswordEncoder passwordEncoder) { // <-- THÊM THAM SỐ passwordEncoder
        this.nguoiDungRepository = nguoiDungRepository;
        this.vaiTroRepository = vaiTroRepository;
        this.passwordEncoder = passwordEncoder; // <-- GÁN GIÁ TRỊ VÀO BIẾN
    }

    /**
     * Đăng ký người dùng mới.
     * @param tenDangNhap Tên đăng nhập.
     * @param email Email.
     * @param matKhau Mật khẩu.
     * @param confirmPassword Xác nhận mật khẩu.
     * @param hoTen Họ tên.
     * @param maVaiTro Mã vai trò.
     * @param gioiThieu Giới thiệu (có thể là null).
     */
    // Bỏ @SuppressWarnings("unchecked") vì các lỗi ép kiểu sẽ được sửa
    public void register(String tenDangNhap, String email, String matKhau, String confirmPassword, String hoTen, Integer maVaiTro, String gioiThieu) {
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

        // SỬA LỖI KIỂM TRA TỒN TẠI NGƯỜI DÙNG:
        // KHÔNG CẦN ÉP KIỂU SANG Optional<VaiTro>
        // Sử dụng phương thức existsBy... (nếu có trong Repository) hoặc kiểm tra .isPresent() trực tiếp
        if (nguoiDungRepository.existsByTenDangNhap(tenDangNhap)) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại");
        }
        if (nguoiDungRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setTenDangNhap(tenDangNhap);
        nguoiDung.setEmail(email);
        // QUAN TRỌNG: Băm mật khẩu trước khi lưu vào CSDL để bảo mật
        nguoiDung.setMatKhau(passwordEncoder.encode(matKhau)); // <-- SỬ DỤNG passwordEncoder.encode()
        nguoiDung.setHoTen(hoTen);
        nguoiDung.setVaiTro(vaiTro);
        nguoiDung.setGioiThieu(gioiThieu);
        nguoiDungRepository.save(nguoiDung);
    }

    /**
     * Đăng nhập người dùng.
     * @param tenDangNhap Tên đăng nhập.
     * @param rawMatKhau Mật khẩu thô (người dùng nhập vào).
     * @return Người dùng (nếu đăng nhập thành công).
     */
    // Bỏ @SuppressWarnings("unchecked")
    public Optional<NguoiDung> login(String tenDangNhap, String rawMatKhau) { // Đổi tên tham số để rõ ràng là mật khẩu thô
        if (tenDangNhap == null || rawMatKhau == null) {
            return Optional.empty();
        }
        // Không cần ép kiểu nếu phương thức Repository trả về đúng Optional<NguoiDung>
        Optional<NguoiDung> nguoiDungOptional = nguoiDungRepository.findByTenDangNhap(tenDangNhap);

        if (nguoiDungOptional.isPresent()) {
            NguoiDung nguoiDung = nguoiDungOptional.get();
            // QUAN TRỌNG: So sánh mật khẩu thô với mật khẩu đã băm trong CSDL
            // KHÔNG dùng .equals() trực tiếp
            if (passwordEncoder.matches(rawMatKhau, nguoiDung.getMatKhau())) { // <-- SỬ DỤNG passwordEncoder.matches()
                return nguoiDungOptional;
            }
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
    // Bỏ @SuppressWarnings("unchecked")
    public Optional<NguoiDung> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        // Không cần ép kiểu
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
        // Không cần ép kiểu
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }

    public void save(NguoiDung nguoiDung) {
        nguoiDungRepository.save(nguoiDung);
    }

    public NguoiDung getCurrentUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}