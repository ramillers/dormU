package br.dormU.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean //p instanciar a classe
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // ===== CONFIGURAÇÃO DE AUTORIZAÇÃO =====
            // Aqui definimos QUEM pode acessar O QUÊ
            .authorizeHttpRequests(auth -> auth
                // ROTAS PÚBLICAS (não precisam de autenticação)
                // permitAll() = qualquer pessoa pode acessar
                
                // H2 Console (apenas para desenvolvimento!)
                .requestMatchers("/h2-console/**").permitAll()
                
                // Endpoints de autenticação (login, registro)
                // Ainda não existem, mas já liberados
                .requestMatchers("/auth/**").permitAll()
                
                // Listagem pública de imóveis (qualquer um pode ver)
                .requestMatchers("/imoveis").permitAll()
                .requestMatchers("/imoveis/{id}").permitAll()
                
                // ROTAS PROTEGIDAS (precisam de autenticação)
                // anyRequest().authenticated() = tudo que não foi especificado acima
                // precisa estar autenticado
                .anyRequest().authenticated()
            );
        
        // Constrói e retorna a configuração
        return http.build();
    }
}
