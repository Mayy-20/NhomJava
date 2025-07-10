package ut.edu.admin.controller;

import ut.edu.admin.dto.ChuDeDTO;
import ut.edu.admin.model.ChuDe;
import ut.edu.admin.repository.ChuDeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class ChuDeController {
    private final ChuDeRepository chuDeRepository;

    @GetMapping
    public ResponseEntity<List<ChuDeDTO>> getAllCategories() {
        List<ChuDeDTO> categories = chuDeRepository.findAll().stream()
                .map(chuDe -> {
                    ChuDeDTO dto = new ChuDeDTO();
                    dto.setMaChuDe(chuDe.getMaChuDe());
                    dto.setTenChuDe(chuDe.getTenChuDe());
                    dto.setMoTa(chuDe.getMoTa());
                    dto.setIcon(chuDe.getIcon());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }
}