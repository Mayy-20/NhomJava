package com.hannah.demo.service;

import com.hannah.demo.dto.ApiResponse;
import com.hannah.demo.dto.BaiDangDTO;
import com.hannah.demo.model.BaiDang;
import com.hannah.demo.repository.BaiDangRepository;
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
public class BaiDangService {
    
    @Autowired
    private BaiDangRepository baiDangRepository;
    
    public ApiResponse<Page<BaiDangDTO>> getAllPosts(int page, int size, String search, 
                                                    String trangThai, Integer maChuDe) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("ngayTao").descending());
            BaiDang.TrangThaiBaiDang status = null;
            
            if (trangThai != null && !trangThai.isEmpty()) {
                status = BaiDang.TrangThaiBaiDang.valueOf(trangThai);
            }
            
            Page<BaiDang> posts = baiDangRepository.findWithFilters(search, status, maChuDe, pageable);
            Page<BaiDangDTO> postDTOs = posts.map(this::convertToDTO);
            
            return ApiResponse.success(postDTOs);
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi lấy danh sách bài đăng: " + e.getMessage());
        }
    }
    
    public ApiResponse<BaiDangDTO> getPostById(Integer id) {
        try {
            Optional<BaiDang> post = baiDangRepository.findById(id);
            if (post.isPresent()) {
                return ApiResponse.success(convertToDTO(post.get()));
            }
            return ApiResponse.error("Không tìm thấy bài đăng");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi lấy thông tin bài đăng: " + e.getMessage());
        }
    }
    
    public ApiResponse<String> approvePost(Integer id) {
        try {
            Optional<BaiDang> post = baiDangRepository.findById(id);
            if (!post.isPresent()) {
                return ApiResponse.error("Không tìm thấy bài đăng");
            }
            
            BaiDang baiDang = post.get();
            baiDang.setTrangThai(BaiDang.TrangThaiBaiDang.DaDuyet);
            baiDangRepository.save(baiDang);
            
            return ApiResponse.success("Duyệt bài đăng thành công", "Approved");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi duyệt bài đăng: " + e.getMessage());
        }
    }
    
    public ApiResponse<String> hidePost(Integer id) {
        try {
            Optional<BaiDang> post = baiDangRepository.findById(id);
            if (!post.isPresent()) {
                return ApiResponse.error("Không tìm thấy bài đăng");
            }
            
            BaiDang baiDang = post.get();
            baiDang.setTrangThai(BaiDang.TrangThaiBaiDang.An);
            baiDangRepository.save(baiDang);
            
            return ApiResponse.success("Ẩn bài đăng thành công", "Hidden");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi ẩn bài đăng: " + e.getMessage());
        }
    }
    
    public ApiResponse<String> deletePost(Integer id) {
        try {
            if (!baiDangRepository.existsById(id)) {
                return ApiResponse.error("Không tìm thấy bài đăng");
            }
            
            baiDangRepository.deleteById(id);
            return ApiResponse.success("Xóa bài đăng thành công", "Deleted");
        } catch (Exception e) {
            return ApiResponse.error("Lỗi khi xóa bài đăng: " + e.getMessage());
        }
    }
    
    private BaiDangDTO convertToDTO(BaiDang baiDang) {
        BaiDangDTO dto = new BaiDangDTO();
        dto.setMaBaiDang(baiDang.getMaBaiDang());
        dto.setTieuDe(baiDang.getTieuDe());
        dto.setNoiDung(baiDang.getNoiDung());
        dto.setMaTacGia(baiDang.getMaTacGia());
        dto.setMaChuDe(baiDang.getMaChuDe());
        dto.setMaBaiHoc(baiDang.getMaBaiHoc());
        dto.setTrangThai(baiDang.getTrangThai());
        dto.setSoBaoCao(baiDang.getSoBaoCao());
        dto.setLuotXem(baiDang.getLuotXem());
        dto.setNgayTao(baiDang.getNgayTao());
        
        if (baiDang.getTacGia() != null) {
            dto.setTenTacGia(baiDang.getTacGia().getHoTen());
        }
        
        if (baiDang.getChuDe() != null) {
            dto.setTenChuDe(baiDang.getChuDe().getTenChuDe());
        }
        
        if (baiDang.getBaiHoc() != null) {
            dto.setTenBaiHoc(baiDang.getBaiHoc().getTenBaiHoc());
        }
        
        return dto;
    }
}
