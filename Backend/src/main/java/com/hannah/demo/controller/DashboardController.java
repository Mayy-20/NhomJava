package com.hannah.demo.controller;

import com.hannah.demo.dto.ApiResponse;
import com.hannah.demo.model.NguoiDung;
import com.hannah.demo.model.KhoaHoc;
import com.hannah.demo.model.BaiDang;
import com.hannah.demo.model.TaiLieu;
import com.hannah.demo.repository.NguoiDungRepository;
import com.hannah.demo.repository.KhoaHocRepository;
import com.hannah.demo.repository.BaiDangRepository;
import com.hannah.demo.repository.TaiLieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin-dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {
    
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    
    @Autowired
    private KhoaHocRepository khoaHocRepository;
    
    @Autowired
    private BaiDangRepository baiDangRepository;

    @Autowired
    private TaiLieuRepository taiLieuRepository;
    
    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getDashboardStats() {
        try {
            Map<String, Object> stats = new HashMap<>();
            
            // User statistics
            Long totalUsers = nguoiDungRepository.count();
            Long activeUsers = nguoiDungRepository.countByTrangThai(NguoiDung.TrangThaiNguoiDung.Active);
            Long pendingUsers = nguoiDungRepository.countByTrangThai(NguoiDung.TrangThaiNguoiDung.Inactive);
            
            stats.put("totalUsers", totalUsers);
            stats.put("activeUsers", activeUsers);
            stats.put("pendingUsers", pendingUsers);
            
            // Course statistics
            Long totalCourses = khoaHocRepository.count();
            Long activeCourses = khoaHocRepository.countByTrangThai(KhoaHoc.TrangThaiKhoaHoc.HoatDong);
            Long pendingCourses = khoaHocRepository.countByTrangThai(KhoaHoc.TrangThaiKhoaHoc.ChoDuyet);
            
            stats.put("totalCourses", totalCourses);
            stats.put("activeCourses", activeCourses);
            stats.put("pendingCourses", pendingCourses);
            
            // Post statistics
            Long totalPosts = baiDangRepository.count();
            Long approvedPosts = baiDangRepository.countByTrangThai(BaiDang.TrangThaiBaiDang.DaDuyet);
            Long pendingPosts = baiDangRepository.countByTrangThai(BaiDang.TrangThaiBaiDang.ChoDuyet);
            
            stats.put("totalPosts", totalPosts);
            stats.put("approvedPosts", approvedPosts);
            stats.put("pendingPosts", pendingPosts);

            // Document statistics
            Long totalDocuments = taiLieuRepository.count();
            Long approvedDocuments = taiLieuRepository.countByTrangThai(TaiLieu.TrangThaiTaiLieu.DaDuyet);
            Long pendingDocuments = taiLieuRepository.countByTrangThai(TaiLieu.TrangThaiTaiLieu.ChoDuyet);

            stats.put("totalDocuments", totalDocuments);
            stats.put("approvedDocuments", approvedDocuments);
            stats.put("pendingDocuments", pendingDocuments);
            
            return ResponseEntity.ok(ApiResponse.success(stats));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error("Lỗi khi lấy thống kê: " + e.getMessage()));
        }
    }
}
