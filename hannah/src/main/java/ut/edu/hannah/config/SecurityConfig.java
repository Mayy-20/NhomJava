// package ut.edu.hannah.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import ut.edu.hannah.services.NguoiDungService;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     private final NguoiDungService nguoiDungService;

//     public SecurityConfig(NguoiDungService nguoiDungService) {
//         this.nguoiDungService = nguoiDungService;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeHttpRequests((requests) -> requests
//                 .requestMatchers("/", "/home", "/courses", "/courses/**", "/about", "/support", "/register", "/login", "/resources/**").permitAll()
//                 .requestMatchers("/community", "/community/**", "/documents", "/documents/**", "/progress", "/progress/**", "/learn/**").authenticated()
//                 .anyRequest().authenticated()
//             )
//             .formLogin((form) -> form
//                 .loginPage("/login")
//                 .defaultSuccessUrl("/courses")
//                 .permitAll()
//             )
//             .logout((logout) -> logout
//                 .logoutUrl("/logout")
//                 .logoutSuccessUrl("/login")
//                 .permitAll()
//             );
//         return http.build();
//     }

//     @Bean
//     public UserDetailsService userDetailsService() {
//         return new UserDetailsService() {
//             @Override
//             public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) {
//                 return nguoiDungService.findByTenDangNhap(username)
//                         .map(user -> org.springframework.security.core.userdetails.User
//                                 .withUsername(user.getTenDangNhap())
//                                 .password(user.getMatKhau())
//                                 .roles(user.getVaiTro().getTenVaiTro())
//                                 .build())
//                         .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));
//             }
//         };
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }