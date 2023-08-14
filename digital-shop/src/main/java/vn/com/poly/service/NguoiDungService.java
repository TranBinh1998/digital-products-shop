package vn.com.poly.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import vn.com.poly.entities.NguoiDung;

public interface NguoiDungService extends UserDetailsService {


    public  NguoiDung findByUserName(String userName);

    public void save(NguoiDung user);

    public boolean confirmPassWord(String passWord, String rePassWord);

}
