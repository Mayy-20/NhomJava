package ut.nhomjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.nhomjava.model.KhoaHoc;
import ut.nhomjava.repository.KhoaHocRepository;

@Service
public class KhoaHocService {

    @Autowired
    private KhoaHocRepository khoaHocRepository;

    public List<KhoaHoc> findAll() {
        return khoaHocRepository.findAll();
    }

    public KhoaHoc save(KhoaHoc khoaHoc) {
        return khoaHocRepository.save(khoaHoc);
    }

    public KhoaHoc findById(Integer id) {
        Optional<KhoaHoc> khoaHoc = khoaHocRepository.findById(id);
        return khoaHoc.orElse(null);
    }

    public void deleteById(Integer id) {
        khoaHocRepository.deleteById(id);
    }
}