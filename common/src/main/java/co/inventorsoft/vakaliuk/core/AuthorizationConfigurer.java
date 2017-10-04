package co.inventorsoft.vakaliuk.core;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class AuthorizationConfigurer {

    public static void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/protected/user").hasRole("USER")
                .mvcMatchers(HttpMethod.GET, "/protected/admin").hasRole("ADMIN")
                .antMatchers("/**").authenticated();
    }
}
