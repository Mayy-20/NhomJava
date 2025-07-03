package ut.edu.hannah.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ut.edu.hannah.model.BaiDang;
import ut.edu.hannah.services.BaiDangService;
import ut.edu.hannah.services.ChuDeService;

/**
 * Controller để xử lý các yêu cầu liên quan đến bài đăng trong cộng đồng.
 */
@Controller
public class BaiDangController {
    private final BaiDangService baiDangService;
    private final ChuDeService chuDeService;

    public BaiDangController(BaiDangService baiDangService, ChuDeService chuDeService) {
        this.baiDangService = baiDangService;
        this.chuDeService = chuDeService;
    }

    /**
     * Hiển thị danh sách bài đăng theo chủ đề hoặc trạng thái.
     * @param maChuDe Mã chủ đề (tùy chọn).
     * @param model Model để truyền dữ liệu tới view.
     * @return Tên template Thymeleaf.
     */
    @GetMapping("/community")
    public String listBaiDang(@RequestParam(required = false) Integer maChuDe, Model model) {
        List<BaiDang> baiDangList;
        if (maChuDe != null) {
            if (chuDeService.findById(maChuDe).isEmpty()) {
                model.addAttribute("error", "Chủ đề không tồn tại");
                baiDangList = List.of();
            } else {
                baiDangList = baiDangService.findByChuDe(maChuDe);
            }
        } else {
            baiDangList = baiDangService.findByTrangThai(BaiDang.TrangThai.DaDuyet);
        }
        model.addAttribute("baiDangList", baiDangList);
        model.addAttribute("chuDeList", chuDeService.getAllChuDe());
        return "community";
    }

    /**
     * Tạo bài đăng mới.
     * @param tieuDe Tiêu đề bài đăng.
     * @param noiDung Nội dung bài đăng.
     * @param maTacGia Mã tác giả.
     * @param maChuDe Mã chủ đề (tùy chọn).
     * @param maBaiHoc Mã bài học (tùy chọn).
     * @param model Model để truyền lỗi (nếu có).
     * @return Chuyển hướng hoặc trang community.
     */
    @PostMapping("/community")
    public String createBaiDang(@RequestParam String tieuDe,
                               @RequestParam String noiDung,
                               @RequestParam Integer maTacGia,
                               @RequestParam(required = false) Integer maChuDe,
                               @RequestParam(required = false) Integer maBaiHoc,
                               Model model) {
        try {
            if (tieuDe == null || tieuDe.trim().isEmpty()) {
                throw new IllegalArgumentException("Tiêu đề không được để trống");
            }
            if (noiDung == null || noiDung.trim().isEmpty()) {
                throw new IllegalArgumentException("Nội dung không được để trống");
            }
            if (maTacGia == null) {
                throw new IllegalArgumentException("Mã tác giả không được để trống");
            }
            baiDangService.createBaiDang(tieuDe, noiDung, maTacGia, maChuDe, maBaiHoc);
            return "redirect:/community";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("chuDeList", chuDeService.getAllChuDe());
            return "community";
        }
    }
}