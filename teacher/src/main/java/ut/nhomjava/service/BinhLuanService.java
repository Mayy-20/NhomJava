package ut.nhomjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.nhomjava.model.BinhLuan;
import ut.nhomjava.repository.BinhLuanRepository;

@Service
public class BinhLuanService {

    @Autowired
    private BinhLuanRepository binhLuanRepository;

    public List<BinhLuan> findAll() {
        return binhLuanRepository.findAll();
    }

    public BinhLuan save(BinhLuan binhLuan) {
        return binhLuanRepository.save(binhLuan);
    }

    public BinhLuan findById(Integer id) {
        Optional<BinhLuan> binhLuan = binhLuanRepository.findById(id);
        return binhLuan.orElse(null);
    }

    public void deleteById(Integer id) {
        binhLuanRepository.deleteById(id);
    }

    // --- THÊM PHƯƠNG THỨC NÀY ---
    // Phương thức tìm kiếm bình luận theo Mã Bài Đăng
    public List<BinhLuan> findByBaiDangMaBaiDang(Integer maBaiDang) {
        return binhLuanRepository.findByBaiDang_MaBaiDang(maBaiDang);
    }
    // --- KẾT THÚC PHẦN THÊM ---
}