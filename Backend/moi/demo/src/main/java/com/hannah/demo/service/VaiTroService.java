package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.VaiTro;
import com.hannah.demo.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaiTroService {
    @Autowired
    private VaiTroRepository vaiTroRepository;

    public List<VaiTro> getAllVaiTro() {
        return vaiTroRepository.findAll();
    }

    public Optional<VaiTro> getVaiTroById(int id) {
        return vaiTroRepository.findById(id);
    }

    public VaiTro createVaiTro(VaiTro vaiTro) {
        return vaiTroRepository.save(vaiTro);
    }

    public VaiTro updateVaiTro(int id, VaiTro vaiTroDetails) {
        VaiTro vaiTro = vaiTroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VaiTro not found with id: " + id));
        vaiTro.setTenVaiTro(vaiTroDetails.getTenVaiTro());
        vaiTro.setMoTa(vaiTroDetails.getMoTa());
        return vaiTroRepository.save(vaiTro);
    }

    public void deleteVaiTro(int id) {
        VaiTro vaiTro = vaiTroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VaiTro not found with id: " + id));
        vaiTroRepository.delete(vaiTro);
    }
}