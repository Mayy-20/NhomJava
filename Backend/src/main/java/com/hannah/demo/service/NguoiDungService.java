package com.hannah.demo.service;

import com.hannah.demo.dto.ApiResponse;
import com.hannah.demo.dto.NguoiDungDTO;
import com.hannah.demo.model.NguoiDung;
import com.hannah.demo.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse<Page<NguoiDungDTO>> getAllUsers(int page, int size, String search,
                                                      String trangThai, Integer maVaiTro) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("ngayTao").descending());
            NguoiDung.TrangThaiNguoiDung status = null;

            if (trangThai != null && !trangThai.isEmpty()) {
                status = NguoiDung.TrangThaiNguoiDung.valueOf(trangThai);
            }

            Page<NguoiDung> users = nguoiDungRepository.findWithFilters(search, status, maVaiTro, pageable);
            Page<NguoiDungDTO> userDTOs = users.map(this::convertToDTO);

            return ApiResponse.success(userDTOs);
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi lấy danh sách người dùng: " + e.getMessage());
        }
    }

    public ApiResponse<NguoiDungDTO> getUserById(Integer id) {
        try {
            Optional<NguoiDung> user = nguoiDungRepository.findById(id);
            if (user.isPresent()) {
                return ApiResponse.success(convertToDTO(user.get()));
            }
            return ApiResponse.error("Không tìm thấy người dùng");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi lấy thông tin người dùng: " + e.getMessage());
        }
    }

    public ApiResponse<NguoiDungDTO> createUser(NguoiDung nguoiDung) {
        try {
            if (nguoiDungRepository.existsByTenDangNhap(nguoiDung.getTenDangNhap())) {
                return ApiResponse.error("Tên đăng nhập đã tồn tại");
            }

            if (nguoiDungRepository.existsByEmail(nguoiDung.getEmail())) {
                return ApiResponse.error("Email đã tồn tại");
            }

            nguoiDung.setMatKhau(passwordEncoder.encode(nguoiDung.getMatKhau()));
            NguoiDung savedUser = nguoiDungRepository.save(nguoiDung);

            return ApiResponse.success("Tạo người dùng thành công", convertToDTO(savedUser));
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi tạo người dùng: " + e.getMessage());
        }
    }

    public ApiResponse<NguoiDungDTO> updateUser(Integer id, NguoiDung nguoiDung) {
        try {
            Optional<NguoiDung> existingUser = nguoiDungRepository.findById(id);
            if (!existingUser.isPresent()) {
                return ApiResponse.error("Không tìm thấy người dùng");
            }

            NguoiDung user = existingUser.get();
            user.setHoTen(nguoiDung.getHoTen());
            user.setEmail(nguoiDung.getEmail());
            user.setDienThoai(nguoiDung.getDienThoai());
            user.setGioiThieu(nguoiDung.getGioiThieu());
            user.setMaVaiTro(nguoiDung.getMaVaiTro());
            user.setTrangThai(nguoiDung.getTrangThai());

            if (nguoiDung.getMatKhau() != null && !nguoiDung.getMatKhau().isEmpty()) {
                user.setMatKhau(passwordEncoder.encode(nguoiDung.getMatKhau()));
            }

            NguoiDung updatedUser = nguoiDungRepository.save(user);
            return ApiResponse.success("Cập nhật người dùng thành công", convertToDTO(updatedUser));
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi cập nhật người dùng: " + e.getMessage());
        }
    }

    public ApiResponse<String> deleteUser(Integer id) {
        try {
            if (!nguoiDungRepository.existsById(id)) {
                return ApiResponse.error("Không tìm thấy người dùng");
            }

            nguoiDungRepository.deleteById(id);
            return ApiResponse.success("Xóa người dùng thành công", "Deleted");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi xóa người dùng: " + e.getMessage());
        }
    }

    public ApiResponse<String> approveUser(Integer id) {
        try {
            Optional<NguoiDung> user = nguoiDungRepository.findById(id);
            if (!user.isPresent()) {
                return ApiResponse.error("Không tìm thấy người dùng");
            }

            NguoiDung nguoiDung = user.get();
            nguoiDung.setTrangThai(NguoiDung.TrangThaiNguoiDung.Active);
            nguoiDungRepository.save(nguoiDung);

            return ApiResponse.success("Duyệt người dùng thành công", "Approved");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi duyệt người dùng: " + e.getMessage());
        }
    }

    public ApiResponse<String> rejectUser(Integer id, String reason) {
        try {
            Optional<NguoiDung> user = nguoiDungRepository.findById(id);
            if (!user.isPresent()) {
                return ApiResponse.error("Không tìm thấy người dùng");
            }

            NguoiDung nguoiDung = user.get();
            nguoiDung.setTrangThai(NguoiDung.TrangThaiNguoiDung.Inactive);
            nguoiDungRepository.save(nguoiDung);

            return ApiResponse.success("Từ chối người dùng thành công", "Rejected");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi từ chối người dùng: " + e.getMessage());
        }
    }

    private NguoiDungDTO convertToDTO(NguoiDung nguoiDung) {
        NguoiDungDTO dto = new NguoiDungDTO();
        dto.setMaNguoiDung(nguoiDung.getMaNguoiDung());
        dto.setTenDangNhap(nguoiDung.getTenDangNhap());
        dto.setEmail(nguoiDung.getEmail());
        dto.setHoTen(nguoiDung.getHoTen());
        dto.setMaVaiTro(nguoiDung.getMaVaiTro());
        dto.setAnhDaiDien(nguoiDung.getAnhDaiDien());
        dto.setDienThoai(nguoiDung.getDienThoai());
        dto.setGioiThieu(nguoiDung.getGioiThieu());
        dto.setTrangThai(nguoiDung.getTrangThai());
        dto.setNgayTao(nguoiDung.getNgayTao());

        if (nguoiDung.getVaiTro() != null) {
            dto.setTenVaiTro(nguoiDung.getVaiTro().getTenVaiTro());
        }

        return dto;
    }
}
