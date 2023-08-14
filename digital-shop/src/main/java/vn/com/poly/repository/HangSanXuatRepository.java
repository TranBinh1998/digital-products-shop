package vn.com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.poly.entities.HangSanXuat;

@Repository
public interface HangSanXuatRepository extends JpaRepository<HangSanXuat, Long> {
}
