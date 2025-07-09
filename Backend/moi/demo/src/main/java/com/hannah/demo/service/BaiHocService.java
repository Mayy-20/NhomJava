package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.BaiHoc;
import com.hannah.demo.repository.BaiHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaiHocService {
    @Autowired
    private BaiHocRepository baiHocRepository;

    public List<BaiHoc> getAllBaiHoc() {
        return baiHocRepository.findAll();
    }

    public Optional<BaiHoc> getBaiHocById(int id) {
        return baiHocRepository.findById(id);
    }

    public BaiHoc createBaiHoc(BaiHoc baiHoc) {
        return baiHocRepository.save(baiHoc);
    }

    public BaiHoc updateBaiHoc(int id, BaiHoc baiHocDetails) {
        BaiHoc baiHoc = baiHocRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BaiHoc not found with id: " + id));
        baiHoc.setTenBaiHoc(baiHocDetails.getTenBaiHoc());
        baiHoc.setMaKhoaHoc(baiHocDetails.getMaKhoaHoc());
        baiHoc.setThuTu(baiHocDetails.getThuTu());
        baiHoc.setVideoURL(baiHocDetails.getVideoURL());
        baiHoc.setCapDo(baiHocDetails.getCapDo());
        baiHoc.setThoiLuong(baiHocDetails.getThoiLuong());
        return baiHocRepository.save(baiHoc);
    }

    public void deleteBaiHoc(int id) {
        BaiHoc baiHoc = baiHocRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BaiHoc not found with id: " + id));
        baiHocRepository.delete(baiHoc);
    }
}