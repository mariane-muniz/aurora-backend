package com.omni.backend.config;

import com.omni.aurora.token.config.SecurityTokenConfig;
import com.omni.aurora.token.converter.TokenConverter;
import com.omni.aurora.token.property.JWTConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;

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
                .authorizeRequests()
                    .antMatchers(HttpMethod.PUT).permitAll()
                    .anyRequest().permitAll();
                ;



//        http.addFilterAfter(
//                new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
//                UsernamePasswordAuthenticationFilter.class);
//        super.configure(http);
    }

}