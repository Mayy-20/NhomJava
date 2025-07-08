package ut.nhomjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.nhomjava.model.KhoaHoc;
import ut.nhomjava.model.NguoiDung;
import ut.nhomjava.model.TienDo;
import ut.nhomjava.repository.TienDoRepository;

@Service
public class TienDoService {

    @Autowired
    private TienDoRepository tienDoRepository;

    public List<TienDo> findAll() {
        return tienDoRepository.findAll();
    }

    public TienDo save(TienDo tienDo) {
        return tienDoRepository.save(tienDo);
    }

    public TienDo findById(Integer id) {
        Optional<TienDo> tienDo = tienDoRepository.findById(id);
        return tienDo.orElse(null);
    }

    public void deleteById(Integer id) {
        tienDoRepository.deleteById(id);
    }

    public Optional<TienDo> findByNguoiDungAndKhoaHoc(NguoiDung nguoiDung, KhoaHoc khoaHoc) {
        return tienDoRepository.findByNguoiDungAndKhoaHoc(nguoiDung, khoaHoc);
    }

    public List<TienDo> findByNguoiDungMaNguoiDung(Integer maNguoiDung) {
        return tienDoRepository.findByNguoiDung_MaNguoiDung(maNguoiDung);
    }

    public List<TienDo> findByKhoaHocMaKhoaHoc(Integer maKhoaHoc) {
        return tienDoRepository.findByKhoaHoc_MaKhoaHoc(maKhoaHoc);
    }
}