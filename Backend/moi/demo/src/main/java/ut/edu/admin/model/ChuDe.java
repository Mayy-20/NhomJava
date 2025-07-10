package ut.edu.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chude")
@Data
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maChuDe;

    @Column(nullable = false)
    private String tenChuDe;

    private String moTa;
    private String icon;
}
