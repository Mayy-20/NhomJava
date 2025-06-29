package ut.edu.hannah.services;

import ut.edu.hannah.model.TaiLieu;

import java.util.List;

// Interface định nghĩa các phương thức liên quan đến tài liệu
public interface ITaiLieuService {
    List<TaiLieu> findByMaBaiHoc(Integer maBaiHoc);
}