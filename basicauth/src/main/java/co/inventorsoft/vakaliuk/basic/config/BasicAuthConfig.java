package co.inventorsoft.vakaliuk.basic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BasicAuthConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().realmName("Basic-demo");

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/protected/user").hasRole("USER")
                .mvcMatchers(HttpMethod.GET, "/protected/admin").hasRole("ADMIN")
                .antMatchers("/**").authenticated();
    }


}
