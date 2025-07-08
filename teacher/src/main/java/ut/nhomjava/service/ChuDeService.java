package ut.nhomjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.nhomjava.model.ChuDe;
import ut.nhomjava.repository.ChuDeRepository;

@Service
public class ChuDeService {

    @Autowired
    private ChuDeRepository chuDeRepository;

    public List<ChuDe> findAll() {
        return chuDeRepository.findAll();
    }

    public ChuDe save(ChuDe chuDe) {
        return chuDeRepository.save(chuDe);
    }

    public ChuDe findById(Integer id) {
        Optional<ChuDe> chuDe = chuDeRepository.findById(id);
        return chuDe.orElse(null);
    }

    public void deleteById(Integer id) {
        chuDeRepository.deleteById(id);
    }

    public Optional<ChuDe> findByTenChuDe(String tenChuDe) {
        return chuDeRepository.findByTenChuDe(tenChuDe);
    }
}