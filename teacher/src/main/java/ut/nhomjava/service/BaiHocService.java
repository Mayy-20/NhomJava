package ut.nhomjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.nhomjava.model.BaiHoc;
import ut.nhomjava.repository.BaiHocRepository;

@Service
public class BaiHocService {

    @Autowired
    private BaiHocRepository baiHocRepository;

    public List<BaiHoc> findAll() {
        return baiHocRepository.findAll();
    }

    public BaiHoc save(BaiHoc baiHoc) {
        return baiHocRepository.save(baiHoc);
    }

    public BaiHoc findById(Integer id) {
        Optional<BaiHoc> baiHoc = baiHocRepository.findById(id);
        return baiHoc.orElse(null);
    }

    public void deleteById(Integer id) {
        baiHocRepository.deleteById(id);
    }

    public List<BaiHoc> findByKhoaHocMaKhoaHoc(Integer maKhoaHoc) {
        return baiHocRepository.findByKhoaHoc_MaKhoaHocOrderByThuTuAsc(maKhoaHoc);
    }
}