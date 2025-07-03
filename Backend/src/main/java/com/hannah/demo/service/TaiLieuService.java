package com.hannah.demo.service;

import com.hannah.demo.dto.ApiResponse;
import com.hannah.demo.dto.TaiLieuDTO;
import com.hannah.demo.model.TaiLieu;
import com.hannah.demo.repository.TaiLieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class TaiLieuService {
    
    @Autowired
    private TaiLieuRepository taiLieuRepository;
    
    private final String uploadDir = "uploads/documents/";
    
    public ApiResponse<Page<TaiLieuDTO>> getAllDocuments(int page, int size, String search, 
                                                        String trangThai, Integer maTacGia) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("ngayTao").descending());
            TaiLieu.TrangThaiTaiLieu status = null;
            
            if (trangThai != null && !trangThai.isEmpty()) {
                status = TaiLieu.TrangThaiTaiLieu.valueOf(trangThai);
            }
            
            Page<TaiLieu> documents = taiLieuRepository.findWithFilters(search, status, maTacGia, pageable);
            Page<TaiLieuDTO> documentDTOs = documents.map(this::convertToDTO);
            
            return ApiResponse.success(documentDTOs);
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi lấy danh sách tài liệu: " + e.getMessage());
        }
    }
    
    public ApiResponse<TaiLieuDTO> getDocumentById(Integer id) {
        try {
            Optional<TaiLieu> document = taiLieuRepository.findById(id);
            if (document.isPresent()) {
                return ApiResponse.success(convertToDTO(document.get()));
            }
            return ApiResponse.error("Không tìm thấy tài liệu");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi lấy thông tin tài liệu: " + e.getMessage());
        }
    }
    
    public ApiResponse<TaiLieuDTO> uploadDocument(MultipartFile file, TaiLieu taiLieu) {
        try {
            // Create upload directory if not exists
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            
            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || !originalFilename.contains(".")) {
                return ApiResponse.error("Tên file không hợp lệ");
            }
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
            
            // Save file
            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath);
            
            // Set file info
            taiLieu.setDuongDan(uploadDir + uniqueFilename);
            taiLieu.setKichThuoc(file.getSize());
            taiLieu.setTenTaiLieu(originalFilename);
            
            TaiLieu savedDocument = taiLieuRepository.save(taiLieu);
            return ApiResponse.success("Tải lên tài liệu thành công", convertToDTO(savedDocument));
        } catch (IOException e) {
            return ApiResponse.error("Lỗi khi tải lên file: " + e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi lưu tài liệu: " + e.getMessage());
        }
    }
    
    public ApiResponse<String> approveDocument(Integer id) {
        try {
            Optional<TaiLieu> document = taiLieuRepository.findById(id);
            if (!document.isPresent()) {
                return ApiResponse.error("Không tìm thấy tài liệu");
            }
            
            TaiLieu taiLieu = document.get();
            taiLieu.setTrangThai(TaiLieu.TrangThaiTaiLieu.DaDuyet);
            taiLieuRepository.save(taiLieu);
            
            return ApiResponse.success("Duyệt tài liệu thành công", "Approved");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi duyệt tài liệu: " + e.getMessage());
        }
    }
    
    public ApiResponse<String> rejectDocument(Integer id, String reason) {
        try {
            Optional<TaiLieu> document = taiLieuRepository.findById(id);
            if (!document.isPresent()) {
                return ApiResponse.error("Không tìm thấy tài liệu");
            }
            
            TaiLieu taiLieu = document.get();
            taiLieu.setTrangThai(TaiLieu.TrangThaiTaiLieu.TuChoi);
            taiLieuRepository.save(taiLieu);
            
            return ApiResponse.success("Từ chối tài liệu thành công", "Rejected");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi từ chối tài liệu: " + e.getMessage());
        }
    }
    
    public ApiResponse<String> deleteDocument(Integer id) {
        try {
            Optional<TaiLieu> document = taiLieuRepository.findById(id);
            if (!document.isPresent()) {
                return ApiResponse.error("Không tìm thấy tài liệu");
            }
            
            TaiLieu taiLieu = document.get();
            
            // Delete physical file
            if (taiLieu.getDuongDan() != null) {
                Path filePath = Paths.get(taiLieu.getDuongDan());
                Files.deleteIfExists(filePath);
            }
            
            taiLieuRepository.deleteById(id);
            return ApiResponse.success("Xóa tài liệu thành công", "Deleted");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi xóa tài liệu: " + e.getMessage());
        }
    }
    
    private TaiLieuDTO convertToDTO(TaiLieu taiLieu) {
        TaiLieuDTO dto = new TaiLieuDTO();
        dto.setMaTaiLieu(taiLieu.getMaTaiLieu());
        dto.setTenTaiLieu(taiLieu.getTenTaiLieu());
        dto.setMaLoaiTaiLieu(taiLieu.getMaLoaiTaiLieu());
        dto.setMaBaiHoc(taiLieu.getMaBaiHoc());
        dto.setMaTacGia(taiLieu.getMaTacGia());
        dto.setDuongDan(taiLieu.getDuongDan());
        dto.setKichThuoc(taiLieu.getKichThuoc());
        dto.setLuotTai(taiLieu.getLuotTai());
        dto.setDanhGia(taiLieu.getDanhGia());
        dto.setTrangThai(taiLieu.getTrangThai());
        dto.setNgayTao(taiLieu.getNgayTao());
        
        if (taiLieu.getBaiHoc() != null) {
            dto.setTenBaiHoc(taiLieu.getBaiHoc().getTenBaiHoc());
        }
        
        if (taiLieu.getTacGia() != null) {
            dto.setTenTacGia(taiLieu.getTacGia().getHoTen());
        }
        
        // Determine file type from filename
        if (taiLieu.getTenTaiLieu() != null) {
            String extension = taiLieu.getTenTaiLieu().substring(taiLieu.getTenTaiLieu().lastIndexOf(".") + 1).toUpperCase();
            dto.setLoaiFile(extension);
        }
        
        return dto;
    }
}
