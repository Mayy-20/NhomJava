package ut.edu.admin.service;

import ut.edu.admin.dto.BaiDangDTO;
import ut.edu.admin.model.BaiDang;
import ut.edu.admin.model.ChuDe;
import ut.edu.admin.model.BaiHoc;
import ut.edu.admin.repository.BaiDangRepository;
import ut.edu.admin.repository.ChuDeRepository;
import ut.edu.admin.repository.BaiHocRepository;
import ut.edu.admin.repository.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaiDangService {
    private final BaiDangRepository baiDangRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final ChuDeRepository chuDeRepository;
    private final BaiHocRepository baiHocRepository;

    public Page<BaiDangDTO> getAllPosts(Pageable pageable) {
        return baiDangRepository.findAll(pageable)
                .map(this::convertToDTO);
    }

    public BaiDangDTO getPostById(Integer id) {
        BaiDang baiDang = baiDangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return convertToDTO(baiDang);
    }

    public BaiDangDTO createPost(BaiDangDTO dto) {
        BaiDang baiDang = new BaiDang();
        baiDang.setTieuDe(dto.getTieuDe());
        baiDang.setNoiDung(dto.getNoiDung());
        baiDang.setTacGia(nguoiDungRepository.findById(dto.getMaTacGia())
                .orElseThrow(() -> new RuntimeException("Author not found")));
        if (dto.getMaChuDe() != null) {
            baiDang.setChuDe(chuDeRepository.findById(dto.getMaChuDe())
                    .orElseThrow(() -> new RuntimeException("Category not found")));
        }
        if (dto.getMaBaiHoc() != null) {
            baiDang.setBaiHoc(baiHocRepository.findById(dto.getMaBaiHoc())
                    .orElseThrow(() -> new RuntimeException("Lesson not found")));
        }
        baiDang.setTrangThai(BaiDang.TrangThaiBaiDang.valueOf(dto.getTrangThai()));
        baiDang = baiDangRepository.save(baiDang);
        return convertToDTO(baiDang);
    }

    public BaiDangDTO approvePost(Integer id) {
        BaiDang baiDang = baiDangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        baiDang.setTrangThai(BaiDang.TrangThaiBaiDang.DaDuyet);
        baiDang = baiDangRepository.save(baiDang);
        return convertToDTO(baiDang);
    }

    public BaiDangDTO hidePost(Integer id) {
        BaiDang baiDang = baiDangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        baiDang.setTrangThai(BaiDang.TrangThaiBaiDang.An);
        baiDang = baiDangRepository.save(baiDang);
        return convertToDTO(baiDang);
    }

    public void deletePost(Integer id) {
        BaiDang baiDang = baiDangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        baiDangRepository.delete(baiDang);
    }

    private BaiDangDTO convertToDTO(BaiDang baiDang) {
        BaiDangDTO dto = new BaiDangDTO();
        dto.setMaBaiDang(baiDang.getMaBaiDang());
        dto.setTieuDe(baiDang.getTieuDe());
        dto.setNoiDung(baiDang.getNoiDung());
        dto.setMaTacGia(baiDang.getTacGia().getMaNguoiDung());
        dto.setMaChuDe(baiDang.getChuDe() != null ? baiDang.getChuDe().getMaChuDe() : null);
        dto.setMaBaiHoc(baiDang.getBaiHoc() != null ? baiDang.getBaiHoc().getMaBaiHoc() : null);
        dto.setTrangThai(baiDang.getTrangThai().name());
        dto.setSoBaoCao(baiDang.getSoBaoCao());
        dto.setLuotXem(baiDang.getLuotXem());
        dto.setNgayTao(baiDang.getNgayTao());
        return dto;
    }
}