package vn.com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.com.poly.entities.NguoiDung;
import vn.com.poly.entities.VaiTro;
import vn.com.poly.repository.NguoiDungRepository;
import vn.com.poly.service.NguoiDungService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;



    @Override
    public NguoiDung findByUserName(String userName) {
        return nguoiDungRepository.findByUserName(userName);
    }

    @Override
    public void save(NguoiDung user) {
        nguoiDungRepository.save(user);
    }

    @Override
    public boolean confirmPassWord(String passWord, String rePassWord) {
        return passWord.equals(rePassWord);
    }


    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<VaiTro> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getTenVaiTro())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserName = "+username);
        NguoiDung nguoiDung = nguoiDungRepository.findByUserName(username);
        if (nguoiDung==null) {
            System.out.println("Lại sai");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(nguoiDung.getUserName(), nguoiDung.getPassword(),rolesToAuthorities(nguoiDung.getVaiTro()));
    }
}
