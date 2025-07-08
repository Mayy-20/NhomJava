package ut.nhomjava.service; // Đảm bảo đúng package service của bạn

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ut.nhomjava.model.BaiDang; // Import Entity BaiDang
import ut.nhomjava.repository.BaiDangRepository; // Import BaiDangRepository
import java.util.List;
import java.util.Optional;

@Service
public class BaiDangService {

    @Autowired
    private BaiDangRepository baiDangRepository; // Inject BaiDangRepository

    public List<BaiDang> findAll() {
        return baiDangRepository.findAll();
    }

    public BaiDang save(BaiDang baiDang) {
        return baiDangRepository.save(baiDang);
    }

    public BaiDang findById(Integer id) { // Kiểu ID là Integer
        Optional<BaiDang> baiDang = baiDangRepository.findById(id);
        return baiDang.orElse(null);
    }

    public void deleteById(Integer id) { // Kiểu ID là Integer
        baiDangRepository.deleteById(id);
    }
}