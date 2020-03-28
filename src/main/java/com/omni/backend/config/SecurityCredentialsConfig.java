package com.omni.backend.config;

import com.omni.aurora.core.property.JWTConfiguration;
import com.omni.aurora.token.config.SecurityTokenConfig;
import com.omni.aurora.token.converter.TokenConverter;
import com.omni.aurora.token.filter.JwtTokenAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {

    private TokenConverter tokenConverter;

    public SecurityCredentialsConfig(
            final JWTConfiguration jwtConfiguration,
            final TokenConverter tokenConverter) {
        super(jwtConfiguration);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .authorizeRequests().anyRequest().permitAll();
                ;

//        http.addFilterAfter(
//                new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
//                UsernamePasswordAuthenticationFilter.class);
//        super.configure(http);
    }

}