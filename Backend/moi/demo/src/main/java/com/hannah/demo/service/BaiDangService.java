package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.BaiDang;
import com.hannah.demo.repository.BaiDangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BaiDangService {

    @Autowired
    private BaiDangRepository baiDangRepository;

    public Page<BaiDang> findAll(String search, String status, String category, Pageable pageable) {
        Specification<BaiDang> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. Lọc theo từ khóa tìm kiếm (trong tiêu đề hoặc nội dung)
            if (StringUtils.hasText(search)) {
                String searchPattern = "%" + search.toLowerCase() + "%";
                Predicate titleLike = criteriaBuilder.like(criteriaBuilder.lower(root.get("tieuDe")), searchPattern);
                Predicate contentLike = criteriaBuilder.like(criteriaBuilder.lower(root.get("noiDung")), searchPattern);
                predicates.add(criteriaBuilder.or(titleLike, contentLike));
            }

            // 2. Lọc theo trạng thái
            if (StringUtils.hasText(status)) {
                try {
                    // Giả sử front-end gửi giá trị enum chuẩn (ví dụ: "ChoDuyet", "DaDuyet")
                    BaiDang.TrangThai trangThaiEnum = BaiDang.TrangThai.valueOf(status);
                    predicates.add(criteriaBuilder.equal(root.get("trangThai"), trangThaiEnum));
                } catch (IllegalArgumentException e) {
                    // Bỏ qua nếu giá trị status không hợp lệ
                }
            }
            
            // 3. Lọc theo mã chủ đề (category id)
            if (StringUtils.hasText(category)) {
                try {
                    Integer chuDeId = Integer.parseInt(category);
                    predicates.add(criteriaBuilder.equal(root.get("maChuDe").get("maChuDe"), chuDeId));
                } catch (NumberFormatException e) {
                    // Bỏ qua nếu category không phải là số hợp lệ
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        
        return baiDangRepository.findAll(spec, pageable);
    }

    public BaiDang findById(Integer id) {
        return baiDangRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BaiDang not found with id: " + id));
    }

    public BaiDang createPost(BaiDang baiDang) {
        baiDang.setTrangThai(BaiDang.TrangThai.ChoDuyet);
        baiDang.setNgayTao(LocalDateTime.now());
        return baiDangRepository.save(baiDang);
    }

    public BaiDang updatePost(Integer id, BaiDang postDetails) {
        BaiDang post = findById(id);
        post.setTieuDe(postDetails.getTieuDe());
        post.setNoiDung(postDetails.getNoiDung());
        // Cập nhật thêm trường category nếu cần
        if (postDetails.getMaChuDe() != null) {
            post.setMaChuDe(postDetails.getMaChuDe());
        }
        return baiDangRepository.save(post);
    }

    public void deletePost(Integer id) {
        BaiDang post = findById(id);
        baiDangRepository.delete(post);
    }
    
    public void approvePost(Integer id) {
        BaiDang post = findById(id);
        post.setTrangThai(BaiDang.TrangThai.DaDuyet);
        baiDangRepository.save(post);
    }

    public void hidePost(Integer id) {
        BaiDang post = findById(id);
        post.setTrangThai(BaiDang.TrangThai.An);
        baiDangRepository.save(post);
    }
}