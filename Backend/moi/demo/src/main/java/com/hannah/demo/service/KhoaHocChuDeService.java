package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.KhoaHocChuDe;
import com.hannah.demo.model.KhoaHocChuDeId;
import com.hannah.demo.repository.KhoaHocChuDeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhoaHocChuDeService {
    @Autowired
    private KhoaHocChuDeRepository khoaHocChuDeRepository;

    public List<KhoaHocChuDe> getAllKhoaHocChuDe() {
        return khoaHocChuDeRepository.findAll();
    }

    public Optional<KhoaHocChuDe> getKhoaHocChuDeById(KhoaHocChuDeId id) {
        return khoaHocChuDeRepository.findById(id);
    }

    public KhoaHocChuDe createKhoaHocChuDe(KhoaHocChuDe khoaHocChuDe) {
        return khoaHocChuDeRepository.save(khoaHocChuDe);
    }

    public void deleteKhoaHocChuDe(KhoaHocChuDeId id) {
        KhoaHocChuDe khoaHocChuDe = khoaHocChuDeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KhoaHocChuDe not found with id: " + id));
        khoaHocChuDeRepository.delete(khoaHocChuDe);
    }
}