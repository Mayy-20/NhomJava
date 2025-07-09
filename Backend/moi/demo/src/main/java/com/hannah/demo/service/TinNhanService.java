package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.TinNhan;
import com.hannah.demo.repository.TinNhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TinNhanService {
    @Autowired
    private TinNhanRepository tinNhanRepository;

    public List<TinNhan> getAllTinNhan() {
        return tinNhanRepository.findAll();
    }

    public Optional<TinNhan> getTinNhanById(int id) {
        return tinNhanRepository.findById(id);
    }

    public TinNhan createTinNhan(TinNhan tinNhan) {
        return tinNhanRepository.save(tinNhan);
    }

    public TinNhan updateTinNhan(int id, TinNhan tinNhanDetails) {
        TinNhan tinNhan = tinNhanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TinNhan not found with id: " + id));
        tinNhan.setMaPhien(tinNhanDetails.getMaPhien());
        tinNhan.setNguoiGui(tinNhanDetails.getNguoiGui());
        tinNhan.setNoiDung(tinNhanDetails.getNoiDung());
        tinNhan.setThoiGian(tinNhanDetails.getThoiGian());
        return tinNhanRepository.save(tinNhan);
    }

    public void deleteTinNhan(int id) {
        TinNhan tinNhan = tinNhanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TinNhan not found with id: " + id));
        tinNhanRepository.delete(tinNhan);
    }
}