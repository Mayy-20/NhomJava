package ut.edu.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vaitro")
@Data
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maVaiTro;

    @Column(unique = true, nullable = false)
    private String tenVaiTro;

    @Column(nullable = false)
    private String moTa;
}
