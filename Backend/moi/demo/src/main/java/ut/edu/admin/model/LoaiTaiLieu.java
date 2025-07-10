package ut.edu.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "loaitailieu")
@Data
public class LoaiTaiLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maLoaiTaiLieu;

    @Column(unique = true, nullable = false)
    private String tenLoai;

    private String moTa;
}
