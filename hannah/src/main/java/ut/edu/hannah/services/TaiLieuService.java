package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.TaiLieu;
import ut.edu.hannah.repository.TaiLieuRepository;
import ut.edu.hannah.repository.LoaiTaiLieuRepository;

import java.util.List;

@Service
public class TaiLieuService implements ITaiLieuService {

    private final TaiLieuRepository taiLieuRepository;
    private final LoaiTaiLieuRepository loaiTaiLieuRepository;

    public TaiLieuService(TaiLieuRepository taiLieuRepository, LoaiTaiLieuRepository loaiTaiLieuRepository) {
        this.taiLieuRepository = taiLieuRepository;
        this.loaiTaiLieuRepository = loaiTaiLieuRepository;
    }

    @Override
    public List<TaiLieu> findByMaBaiHoc(Integer maBaiHoc) {
        return taiLieuRepository.findByBaiHocMaBaiHoc(maBaiHoc);
    }
}