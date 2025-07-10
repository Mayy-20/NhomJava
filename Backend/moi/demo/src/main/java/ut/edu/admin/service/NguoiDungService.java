package ut.edu.admin.service;

import ut.edu.admin.dto.NguoiDungDTO;
import ut.edu.admin.model.NguoiDung;
import ut.edu.admin.model.VaiTro;
import ut.edu.admin.repository.NguoiDungRepository;
import ut.edu.admin.repository.VaiTroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NguoiDungService {
    private final NguoiDungRepository nguoiDungRepository;
    private final VaiTroRepository vaiTroRepository;

    public List<NguoiDungDTO> getAllUsers() {
        return nguoiDungRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NguoiDungDTO getUserById(Integer id) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(nguoiDung);
    }

    public NguoiDungDTO createUser(NguoiDungDTO dto) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setTenDangNhap(dto.getTenDangNhap());
        nguoiDung.setEmail(dto.getEmail());
        nguoiDung.setMatKhau(dto.getMatKhau()); // Nên mã hóa mật khẩu
        nguoiDung.setHoTen(dto.getHoTen());
        nguoiDung.setVaiTro(vaiTroRepository.findById(dto.getMaVaiTro())
                .orElseThrow(() -> new RuntimeException("Role not found")));
        nguoiDung.setAnhDaiDien(dto.getAnhDaiDien());
        nguoiDung.setDienThoai(dto.getDienThoai());
        nguoiDung.setGioiThieu(dto.getGioiThieu());
        nguoiDung.setTrangThai(NguoiDung.TrangThaiNguoiDung.valueOf(dto.getTrangThai()));
        nguoiDung = nguoiDungRepository.save(nguoiDung);
        return convertToDTO(nguoiDung);
    }

    public NguoiDungDTO updateUser(Integer id, NguoiDungDTO dto) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        nguoiDung.setTenDangNhap(dto.getTenDangNhap());
        nguoiDung.setEmail(dto.getEmail());
        nguoiDung.setHoTen(dto.getHoTen());
        nguoiDung.setVaiTro(vaiTroRepository.findById(dto.getMaVaiTro())
                .orElseThrow(() -> new RuntimeException("Role not found")));
        nguoiDung.setAnhDaiDien(dto.getAnhDaiDien());
        nguoiDung.setDienThoai(dto.getDienThoai());
        nguoiDung.setGioiThieu(dto.getGioiThieu());
        nguoiDung.setTrangThai(NguoiDung.TrangThaiNguoiDung.valueOf(dto.getTrangThai()));
        nguoiDung = nguoiDungRepository.save(nguoiDung);
        return convertToDTO(nguoiDung);
    }

    public void deactivateUser(Integer id) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        nguoiDung.setTrangThai(NguoiDung.TrangThaiNguoiDung.Inactive);
        nguoiDungRepository.save(nguoiDung);
    }

    public void deleteUser(Integer id) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        nguoiDungRepository.delete(nguoiDung);
    }

    public List<NguoiDungDTO> getRecentUsers(int limit) {
        return nguoiDungRepository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "ngayTao")))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private NguoiDungDTO convertToDTO(NguoiDung nguoiDung) {
        NguoiDungDTO dto = new NguoiDungDTO();
        dto.setMaNguoiDung(nguoiDung.getMaNguoiDung());
        dto.setTenDangNhap(nguoiDung.getTenDangNhap());
        dto.setEmail(nguoiDung.getEmail());
        dto.setHoTen(nguoiDung.getHoTen());
        dto.setMaVaiTro(nguoiDung.getVaiTro().getMaVaiTro());
        dto.setAnhDaiDien(nguoiDung.getAnhDaiDien());
        dto.setDienThoai(nguoiDung.getDienThoai());
        dto.setGioiThieu(nguoiDung.getGioiThieu());
        dto.setTrangThai(nguoiDung.getTrangThai().name());
        dto.setNgayTao(nguoiDung.getNgayTao());
        return dto;
    }
}