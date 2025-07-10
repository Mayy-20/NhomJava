package ut.edu.admin.controller;

import ut.edu.admin.dto.NguoiDungDTO;
import ut.edu.admin.model.NguoiDung;
import ut.edu.admin.repository.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor

public class GiangVienController {
    private final NguoiDungRepository nguoiDungRepository;

    @GetMapping
    public ResponseEntity<List<NguoiDungDTO>> getAllInstructors() {
        List<NguoiDungDTO> instructors = nguoiDungRepository.findAll().stream()
                .filter(user -> user.getVaiTro().getMaVaiTro() == 2) // VaiTro: GiangVien
                .map(user -> {
                    NguoiDungDTO dto = new NguoiDungDTO();
                    dto.setMaNguoiDung(user.getMaNguoiDung());
                    dto.setTenDangNhap(user.getTenDangNhap());
                    dto.setEmail(user.getEmail());
                    dto.setHoTen(user.getHoTen());
                    dto.setMaVaiTro(user.getVaiTro().getMaVaiTro());
                    dto.setAnhDaiDien(user.getAnhDaiDien());
                    dto.setDienThoai(user.getDienThoai());
                    dto.setGioiThieu(user.getGioiThieu());
                    dto.setTrangThai(user.getTrangThai().name());
                    dto.setNgayTao(user.getNgayTao());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(instructors);
    }
}