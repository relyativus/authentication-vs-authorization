package co.inventorsoft.vakaliuk.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("insecure").secret("test")
                .authorizedGrantTypes("authorization_code")
                .and()
                .withClient("not_trusted_web").secret("test").authorizedGrantTypes("implicit")
                .and()
                .withClient("webapp").secret("test").authorizedGrantTypes("client_credentials")
                .and()
                .withClient("trusted").secret("test").authorizedGrantTypes("password").scopes("read");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    /**
     * Required only to setup authentication manager properly
     */
    @Configuration
    static class AuthManagerConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(inMemoryUserDetails()).passwordEncoder(new BCryptPasswordEncoder());
        }

        @Bean
        @Override
        protected AuthenticationManager authenticationManager() throws Exception {
            return super.authenticationManager();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .formLogin().disable()
                    .httpBasic().disable();
        }

        private UserDetailsService inMemoryUserDetails() {
            final InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
            inMemoryUserDetailsManager.createUser(new User("pavlo_zibrov",
                    "$2a$10$jAsnVSR6VbtNI96A0yEk3eTH78y.QBLpHqDD4OYRbjv0ApoKOKI/m",
                    AuthorityUtils.createAuthorityList("ROLE_USER")));
            return inMemoryUserDetailsManager;
        }
    }


}
