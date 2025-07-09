package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.ChuDe;
import com.hannah.demo.repository.ChuDeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChuDeService {
    @Autowired
    private ChuDeRepository chuDeRepository;

    public List<ChuDe> getAllChuDe() {
        return chuDeRepository.findAll();
    }

    public Optional<ChuDe> getChuDeById(int id) {
        return chuDeRepository.findById(id);
    }

    public ChuDe createChuDe(ChuDe chuDe) {
        return chuDeRepository.save(chuDe);
    }

    public ChuDe updateChuDe(int id, ChuDe chuDeDetails) {
        ChuDe chuDe = chuDeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ChuDe not found with id: " + id));
        chuDe.setTenChuDe(chuDeDetails.getTenChuDe());
        chuDe.setMoTa(chuDeDetails.getMoTa());
        chuDe.setIcon(chuDeDetails.getIcon());
        return chuDeRepository.save(chuDe);
    }

    public void deleteChuDe(int id) {
        ChuDe chuDe = chuDeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ChuDe not found with id: " + id));
        chuDeRepository.delete(chuDe);
    }
}