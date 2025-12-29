package K23CNT3_TranThanhTung_WebBanDienThoai.config;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttAdmin;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private TttAdminRepository adminRepo;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt để gửi form login không bị chặn
                .authorizeHttpRequests(auth -> auth
                        // Cho phép các tài nguyên tĩnh và trang chủ
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/").permitAll()
                        // Chỉ những ai đã đăng nhập mới được vào các trang bắt đầu bằng /admin
                        .requestMatchers("/admin/login").permitAll()
                        .requestMatchers("/admin/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/admin/login") // Đường dẫn trang login của bạn
                        .loginProcessingUrl("/admin/login") // URL để Security xử lý (khớp với action của form)
                        .defaultSuccessUrl("/admin", true) // Đăng nhập xong vào thẳng trang chủ Admin
                        .failureUrl("/admin/login?error=true") // Nếu sai tài khoản
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/admin/login")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Sử dụng repository của bạn để tìm admin
            TttAdmin admin = adminRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy admin: " + username));

            // Trả về đối tượng User của Security
            // {noop} dùng để báo cho Security rằng mật khẩu trong DB đang để dạng text thuần (chưa mã hóa)
            return User.withUsername(admin.getUsername())
                    .password("{noop}" + admin.getPassword())
                    .roles("ADMIN")
                    .build();
        };
    }
}