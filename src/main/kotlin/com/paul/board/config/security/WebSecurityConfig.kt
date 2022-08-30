package com.paul.board.config.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
class WebSecurityConfig(
    private val userDetailsService: CustomUserDetailsService,
    private val authenticationFailureHandler: CustomAuthenticationFailureHandler
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? =
        http
            .cors().disable()
            .csrf().disable()
            .authorizeRequests().antMatchers("/**").permitAll()
            .anyRequest().authenticated()
            .and()

            .formLogin()
            .loginPage("/sign-in")
            .defaultSuccessUrl("/")
            .failureHandler(authenticationFailureHandler)
            .permitAll()
            .and()

            .logout()
            .logoutSuccessUrl("/")
            .permitAll()
            .and()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .build()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder(4)
}