    package com.kalanso.event.Security;
    
    import jakarta.servlet.Filter;
    import lombok.AllArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.AuthenticationProvider;
    import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
    import org.springframework.stereotype.Service;
    import org.springframework.web.servlet.config.annotation.CorsRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Service
    @EnableWebSecurity
    @AllArgsConstructor
    @Configuration
    public class SecurityConfig {
        @Autowired
        private UserDetailServiceConfig userDetail;
        @Autowired
        private JwtFilter jwtFilter;


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests((registry) -> {
                        registry
                                .requestMatchers("/client/voyage/get", "/client/compagnie/get","/client/agence/get/**").permitAll()
                                // Routes protégées
                                .requestMatchers("/api/client/getusername","/client/reservation/get/{id}").hasRole("Client")
                                .requestMatchers("/api/voyage/**").hasAnyRole("AdminA", "AdminC", "Admin")
                                .requestMatchers("/api/currentuser/get").hasAnyRole("AdminA", "AdminC", "Admin")
                                .requestMatchers("/api/admin_a/**").hasAnyRole("AdminC", "Admin")
                                .requestMatchers("/api/admin_c/**").hasRole("Admin")
                                .requestMatchers("/api/admin/bloquer/**").hasRole("Admin")
                                .requestMatchers("/api/admin/client/**").hasRole("Admin")
                                .requestMatchers("/api/admin_a/bloquer/**").hasRole("AdminC")
                                .requestMatchers("/api/compagnie/**").hasAnyRole("Admin", "Client")
                                .requestMatchers("/api/agence/get").hasAnyRole("AdminC", "Admin")
                                .requestMatchers("/api/agence/**").hasAnyRole("AdminC", "Client")
                                .requestMatchers("/api/client/annuler").hasAnyRole("Client")
                                .requestMatchers("/api/reservation/**").hasAnyRole("Client", "AdminA","AdminC","Admin")
                                //.requestMatchers("/api/client/ajout", "/api/client/getusername","/client/reservation/get/{id}").hasRole("Client")

                                .anyRequest().permitAll();
                    })
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        }



        @Configuration
        public class WebConfig implements WebMvcConfigurer {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Autoriser toutes les requêtes sur tous les endpoints
                        .allowedOrigins("http://localhost:8100") // Remplacez par l'URL de votre frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Méthodes autorisées
                        .allowedHeaders("*"); // Autoriser tous les en-têtes
            }
        }



        @Bean
        public AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService((userDetail));
            authProvider.setPasswordEncoder(passwordEncoder());
            return authProvider;
        }
    
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }
    
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    
    }
