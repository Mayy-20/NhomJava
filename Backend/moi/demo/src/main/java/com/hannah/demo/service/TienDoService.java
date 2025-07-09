package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.TienDo;
import com.hannah.demo.repository.TienDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TienDoService {
    @Autowired
    private TienDoRepository tienDoRepository;

    public List<TienDo> getAllTienDo() {
        return tienDoRepository.findAll();
    }

    public Optional<TienDo> getTienDoById(int id) {
        return tienDoRepository.findById(id);
    }

    public TienDo createTienDo(TienDo tienDo) {
        return tienDoRepository.save(tienDo);
    }

    public TienDo updateTienDo(int id, TienDo tienDoDetails) {
        TienDo tienDo = tienDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TienDo not found with id: " + id));
        tienDo.setMaNguoiDung(tienDoDetails.getMaNguoiDung());
        tienDo.setMaKhoaHoc(tienDoDetails.getMaKhoaHoc());
        tienDo.setMaBaiHoc(tienDoDetails.getMaBaiHoc());
        tienDo.setPhanTram(tienDoDetails.getPhanTram());
        tienDo.setThoiGianHoc(tienDoDetails.getThoiGianHoc());
        tienDo.setHoanThanh(tienDoDetails.getHoanThanh());
        tienDo.setLanCuoiHoc(tienDoDetails.getLanCuoiHoc());
        return tienDoRepository.save(tienDo);
    }

    public void deleteTienDo(int id) {
        TienDo tienDo = tienDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TienDo not found with id: " + id));
        tienDoRepository.delete(tienDo);
    }
}