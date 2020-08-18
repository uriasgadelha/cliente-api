package io.github.uriasgadelha.clientes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/usuarios").permitAll()
                .antMatchers("/api/clientes/**").hasAnyRole("CLIENTE","ADMIN")
                .antMatchers("/api/servicos-prestados/**").hasAnyRole("SERVICO","ADMIN")
                .anyRequest().denyAll();
    }
}
