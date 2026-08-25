package com.duoc.veterinaria.app.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class seguridadLogin {

    // CADENA 1: RUTAS /api/** -> SIN SESIÓN, PROTEGIDAS CON JWT
    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
            .securityMatcher("/api/**")
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/login").permitAll()
                .requestMatchers("/api/pacientes/**").hasAnyRole("ADMIN", "ASISTENTE")
                .requestMatchers("/api/citas/**").hasAnyRole("ADMIN", "VETERINARIO")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // CADENA 2: TODO LO DEMÁS -> SESIÓN + FORMULARIO (LO QUE YA TENÍAMOS)
    @Bean
    @Order(2)
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth

                // RUTAS PUBLICA
                .requestMatchers("/", "/login", "/css/**", "/error").permitAll()

                // RESTRICCIÓN DE ACCESO SEGÚN ROLES
                // ADMIN: puede acceder a todo
                // ASISTENTE: puede acceder a pacientes
                // VETERINARIO: puede acceder a citas
                .requestMatchers("/pacientes/**").hasAnyRole("ADMIN", "ASISTENTE")
                .requestMatchers("/citas/**").hasAnyRole("ADMIN", "VETERINARIO")
                .requestMatchers("/home").hasAnyRole("ADMIN", "VETERINARIO", "ASISTENTE")

                // LO DEMAS REQUIERE AUTENTICAR.
                .anyRequest().authenticated()
            )
            .formLogin(form -> form

                // PAGINA DE LOGIN
                .loginPage("/login")

                // SI EL LOGIN ES CORRECTO REDIRIGE
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    // NECESARIO PARA QUE LoginController PUEDA AUTENTICAR USUARIO/CLAVE
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        // SE CREAN LOS USUARIO EN LA MEMORIA

        UserDetails usr1 = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("Admin#2026"))
                .roles("ADMIN")
                .build();

        UserDetails usr2 = User.builder()
                .username("veterinario")
                .password(passwordEncoder.encode("Vet#2026"))
                .roles("VETERINARIO")
                .build();

        UserDetails usr3 = User.builder()
                .username("asistente")
                .password(passwordEncoder.encode("Asist#2026"))
                .roles("ASISTENTE")
                .build();

        return new InMemoryUserDetailsManager(usr1, usr2, usr3);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}