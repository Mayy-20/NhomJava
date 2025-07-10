package ut.edu.admin.service;

import ut.edu.admin.dto.CourseStatsDTO;
import ut.edu.admin.dto.KhoaHocDTO;
import ut.edu.admin.model.ChuDe;
import ut.edu.admin.model.KhoaHoc;
import ut.edu.admin.repository.ChuDeRepository;
import ut.edu.admin.repository.KhoaHocRepository;
import ut.edu.admin.repository.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KhoaHocService {
    private final KhoaHocRepository khoaHocRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final ChuDeRepository chuDeRepository;

    public List<KhoaHocDTO> getAllCourses() {
        return khoaHocRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public KhoaHocDTO getCourseById(Integer id) {
        KhoaHoc khoaHoc = khoaHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return convertToDTO(khoaHoc);
    }

    public KhoaHocDTO createCourse(KhoaHocDTO dto) {
        KhoaHoc khoaHoc = new KhoaHoc();
        khoaHoc.setTenKhoaHoc(dto.getTenKhoaHoc());
        khoaHoc.setMoTa(dto.getMoTa());
        khoaHoc.setGiangVien(nguoiDungRepository.findById(dto.getMaGiangVien())
                .orElseThrow(() -> new RuntimeException("Instructor not found")));
        khoaHoc.setHinhAnh(dto.getHinhAnh());
        khoaHoc.setMienPhi(dto.getMienPhi());
        khoaHoc.setTrangThai(KhoaHoc.TrangThaiKhoaHoc.valueOf(dto.getTrangThai()));
        khoaHoc.setChuDe(dto.getMaChuDe().stream()
                .map(id -> chuDeRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Category not found")))
                .collect(Collectors.toList()));
        khoaHoc = khoaHocRepository.save(khoaHoc);
        return convertToDTO(khoaHoc);
    }

    public KhoaHocDTO approveCourse(Integer id) {
        KhoaHoc khoaHoc = khoaHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        khoaHoc.setTrangThai(KhoaHoc.TrangThaiKhoaHoc.HoatDong);
        khoaHoc = khoaHocRepository.save(khoaHoc);
        return convertToDTO(khoaHoc);
    }

    public KhoaHocDTO rejectCourse(Integer id) {
        KhoaHoc khoaHoc = khoaHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        khoaHoc.setTrangThai(KhoaHoc.TrangThaiKhoaHoc.An);
        khoaHoc = khoaHocRepository.save(khoaHoc);
        return convertToDTO(khoaHoc);
    }

    public void deleteCourse(Integer id) {
        KhoaHoc khoaHoc = khoaHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        khoaHocRepository.delete(khoaHoc);
    }

    public List<KhoaHocDTO> getPendingCourses(int limit) {
        return khoaHocRepository.findByTrangThai(KhoaHoc.TrangThaiKhoaHoc.ChoDuyet).stream()
                .limit(limit)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CourseStatsDTO getCourseStats() {
        List<KhoaHoc> courses = khoaHocRepository.findAll();
        long totalCourses = courses.size();
        long activeCourses = courses.stream()
                .filter(c -> c.getTrangThai() == KhoaHoc.TrangThaiKhoaHoc.HoatDong)
                .count();
        long pendingCourses = courses.stream()
                .filter(c -> c.getTrangThai() == KhoaHoc.TrangThaiKhoaHoc.ChoDuyet)
                .count();
        long hiddenCourses = courses.stream()
                .filter(c -> c.getTrangThai() == KhoaHoc.TrangThaiKhoaHoc.An)
                .count();
        long totalStudents = courses.stream()
                .mapToInt(KhoaHoc::getSoLuongHocVien)
                .sum();
        double averageRating = courses.stream()
                .mapToDouble(KhoaHoc::getDanhGiaTB)
                .average()
                .orElse(0.0);

        CourseStatsDTO stats = new CourseStatsDTO();
        stats.setTotalCourses(totalCourses);
        stats.setActiveCourses(activeCourses);
        stats.setPendingCourses(pendingCourses);
        stats.setHiddenCourses(hiddenCourses);
        stats.setTotalStudents(totalStudents);
        stats.setAverageRating(averageRating);
        return stats;
    }

    private KhoaHocDTO convertToDTO(KhoaHoc khoaHoc) {
        KhoaHocDTO dto = new KhoaHocDTO();
        dto.setMaKhoaHoc(khoaHoc.getMaKhoaHoc());
        dto.setTenKhoaHoc(khoaHoc.getTenKhoaHoc());
        dto.setMoTa(khoaHoc.getMoTa());
        dto.setMaGiangVien(khoaHoc.getGiangVien().getMaNguoiDung());
        dto.setHinhAnh(khoaHoc.getHinhAnh());
        dto.setMienPhi(khoaHoc.getMienPhi());
        dto.setTrangThai(khoaHoc.getTrangThai().name());
        dto.setDanhGiaTB(khoaHoc.getDanhGiaTB());
        dto.setSoLuongHocVien(khoaHoc.getSoLuongHocVien());
        dto.setNgayTao(khoaHoc.getNgayTao());
        dto.setMaChuDe(khoaHoc.getChuDe().stream()
                .map(ChuDe::getMaChuDe)
                .collect(Collectors.toList()));
        return dto;
    }
}