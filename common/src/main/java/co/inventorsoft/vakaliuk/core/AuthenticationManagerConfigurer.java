package co.inventorsoft.vakaliuk.core;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthenticationManagerConfigurer {

    public static void configure(AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        auth
                .inMemoryAuthentication()
                        .withUser("pavlo_zibrov")
                        .password("$2a$10$jAsnVSR6VbtNI96A0yEk3eTH78y.QBLpHqDD4OYRbjv0ApoKOKI/m")
                        .roles("USER")
                    .and()
                        .withUser("ivo_bobul")
                        .password("$2a$10$NWDZgF7zT8yaG2pzCHlQeOx0WYjJn5Sa0bzp0kSilGT3t6IcfEHvy")
                        .roles("ADMIN")
                    .and()
                .passwordEncoder(new BCryptPasswordEncoder());
        // @formatter:on
    }
}
