package ut.nhomjava; // Đảm bảo gói này là gói gốc của ứng dụng

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Nếu AuthController nằm ngoài gói ut.nhomjava hoặc các gói con của nó,
// bạn có thể cần chỉ định rõ package để Spring scan.
// Ví dụ: @ComponentScan(basePackages = {"ut.nhomjava", "ut.nhomjava.controller", "ut.edu.hannah.config"})
public class TeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeacherApplication.class, args);
    }

}