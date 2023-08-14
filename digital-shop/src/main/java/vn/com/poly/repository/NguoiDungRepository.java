package vn.com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.poly.entities.NguoiDung;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
//    @Query("SELECT n " +
//            " FROM NguoiDung n " +
//            " WHERE n.email = :email")
//    public NguoiDung findByEmail(@Param("email") String email);


    @Query("SELECT  n " +
            " from NguoiDung n " +
            " where n.userName = :userName")
    public NguoiDung findByUserName(@Param("userName") String  userName);


}
