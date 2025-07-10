package ut.edu.admin.service;

import ut.edu.admin.dto.TaiLieuDTO;
import ut.edu.admin.model.TaiLieu;
import ut.edu.admin.repository.TaiLieuRepository;
import ut.edu.admin.repository.BaiHocRepository;
import ut.edu.admin.repository.NguoiDungRepository;
import ut.edu.admin.repository.LoaiTaiLieuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaiLieuService {
    private final TaiLieuRepository taiLieuRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final BaiHocRepository baiHocRepository;
    private final LoaiTaiLieuRepository loaiTaiLieuRepository;

    public List<TaiLieuDTO> getAllDocuments() {
        return taiLieuRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TaiLieuDTO createDocument(TaiLieuDTO dto) {
        TaiLieu taiLieu = new TaiLieu();
        taiLieu.setTenTaiLieu(dto.getTenTaiLieu());
        taiLieu.setMaLoaiTaiLieu(loaiTaiLieuRepository.findById(dto.getMaLoaiTaiLieu())
                .orElseThrow(() -> new RuntimeException("Document type not found")));
        taiLieu.setMaBaiHoc(baiHocRepository.findById(dto.getMaBaiHoc())
                .orElseThrow(() -> new RuntimeException("Lesson not found")));
        taiLieu.setMaTacGia(nguoiDungRepository.findById(dto.getMaTacGia())
                .orElseThrow(() -> new RuntimeException("Author not found")));
        taiLieu.setDuongDan(dto.getDuongDan());
        taiLieu.setKichThuoc(dto.getKichThuoc());
        taiLieu.setLuotTai(dto.getLuotTai());
        taiLieu.setDanhGia(dto.getDanhGia());
        taiLieu.setTrangThai(TaiLieu.TrangThaiTaiLieu.valueOf(dto.getTrangThai()));
        taiLieu = taiLieuRepository.save(taiLieu);
        return convertToDTO(taiLieu);
    }

    public TaiLieuDTO approveDocument(Integer id) {
        TaiLieu taiLieu = taiLieuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        taiLieu.setTrangThai(TaiLieu.TrangThaiTaiLieu.DaDuyet);
        taiLieu = taiLieuRepository.save(taiLieu);
        return convertToDTO(taiLieu);
    }

    public TaiLieuDTO rejectDocument(Integer id) {
        TaiLieu taiLieu = taiLieuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        taiLieu.setTrangThai(TaiLieu.TrangThaiTaiLieu.TuChoi);
        taiLieu = taiLieuRepository.save(taiLieu);
        return convertToDTO(taiLieu);
    }

    public void deleteDocument(Integer id) {
        TaiLieu taiLieu = taiLieuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        taiLieuRepository.delete(taiLieu);
    }

    private TaiLieuDTO convertToDTO(TaiLieu taiLieu) {
        TaiLieuDTO dto = new TaiLieuDTO();
        dto.setMaTaiLieu(taiLieu.getMaTaiLieu());
        dto.setTenTaiLieu(taiLieu.getTenTaiLieu());
        dto.setMaLoaiTaiLieu(taiLieu.getMaLoaiTaiLieu().getMaLoaiTaiLieu());
        dto.setMaBaiHoc(taiLieu.getMaBaiHoc().getMaBaiHoc());
        dto.setMaTacGia(taiLieu.getMaTacGia().getMaNguoiDung());
        dto.setDuongDan(taiLieu.getDuongDan());
        dto.setKichThuoc(taiLieu.getKichThuoc());
        dto.setLuotTai(taiLieu.getLuotTai());
        dto.setDanhGia(taiLieu.getDanhGia());
        dto.setTrangThai(taiLieu.getTrangThai().name());
        dto.setNgayTao(taiLieu.getNgayTao());
        return dto;
    }
}
