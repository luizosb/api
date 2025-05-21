package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Indica que esta classe é uma configuração do Spring (substitui o XML antigo)
@EnableWebSecurity // Habilita as configurações personalizadas de segurança na aplicação
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    /**
     * Define a cadeia de filtros de segurança (SecurityFilterChain),
     * que determina como as requisições HTTP serão tratadas no quesito segurança.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Desativa proteção contra CSRF (usada em aplicações web com sessões e formulários)
                // Desnecessário e até problemático para APIs REST, que são stateless.
                .csrf(csrf -> csrf.disable())

                // Define que a API será stateless (sem uso de sessões)
                // Cada requisição deve conter todas as informações de autenticação (ex: JWT)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req->{
                    req.requestMatchers("/login").permitAll();
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                // build() finaliza a configuração da cadeia de filtros
                .build();
    }

    /**
     * Torna o AuthenticationManager acessível como um @Bean,
     * para que possa ser injetado em outras classes (ex: no controller de login).
     *
     * Ele é responsável por coordenar o processo de autenticação.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Define o algoritmo de criptografia de senhas usado na aplicação.
     *
     * BCrypt é um dos algoritmos mais seguros atualmente para armazenar senhas.
     * Ele já inclui salt interno e é resistente a ataques de força bruta.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
