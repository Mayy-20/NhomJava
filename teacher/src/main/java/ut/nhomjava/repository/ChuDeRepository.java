package ut.nhomjava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ut.nhomjava.model.ChuDe;

@Repository
public interface ChuDeRepository extends JpaRepository<ChuDe, Integer> {
    Optional<ChuDe> findByTenChuDe(String tenChuDe);
}