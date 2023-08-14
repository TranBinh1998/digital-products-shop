package vn.com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.poly.entities.VaiTro;

@Repository
public interface VaiTroRepository  extends JpaRepository<VaiTro, Long> {

     public  VaiTro findVaiTroByTenVaiTro(String roleName);
}
