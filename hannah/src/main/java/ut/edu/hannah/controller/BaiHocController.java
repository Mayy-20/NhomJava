
package ut.edu.hannah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ut.edu.hannah.model.BaiHoc;
import ut.edu.hannah.services.BaiHocService;

import java.util.List;

@Controller
public class BaiHocController {

    private final BaiHocService baiHocService;

    public BaiHocController(BaiHocService baiHocService) {
        this.baiHocService = baiHocService;
    }

    @GetMapping("/courses/lesson/{maKhoaHoc}/{maBaiHoc}")
    public String redirectToLearningByLessonId(@PathVariable Integer maKhoaHoc,
                                               @PathVariable Integer maBaiHoc) {
        List<BaiHoc> lessons = baiHocService.findByKhoaHoc(maKhoaHoc);
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getMaBaiHoc().equals(maBaiHoc)) {
                return "redirect:/courses/learning/" + maKhoaHoc + "?lesson=" + (i + 1);
            }
        }
        return "redirect:/courses";
    }
}
