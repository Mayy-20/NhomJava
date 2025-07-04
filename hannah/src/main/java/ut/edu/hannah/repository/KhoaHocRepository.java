package ut.edu.hannah.repository;

  import org.springframework.data.jpa.repository.JpaRepository;
  import org.springframework.data.jpa.repository.Query;
  import org.springframework.stereotype.Repository;
  import ut.edu.hannah.model.KhoaHoc;

  import java.util.List;

  /**
   * Repository để quản lý các thao tác truy vấn với bảng `khoahoc`.
   * Kế thừa từ JpaRepository để có các phương thức CRUD cơ bản.
   */
  @Repository
  public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Integer> {
      /**
       * Tìm danh sách khóa học theo trạng thái (ví dụ: HoatDong, ChoDuyet, An).
       * @param trangThai Trạng thái của khóa học.
       * @return Danh sách các khóa học phù hợp.
       */
      List<KhoaHoc> findByTrangThai(KhoaHoc.TrangThai trangThai);
      List<KhoaHoc> findByCapDo(KhoaHoc.CapDo capDo);

      /**
       * Tìm danh sách khóa học theo mã giảng viên.
       * @param maGiangVien Mã của giảng viên.
       * @return Danh sách các khóa học do giảng viên này tạo.
       */
      List<KhoaHoc> findByGiangVienMaNguoiDung(Integer maGiangVien);
        /**
       * Tìm danh sách khóa học theo mã chủ đề.
       * @param maChuDe Mã của chủ đề.
       * @return Danh sách các khóa học liên quan đến chủ đề.
       */
      List<KhoaHoc> findByChuDes_MaChuDe(Integer maChuDe);
  }

