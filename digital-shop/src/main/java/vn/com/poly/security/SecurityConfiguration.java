package vn.com.poly.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import vn.com.poly.service.NguoiDungService;

import java.io.IOException;
import java.util.Set;

@Configuration
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(NguoiDungService nguoiDungService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(nguoiDungService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }





    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                configurer->configurer
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/san-phams/**").permitAll()
                        .requestMatchers("/cart-api/**").permitAll()
                        .requestMatchers("/uploads/**").permitAll()
                        .anyRequest().authenticated()
        ).formLogin(
                form->form.loginPage("/login/showLoginPage")
                        // chỉ định 1 url tùy chỉnh
                        .successHandler(roleBasedSuccessHandler())
                        .loginProcessingUrl("/authenticateTheUser").permitAll()
        ).logout(
                logout->logout.permitAll().logoutSuccessHandler(logoutSuccessHandler())
        ).exceptionHandling(
                configurer->configurer.accessDeniedPage("/showPage403")
        );

        return http.build();
    }
    @Bean
    public AuthenticationSuccessHandler roleBasedSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                System.out.println("Đến đây chưa lỗi 1");
                Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

                String userName = authentication.getName();
                HttpSession session1 = request.getSession();
                session1.setAttribute("userName", userName);
                if (roles.contains("ROLE_ADMIN")) {
                    response.sendRedirect("/admin"); // chuyển hướng đến trang admin nếu người dùng có vai trò admin
                } else {
                    SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
                    HttpSession session = request.getSession();
                    String previousUrl = (String) session.getAttribute("previousUrl");
                    System.out.println("Đến đây chưa lỗi 2 ");
                    System.out.println("request" +previousUrl);
                    handler.setDefaultTargetUrl(previousUrl);
                    // sử dụng tiêu đề Referer nếu người dùng không phải là admin
                    System.out.println("Đến đây chưa lỗi 3 ");
                    handler.onAuthenticationSuccess(request, response, authentication); // gọi phương thức của lớp cha để chuyển hướng đến URL Referer
                }
            }
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();
        // Cấu hnhf đối tượng logoutSuccessHandler khi đăng xuất thì chuyển hướng đến home
        handler.setDefaultTargetUrl("/");
        return handler;
    }



}
