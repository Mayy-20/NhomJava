package com.hannah.demo.service;

import com.hannah.demo.dto.ApiResponse;
import com.hannah.demo.dto.KhoaHocDTO;
import com.hannah.demo.model.KhoaHoc;
import com.hannah.demo.repository.KhoaHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class KhoaHocService {
    
    @Autowired
    private KhoaHocRepository khoaHocRepository;
    
    public ApiResponse<Page<KhoaHocDTO>> getAllCourses(int page, int size, String search, 
                                                      String trangThai, Integer maGiangVien) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("ngayTao").descending());
            KhoaHoc.TrangThaiKhoaHoc status = null;
            
            if (trangThai != null && !trangThai.isEmpty()) {
                status = KhoaHoc.TrangThaiKhoaHoc.valueOf(trangThai);
            }
            
            Page<KhoaHoc> courses = khoaHocRepository.findWithFilters(search, status, maGiangVien, pageable);
            Page<KhoaHocDTO> courseDTOs = courses.map(this::convertToDTO);
            
            return ApiResponse.success(courseDTOs);
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi lấy danh sách khóa học: " + e.getMessage());
        }
    }
    
    public ApiResponse<KhoaHocDTO> getCourseById(Integer id) {
        try {
            Optional<KhoaHoc> course = khoaHocRepository.findById(id);
            if (course.isPresent()) {
                return ApiResponse.success(convertToDTO(course.get()));
            }
            return ApiResponse.error("Không tìm thấy khóa học");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi lấy thông tin khóa học: " + e.getMessage());
        }
    }
    
    public ApiResponse<String> approveCourse(Integer id) {
        try {
            Optional<KhoaHoc> course = khoaHocRepository.findById(id);
            if (!course.isPresent()) {
                return ApiResponse.error("Không tìm thấy khóa học");
            }
            
            KhoaHoc khoaHoc = course.get();
            khoaHoc.setTrangThai(KhoaHoc.TrangThaiKhoaHoc.HoatDong);
            khoaHocRepository.save(khoaHoc);
            
            return ApiResponse.success("Duyệt khóa học thành công", "Approved");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi duyệt khóa học: " + e.getMessage());
        }
    }
    
    public ApiResponse<String> rejectCourse(Integer id, String reason) {
        try {
            Optional<KhoaHoc> course = khoaHocRepository.findById(id);
            if (!course.isPresent()) {
                return ApiResponse.error("Không tìm thấy khóa học");
            }
            
            KhoaHoc khoaHoc = course.get();
            khoaHoc.setTrangThai(KhoaHoc.TrangThaiKhoaHoc.An);
            khoaHocRepository.save(khoaHoc);
            
            return ApiResponse.success("Từ chối khóa học thành công", "Rejected");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi từ chối khóa học: " + e.getMessage());
        }
    }
    
    public ApiResponse<String> deleteCourse(Integer id) {
        try {
            if (!khoaHocRepository.existsById(id)) {
                return ApiResponse.error("Không tìm thấy khóa học");
            }
            
            khoaHocRepository.deleteById(id);
            return ApiResponse.success("Xóa khóa học thành công", "Deleted");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi xóa khóa học: " + e.getMessage());
        }
    }
    
    private KhoaHocDTO convertToDTO(KhoaHoc khoaHoc) {
        KhoaHocDTO dto = new KhoaHocDTO();
        dto.setMaKhoaHoc(khoaHoc.getMaKhoaHoc());
        dto.setTenKhoaHoc(khoaHoc.getTenKhoaHoc());
        dto.setMoTa(khoaHoc.getMoTa());
        dto.setMaGiangVien(khoaHoc.getMaGiangVien());
        dto.setHinhAnh(khoaHoc.getHinhAnh());
        dto.setMienPhi(khoaHoc.getMienPhi());
        dto.setTrangThai(khoaHoc.getTrangThai());
        dto.setDanhGiaTB(khoaHoc.getDanhGiaTB());
        dto.setSoLuongHocVien(khoaHoc.getSoLuongHocVien());
        dto.setNgayTao(khoaHoc.getNgayTao());
        
        if (khoaHoc.getGiangVien() != null) {
            dto.setTenGiangVien(khoaHoc.getGiangVien().getHoTen());
        }
        
        return dto;
    }
}
